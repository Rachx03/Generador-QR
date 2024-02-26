
package com.mycompany.generador_de_qr ;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Rachix
 */
public class QR extends javax.swing.JFrame {

    /**
     * Creates new form QR
     */
    public QR() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CajaTexto = new javax.swing.JTextField();
        Generar = new javax.swing.JButton();
        Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Generar.setText("Generar");
        Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(Generar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CajaTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addComponent(Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(CajaTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Generar)
                .addGap(32, 32, 32)
                .addComponent(Label, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarActionPerformed
        int size=1000;
        String FileType="png";
        String codigo=CajaTexto.getText().trim();
     //1.Elegir la ruta de la imagen
       String filePath="";
       JFileChooser chooser=new JFileChooser();
       chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
           filePath=chooser.getSelectedFile().getAbsolutePath();
       }
       //2.Generar el nombre 
       UUID uuid=UUID.randomUUID();
       String randomName=uuid.toString();
       
       //3.Generar el QR
       QRCodeWriter qrcode=new QRCodeWriter();
        try {
            BitMatrix matrix=qrcode.encode(codigo, BarcodeFormat.QR_CODE, size, size);
             File f =new File(filePath+"/"+randomName+"."+FileType);
             int matrixWidth=matrix.getWidth();//Obtener el tamaño de la matriz
             BufferedImage imagen= new BufferedImage(matrixWidth, matrixWidth,BufferedImage.TYPE_INT_RGB);
             imagen.createGraphics();
             Graphics2D gd= (Graphics2D) imagen.getGraphics();
             gd.setColor(Color.WHITE);//Fondo
             gd.fillRect(0, 0, size, size);
               gd.setColor(Color.BLACK);//QR
               //Generar imagen rellenando la matriz
               for(int i =0;i<matrixWidth;i++){
                   for(int j=0;j<matrixWidth;j++){
                       if(matrix.get(i, j)){
                           gd.fillRect(i, j, 1, 1);
                       }
                   }
               }
               
             
            try {
                //4.Mostrar la imagen generada
                ImageIO.write(imagen, FileType, f);
            } catch (IOException ex) {
                Logger.getLogger(QR.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image mImagen= new ImageIcon(filePath+"/"+randomName+"."+FileType).getImage();
            ImageIcon mIcono=new ImageIcon(mImagen.getScaledInstance(Label.getWidth(),Label.getHeight(), 0));
            Label.setIcon(mIcono);
        } catch (WriterException ex) {
            Logger.getLogger(QR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GenerarActionPerformed

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
            java.util.logging.Logger.getLogger(QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CajaTexto;
    private javax.swing.JButton Generar;
    private javax.swing.JLabel Label;
    // End of variables declaration//GEN-END:variables
}
