package ScreenPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vhqua
 */
public class PlayScreen extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    GamePanel g;

    public PlayScreen() {
        initComponents();
        ImageIcon img = new ImageIcon("src/data/main-character1.png");
        this.setIconImage(img.getImage());
        this.setTitle("Dinosaur");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        g = new GamePanel();
        pnlGamePlay.setLayout(new BorderLayout());
        pnlGamePlay.add(g);
        this.addKeyListener(g);
        this.pack();
        pnlScore.setBackground(Color.decode("#f7f7f7"));
        tScore1.setEnabled(false);
        tHighestScore.setEnabled(false);
        btnback.setVisible(false);

    }

    public void returnMenu() {
        this.dispose();
        JFrame menu = new MainScreen();
        menu.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGamePlay = new javax.swing.JPanel();
        pnlScore = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tHighestScore = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        tScore1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout pnlGamePlayLayout = new javax.swing.GroupLayout(pnlGamePlay);
        pnlGamePlay.setLayout(pnlGamePlayLayout);
        pnlGamePlayLayout.setHorizontalGroup(
            pnlGamePlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        pnlGamePlayLayout.setVerticalGroup(
            pnlGamePlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        getContentPane().add(pnlGamePlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 620, 180));

        pnlScore.setBorder(javax.swing.BorderFactory.createTitledBorder("Game Information"));
        pnlScore.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Score:");
        pnlScore.add(jLabel1);
        jLabel1.setBounds(10, 20, 40, 45);

        tHighestScore.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tHighestScore.setText("0000");
        pnlScore.add(tHighestScore);
        tHighestScore.setBounds(200, 20, 60, 45);

        btnback.setBackground(Color.decode("#f7f7f7"));
        btnback.setText("Back to menu");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });
        pnlScore.add(btnback);
        btnback.setBounds(480, 30, 120, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("  Highest score:");
        pnlScore.add(jLabel3);
        jLabel3.setBounds(110, 20, 90, 45);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Time:");
        pnlScore.add(jLabel4);
        jLabel4.setBounds(290, 20, 40, 45);

        lbTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTime.setText("00:00:00");
        pnlScore.add(lbTime);
        lbTime.setBounds(330, 20, 80, 45);

        tScore1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tScore1.setText("0000");
        pnlScore.add(tScore1);
        tScore1.setBounds(50, 20, 60, 45);

        getContentPane().add(pnlScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        returnMenu();
        g.saveHighestScore();
        g.thread = null;
    }//GEN-LAST:event_btnbackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnback;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel lbTime;
    private javax.swing.JPanel pnlGamePlay;
    private javax.swing.JPanel pnlScore;
    public static javax.swing.JLabel tHighestScore;
    public static javax.swing.JLabel tScore1;
    // End of variables declaration//GEN-END:variables
}
