
package Lex;

import Lex.Interface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Splash extends javax.swing.JFrame {

    public Splash() {
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension size = miPantalla.getScreenSize();
        double alto,ancho;
        alto = size.getHeight();
        ancho = size.getWidth();
        this.setLocation((int)(ancho/2)-224, (int)(alto/2)-228);
        initComponents();
        
      
   
    }
    

    
    private static void sleepSplash(){
       
     try {
        
			Thread.sleep(0);
                        
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
            
        
   
 
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lex/Splash.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(448, 456));
        jLabel1.setMinimumSize(new java.awt.Dimension(448, 456));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 456));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
              Splash sp = new Splash();
              sp.setVisible(true);
                sleepSplash();
                sp.setVisible(false);
                new Interface().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
