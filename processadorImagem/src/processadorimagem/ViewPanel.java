/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processadorimagem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Matheus Prachedes Batista & Eymar Ferrario de Lima
 */
public class ViewPanel extends JPanel{
    private BufferedImage canvas;
    
    public void setImage(BufferedImage novaImagem){
        canvas = novaImagem;
        this.setPreferredSize(new Dimension(canvas.getWidth(),canvas.getHeight()));
    }
    
    public BufferedImage getCanvas(){
        return canvas;
    }
    
    public ViewPanel() {
        super();
        this.setSize(this.getPreferredSize());
        this.setBackground(Color.gray);
        canvas = new BufferedImage(800,600, BufferedImage.TYPE_INT_RGB);
        Graphics g = canvas.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(canvas, 0, 0, null);
    }

    public void fitSize(){
        if(!this.getSize().equals(new Dimension(canvas.getWidth(),canvas.getHeight()))){
            canvas = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = canvas.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }
    
    
    
    
    public void plot(int i,int j, Color cor){
        try{
            canvas.setRGB(i, j, cor.getRGB());
        }catch(Exception ex){
        }
    }

    public Color getPixel(int x,int y){
        try{
            return new Color(canvas.getRGB(x, y));
        }catch( Exception ex){
            
        }
        return null;
    }
    
    public void cleanImage() {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    } 
}
