/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processadorimagem;

import java.io.FileNotFoundException;

/**
 *
 * @author SAMSUNG
 */
public class ProcessadorImagem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        
        ImagemPGM imagem1 = new ImagemPGM("imagem.pgm");
        
        imagem1.salvarImagem(imagem1.getCurrentMatrix(), "copia.pgm");
        
    }
    
}