/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ReserverSession;
import controller.config;
import view.parentinterface;

/**
 *
 * @author 33781
 */
public class Sessionsparent extends javax.swing.JFrame {
    static Sessionsparent s1 = new Sessionsparent();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private ReserverSession rss = new ReserverSession(jTable1, jTextField1);

    /**
     * Creates new form Sessionsparent
     */
    public Sessionsparent() {
        initComponents();
        ShowTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 153));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {                   
                },
                new String[] {
                        "Id Session", "Date", "Heure", "Patient", "Nb places"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jTable1.setRowHeight(100);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Reserver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Connection con = DriverManager.getConnection(config.url, config.user, config.password);
                    int row = jTable1.getSelectedRow();
                    String a = jTextField1.getText();
                    String cell = jTable1.getModel().getValueAt(row, 0).toString();
                    String sql3 = "SELECT NOMBRE_PLACE FROM SESSION WHERE idSESSION=" + Integer.parseInt(cell) + "";
                    PreparedStatement pst3 = con.prepareStatement(sql3);
                    ResultSet rs = pst3.executeQuery(sql3);
                    if (rs.next()) {
                        int g = rs.getInt("NOMBRE_PLACE");                        int s;
                        s = g - 1;
                        String sql = "UPDATE cantine.SESSION SET NOMBRE_PLACE=" + s + " where idSESSION=" + Integer.parseInt(cell) + "";
                        
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.execute(sql);
                        System.out.println(a);
                        String sql4 = "SELECT LAST_NAME FROM PATIENT WHERE LOGIN='" + a + "'";; 

                        PreparedStatement pst4 = con.prepareStatement(sql4);
                        {
                        ResultSet rs2 = pst4.executeQuery(sql4);
                        if (rs2.next()) {
                        String name = rs2.getString("LAST_NAME");
                        String sql5 = "UPDATE cantine.SESSION SET PATIENT='" + name + "' WHERE idSESSION=" + Integer.parseInt(cell) + "";    
                        PreparedStatement pst5 = con.prepareStatement(sql5);
                        pst5.executeUpdate(sql5);


                        JOptionPane.showMessageDialog(null, "Vous avez à été Inscrit à la Session" + Integer.parseInt(cell) + " ... ");
                        // verify availale places
                        }
                    }}
                    con.close();
                }

                catch (Exception ez) {
                    JOptionPane.showMessageDialog(null, ez);

                }
            }
        });

        jLabel1.setText("Entrez votre identifiant");
        jButton3.setText("Annuler");

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Connection con = DriverManager.getConnection(config.url, config.user, config.password);
                    int row = jTable1.getSelectedRow();
                    String a = jTextField1.getText();
                    String cell = jTable1.getModel().getValueAt(row, 0).toString();
                    String sql3 = "SELECT NOMBRE_PLACE FROM SESSION WHERE idSESSION=" + Integer.parseInt(cell) + "";
                    PreparedStatement pst3 = con.prepareStatement(sql3);
                    ResultSet rs = pst3.executeQuery(sql3);
                    if (rs.next()) {
                        int g = rs.getInt("NOMBRE_PLACE");
                        int s = g + 1;
                        String sql = "UPDATE cantine.SESSION SET NOMBRE_PLACE=" + s + " where idSESSION=" + cell + "";
                        String sql2 = "DELETE FROM cantine.SESSION_has_ENFANT WHERE SESSION_idSESSION = "+ cell + "";
                        PreparedStatement pst2 = con.prepareStatement(sql2);
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.execute(sql);
                        pst2.execute(sql2);
                        JOptionPane.showMessageDialog(null, "Votre enfant à été Désinscrit à la Session" + cell + " ... ");
                    }
                    con.close();
                }

                catch (Exception ez) {
                    JOptionPane.showMessageDialog(null, ez);

                }
            }
        });
        jButton2.setText("Retour ");

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
                parentinterface pa2 = new parentinterface();
                pa2.main();

            }
        });
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main() {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sessionsparent.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sessionsparent.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sessionsparent.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sessionsparent.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sessionsparent().setVisible(true);
            }
        });
    }

   

    public void ShowTable() {
        String add2;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(config.url, config.user, config.password);
            String sql = "SELECT * FROM cantine.SESSION WHERE NOMBRE_PLACE >= 1";
            // String sql3 = "SELECT * FROM cantineV2.SESSION where idSESSION =? ";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            String sql2 = "SELECT * FROM cantine.MENU";
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setRowCount(0);
            while (rs.next()) {
                Object o[] = { rs.getInt("idSESSION"), rs.getString("JOUR_RESERVATION"), rs.getString("HEURE"),
                        rs.getString("PATIENT"),
                        rs.getInt("NOMBRE_PLACE") };
                tm.addRow(o);
            }
            con.close();

        } catch (Exception ez) {
            // TODO Auto-generated catch block
            ez.printStackTrace();
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables
}
