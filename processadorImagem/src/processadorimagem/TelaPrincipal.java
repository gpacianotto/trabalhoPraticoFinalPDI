/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processadorimagem;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import static oracle.jrockit.jfr.events.Bits.floatValue;

/**
 *
 * @author SAMSUNG
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    private ViewPanel canvas;
    private ImagemPGM imagem;
    public TelaPrincipal() {
        initComponents();
        
        try{
            imagem = new ImagemPGM("kook.pgm");
        }
        catch(FileNotFoundException e){
            
        }
        
        imagem.salvarImagem(imagem.getCurrentMatrix(), "");
        
        painelPrincipal.setSize(imagem.getLinha(), imagem.getColuna());
        
        this.atualizarImagem();
        painelPrincipal.setSize(imagem.getLinha(), imagem.getColuna());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new processadorimagem.ViewPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout painelPrincipalLayout = new javax.swing.GroupLayout(painelPrincipal);
        painelPrincipal.setLayout(painelPrincipalLayout);
        painelPrincipalLayout.setHorizontalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        painelPrincipalLayout.setVerticalGroup(
            painelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    
    public void atualizarImagem(){
        
        int[][] aux = imagem.getCurrentMatrix();
        
        painelPrincipal.setImage(new BufferedImage(imagem.getLinha(), imagem.getColuna(), BufferedImage.TYPE_INT_RGB));
        painelPrincipal.setSize(imagem.getColuna(), imagem.getLinha());
        painelPrincipal.fitSize();
        painelPrincipal.cleanImage();
        
        for(int i=0;i<imagem.getLinha();i++){
            for(int j=0;j<imagem.getColuna();j++){
                //int pixel = (this.imagem.getValorNaMatrizPGM(i, j));
                painelPrincipal.plot(j, i, new Color(aux[i][j], aux[i][j], aux[i][j]));
               //System.out.println(imagem.getValorNaMatrizPGM(i, j)+" ");
            }
            //System.out.println("");
        }
        this.painelPrincipal.repaint();
        this.painelPrincipal.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private processadorimagem.ViewPanel painelPrincipal;
    // End of variables declaration//GEN-END:variables
}