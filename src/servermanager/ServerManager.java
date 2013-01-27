/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servermanager;


import javax.swing.*;
import java.awt.*;

public class ServerManager extends javax.swing.JFrame {


    public ServerManager() {
        setTitle("Menadżer Serwera");
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(new TableDisplay("user").getModel());
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(new TableDisplay("employee").getModel());
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable(new TableDisplay("operator").getModel());
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable(new TableDisplay("shipment").getModel());
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable(new TableDisplay("courier").getModel());
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable(new TableDisplay("address_book").getModel());
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable(new TableDisplay("country").getModel());
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable(new TableDisplay("courier_history").getModel());



        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        jScrollPane1.setViewportView(jTable1);


        jTabbedPane1.addTab("Użytkownicy", jScrollPane1);


        jScrollPane2.setViewportView(jTable2);
        jScrollPane2.getAutoscrolls();

        jTabbedPane1.addTab("Pracownicy", jScrollPane2);


        jScrollPane3.setViewportView(jTable3);

        jTabbedPane1.addTab("Operatorzy", jScrollPane3);


        jScrollPane4.setViewportView(jTable4);

        jTabbedPane1.addTab("Paczki", jScrollPane4);


        jScrollPane5.setViewportView(jTable5);

        jTabbedPane1.addTab("Kurierzy", jScrollPane5);


        jScrollPane6.setViewportView(jTable6);

        jTabbedPane1.addTab("Historia", jScrollPane6);


        jScrollPane7.setViewportView(jTable7);

        jTabbedPane1.addTab("Kraje", jScrollPane7);


        jScrollPane8.setViewportView(jTable8);

        jTabbedPane1.addTab("Książka", jScrollPane8);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

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
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerManager().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;

    // End of variables declaration
}
