/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PlayerGUI.java
 *
 * Created on Oct 26, 2012, 11:06:31 PM
 */
package com.musicplayer.gui;

import com.musicplayer.bll.AddSongDialog;
import com.musicplayer.bll.Library;
import com.musicplayer.bll.LibraryDataModel;
import com.musicplayer.bll.LibraryRepository;
import com.musicplayer.bll.Playlist;
import com.musicplayer.bll.PlaylistDataModel;
import com.musicplayer.exceptions.PlaylistNotFoundException;
import com.musicplayer.bll.PlaylistRepository;
import com.musicplayer.bll.Song;
import com.musicplayer.bll.UserAccount;
import com.musicplayer.bll.UserRepository;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author TJ
 */
public class PlayerGUI extends javax.swing.JFrame {

    private UserAccount currentUser;
    private Library currentLibrary;
    private LibraryRepository repo;
    private UserRepository userRepo;
    private PlaylistRepository listRepo;

    /**
     * Creates new form PlayerGUI
     */
    public PlayerGUI() throws Exception {
        initComponents();
        repo = new LibraryRepository();
        userRepo = new UserRepository();
        listRepo = new PlaylistRepository();
        tblPlaylists.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tblPlaylists.getSelectedRow() != -1) {
                    if (!e.getValueIsAdjusting()) {
                        displayPlaylist();
                    }
                }
            }
        });
    }

    public void loadLibrary() {
        this.currentLibrary = repo.getUserLibrary(currentUser);
    }

    private void loadPlaylists() {
        tblPlaylists.setModel(new PlaylistDataModel(listRepo.getUserPlaylists(currentUser)));
    }

    public void updateLibrary() {
        tblLibrary.setModel(new LibraryDataModel(currentLibrary));
    }

    public void updatePlaylists() {
        PlaylistDataModel model = (PlaylistDataModel) tblPlaylists.getModel();
        model.setData(listRepo.getUserPlaylists(currentUser));
        model.fireTableDataChanged();
    }

    public void setUser(UserAccount user) {
        this.currentUser = user;
        loadLibrary();
        loadPlaylists();
        updateLibrary();
    }

    public void displayPlaylist() {
        Playlist list = null;
        try {
            list = listRepo.getPlaylist(currentUser, tblPlaylists.getValueAt(tblPlaylists.getSelectedRow(), 0).toString());
        } catch (PlaylistNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        
        Object[] songs = list.getList();
        DefaultListModel model = (DefaultListModel) listPlaylist.getModel();
        model.clear();
        for (int i = 0; i < songs.length; i++) {
            model.add(i, songs[i]);
        }
    }

    public Library currentLibrary() {
        return this.currentLibrary;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblCurrentTrack = new javax.swing.JLabel();
        sldCurrentPos = new javax.swing.JSlider();
        lblCurrentTime = new javax.swing.JLabel();
        lblEndTime = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listPlaylist = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPlaylists = new javax.swing.JTable();
        btnAddToLibrary = new javax.swing.JButton();
        btnRemoveFromLibrary = new javax.swing.JButton();
        btnNewPlaylist = new javax.swing.JButton();
        btnDeletePlaylist = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLibrary = new javax.swing.JTable();
        txtPlaylistTitle = new javax.swing.JTextField();
        btnAddToPlaylist = new javax.swing.JButton();
        btnRemoveFromPlaylist = new javax.swing.JButton();
        addSongToPlaylistButton = new javax.swing.JButton();
        removeSongFromPlaylistButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuFileClose = new javax.swing.JMenuItem();
        mnuLibrary = new javax.swing.JMenu();
        mnuLibraryAddSong = new javax.swing.JMenuItem();
        mnuLibraryAddPlaylist = new javax.swing.JMenuItem();
        mnuUser = new javax.swing.JMenu();
        mnuLogin = new javax.swing.JMenuItem();
        mnuLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Titan Player");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCurrentTrack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCurrentTrack.setText("Currently Playing...");
        lblCurrentTrack.setName(""); // NOI18N

        sldCurrentPos.setValue(0);

        lblCurrentTime.setText("0:00");

        lblEndTime.setText("0:00");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/musicplayer/gui/splay.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/musicplayer/gui/sstop.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/musicplayer/gui/sforward.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setContentAreaFilled(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/musicplayer/gui/sback.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lblCurrentTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEndTime)
                .addGap(80, 80, 80))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(lblCurrentTrack))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(sldCurrentPos, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblCurrentTrack)
                .addGap(18, 18, 18)
                .addComponent(sldCurrentPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndTime)
                    .addComponent(lblCurrentTime))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        listPlaylist.setBackground(new java.awt.Color(240, 240, 240));
        listPlaylist.setModel(new DefaultListModel());
        listPlaylist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listPlaylist);

        tblPlaylists.setModel(new PlaylistDataModel(new ArrayList()));
        tblPlaylists.setShowHorizontalLines(false);
        tblPlaylists.setShowVerticalLines(false);
        tblPlaylists.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblPlaylists);

        btnAddToLibrary.setText("Add To Library");
        btnAddToLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToLibraryActionPerformed(evt);
            }
        });

        btnRemoveFromLibrary.setText("Remove From Library");
        btnRemoveFromLibrary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromLibraryActionPerformed(evt);
            }
        });

        btnNewPlaylist.setText("New Playlist");
        btnNewPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPlaylistActionPerformed(evt);
            }
        });

        btnDeletePlaylist.setText("Delete Playlist");
        btnDeletePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePlaylistActionPerformed(evt);
            }
        });

        tblLibrary.setAutoCreateRowSorter(true);
        tblLibrary.setModel(new LibraryDataModel(new Library()));
        jScrollPane4.setViewportView(tblLibrary);

        txtPlaylistTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlaylistTitleActionPerformed(evt);
            }
        });
        txtPlaylistTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPlaylistTitleFocusGained(evt);
            }
        });

        btnAddToPlaylist.setText("Add Song");
        btnAddToPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToPlaylistActionPerformed(evt);
            }
        });

        btnRemoveFromPlaylist.setText("Remove Song");
        btnRemoveFromPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromPlaylistActionPerformed(evt);
            }
        });

        addSongToPlaylistButton.setText("Add Song");

        removeSongFromPlaylistButton.setText("Remove Song");

        mnuFile.setText("File");

        mnuFileClose.setText("Close");
        mnuFileClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFileCloseActionPerformed(evt);
            }
        });
        mnuFile.add(mnuFileClose);

        jMenuBar1.add(mnuFile);

        mnuLibrary.setText("Library");

        mnuLibraryAddSong.setText("Add Song");
        mnuLibraryAddSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLibraryAddSongActionPerformed(evt);
            }
        });
        mnuLibrary.add(mnuLibraryAddSong);

        mnuLibraryAddPlaylist.setText("Add Playlist");
        mnuLibraryAddPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLibraryAddPlaylistActionPerformed(evt);
            }
        });
        mnuLibrary.add(mnuLibraryAddPlaylist);

        jMenuBar1.add(mnuLibrary);

        mnuUser.setText("User");

        mnuLogin.setText("Login");
        mnuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLoginActionPerformed(evt);
            }
        });
        mnuUser.add(mnuLogin);

        mnuLogout.setText("Logout");
        mnuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLogoutActionPerformed(evt);
            }
        });
        mnuUser.add(mnuLogout);

        jMenuBar1.add(mnuUser);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddToLibrary)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoveFromLibrary))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 228, Short.MAX_VALUE)
                        .addComponent(btnDeletePlaylist))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNewPlaylist)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPlaylistTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddToPlaylist)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemoveFromPlaylist)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddToPlaylist)
                            .addComponent(btnRemoveFromPlaylist))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddToLibrary)
                    .addComponent(btnRemoveFromLibrary)
                    .addComponent(btnNewPlaylist)
                    .addComponent(btnDeletePlaylist)
                    .addComponent(txtPlaylistTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*public void setUser(UserAccount user){
     this.currentUser = user;
     }*/
    private void btnNewPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPlaylistActionPerformed
        if (currentUser != null) {
            if (!txtPlaylistTitle.getText().isEmpty()) {
                Playlist list = new Playlist(txtPlaylistTitle.getText());
                list.addUser(currentUser);
                listRepo.addPlaylist(list);
                txtPlaylistTitle.setText(null);
                updatePlaylists();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Enter a playlist title", "Playlist Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnNewPlaylistActionPerformed

    private void btnAddToLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToLibraryActionPerformed
        if (currentUser != null) {
            new AddSongDialog().showDialog(this);
        }
    }//GEN-LAST:event_btnAddToLibraryActionPerformed

    private void mnuLibraryAddPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLibraryAddPlaylistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuLibraryAddPlaylistActionPerformed

    private void mnuLibraryAddSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLibraryAddSongActionPerformed
        if (currentUser != null) {
            new AddSongDialog().showDialog(this);
        }
    }//GEN-LAST:event_mnuLibraryAddSongActionPerformed

    private void mnuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLoginActionPerformed
        LoginDialog log = new LoginDialog(this, userRepo);
        log.setLocationRelativeTo(this);
        log.setVisible(true);
    }//GEN-LAST:event_mnuLoginActionPerformed

    private void mnuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLogoutActionPerformed
        if (currentUser != null) {
            currentUser = null;
            currentLibrary = null;
            tblLibrary.setModel(new LibraryDataModel(new Library()));
            updatePlaylists();
            setTitle("Titan Player");
        }
    }//GEN-LAST:event_mnuLogoutActionPerformed

    private void mnuFileCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFileCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_mnuFileCloseActionPerformed

    private void btnRemoveFromLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromLibraryActionPerformed
        int selectedRow = tblLibrary.getSelectedRow();
        if (selectedRow != -1) {
            currentLibrary.removeSong(tblLibrary.getValueAt(selectedRow, 0).toString(), tblLibrary.getValueAt(selectedRow, 1).toString());
            updateLibrary();
        }
    }//GEN-LAST:event_btnRemoveFromLibraryActionPerformed

    private void btnDeletePlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePlaylistActionPerformed
        int selectedRow = tblPlaylists.getSelectedRow();
        if (selectedRow != -1) {
            listRepo.deletePlaylist(currentUser, tblPlaylists.getValueAt(selectedRow, 0).toString());
            updatePlaylists();
        } else {
            JOptionPane.showMessageDialog(this, "Select a playlist to be deleted", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeletePlaylistActionPerformed

    private void txtPlaylistTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlaylistTitleActionPerformed
    }//GEN-LAST:event_txtPlaylistTitleActionPerformed

    private void txtPlaylistTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlaylistTitleFocusGained
        tblPlaylists.clearSelection();
    }//GEN-LAST:event_txtPlaylistTitleFocusGained

    private void btnAddToPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToPlaylistActionPerformed
        if (tblLibrary.getSelectedRow() != -1) { 
            if (tblPlaylists.getSelectedRow() != -1) {                
                try {
                    System.out.println("Add song to playlist");
                    int plrow = tblPlaylists.getSelectedRow();
                    int lrow = tblLibrary.getSelectedRow();
                    Playlist list = listRepo.getPlaylist(currentUser, tblPlaylists.getValueAt(plrow, 0).toString());
                    Song toAdd = currentLibrary.getSong(tblLibrary.getValueAt(lrow, 0).toString(), tblLibrary.getValueAt(lrow, 1).toString());
                    list.addSong(toAdd);
                    updatePlaylists();
                } catch (PlaylistNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                // Display message that a playlist must be selected.
                JOptionPane.showMessageDialog(this, "A playlist must be selected", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Display message that a song must be selected.
            JOptionPane.showMessageDialog(this, "A song from the library must be selected", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAddToPlaylistActionPerformed

    private void btnRemoveFromPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromPlaylistActionPerformed
        if (tblPlaylists.getSelectedRow() != -1) {
            if (listPlaylist.getSelectedIndex() != -1) {
                try {
                    Playlist list = listRepo.getPlaylist(currentUser, tblPlaylists.getValueAt(tblPlaylists.getSelectedRow(), 0).toString());
                    list.removeSong((Song) listPlaylist.getSelectedValue());
                    updatePlaylists();
                } catch (PlaylistNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            } else {
                // Display message that a song must be selected.
                JOptionPane.showMessageDialog(this, "A song from the playlist must be selected", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Display message that a playlist must be selected.
            JOptionPane.showMessageDialog(this, "A playlist must be selected", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveFromPlaylistActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                //Uncomment to have login form at startup

                PlayerGUI gui = null;
                try {
                    gui = new PlayerGUI();
                    gui.setLocationRelativeTo(null);
                    gui.setVisible(true);
                    LoginDialog log = new LoginDialog(gui, gui.userRepo);
                    log.setTitle("Welcome To Titan Player");
                    log.setLocationRelativeTo(gui);
                    log.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSongToPlaylistButton;
    private javax.swing.JButton btnAddToLibrary;
    private javax.swing.JButton btnAddToPlaylist;
    private javax.swing.JButton btnDeletePlaylist;
    private javax.swing.JButton btnNewPlaylist;
    private javax.swing.JButton btnRemoveFromLibrary;
    private javax.swing.JButton btnRemoveFromPlaylist;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCurrentTime;
    private javax.swing.JLabel lblCurrentTrack;
    private javax.swing.JLabel lblEndTime;
    private javax.swing.JList listPlaylist;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuFileClose;
    private javax.swing.JMenu mnuLibrary;
    private javax.swing.JMenuItem mnuLibraryAddPlaylist;
    private javax.swing.JMenuItem mnuLibraryAddSong;
    private javax.swing.JMenuItem mnuLogin;
    private javax.swing.JMenuItem mnuLogout;
    private javax.swing.JMenu mnuUser;
    private javax.swing.JButton removeSongFromPlaylistButton;
    private javax.swing.JSlider sldCurrentPos;
    private javax.swing.JTable tblLibrary;
    private javax.swing.JTable tblPlaylists;
    private javax.swing.JTextField txtPlaylistTitle;
    // End of variables declaration//GEN-END:variables
}
