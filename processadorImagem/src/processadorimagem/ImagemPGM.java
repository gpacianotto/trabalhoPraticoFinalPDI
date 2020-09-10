/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processadorimagem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author SAMSUNG
 */
public class ImagemPGM {
    
    private String endereco;
    private int linha;
    private int coluna;
    private int[][] matriz;
    private int[][] imagemOriginal;
    private int lim;
    private Scanner in;
    
    public ImagemPGM(String path) throws FileNotFoundException{
        
        endereco = path;
        System.out.println(endereco);
        in = new Scanner(new FileReader(endereco)); // cria um scanner para ler arquivos
        matriz = criarMatriz(in);
        
        imagemOriginal = new int[linha + 1][coluna + 1];
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                imagemOriginal[i][j] = matriz[i][j];
                
            }
        }
        
        
    }
    public int getLinha(){
        return linha;
    }
    public int getColuna(){
        return coluna;
    }
    
    public int getValorNaMatrizPGM(int i, int j)
    {
        return matriz[i][j];
    }
    public int getLimite(){
        return lim;
    }
    private int[][] criarMatriz(Scanner i){
        int[][] matrix;
        
        String lixo = i.nextLine();
        //System.out.println(lixo);
        lixo = i.nextLine();
        //System.out.println(lixo);
        
        coluna = i.nextInt();
        linha = i.nextInt();
        
        
        matrix = new int[linha + 1][coluna + 1];
        
        
        lim = i.nextInt();
        
        for(int x = 0; x<linha; x++){
            for(int y = 0; y<coluna; y++)
            {
                matrix[x][y] = i.nextInt();
            }
            
        }
        System.out.println("Colunas: "+coluna+" Linhas: "+linha);
        
        return matrix;
    }
    
    public void fatiamentoNormal(int valor1, int valor2, int valor){
        
        //System.out.println("Colunas: "+coluna+" Linhas: "+linha);
        
        for(int x = 0; x < linha; x++)
        {
            for(int y = 0; y < coluna; y++)
            {
                
                if((matriz[x][y] > valor1) && (matriz[x][y] < valor2))
                {
                    matriz[x][y] = valor;
                }
   
            }
        }
        
    }
    
    public int[][] getCurrentMatrix(){
        return matriz;
    }
    public int[][] getOriginalMatrix(){
        return imagemOriginal;
    }
    
    public void salvarImagem(int[][] imagem, String nameFile){
        
        
        
        try {
        FileWriter fw = new FileWriter(nameFile);
        fw.write("P2");
        fw.write("\n");
        fw.write(Integer.toString(linha));
        fw.write(" ");
        fw.write(Integer.toString(coluna));
        fw.write("\n");
        fw.write(Integer.toString(lim));
        fw.write("\n");
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                    fw.write(imagem[i][j] + " ");
            }
            //fw.write("\n");
        }
        fw.flush();
        } catch (IOException e) {}
    }
    
    public void chamarImagemOriginal(){
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                matriz[i][j] = imagemOriginal[i][j];
                
            }
        }
        
    }
    
    public void somaPorConstante(int constante){
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                if(matriz[i][j] + constante > lim)
                {
                    matriz[i][j] = 255;
                }
                else{
                    matriz[i][j] += constante;
                }
                
            }
        }
        
    }
    public void multiplicacaoPorConstante(int constante){
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                if(matriz[i][j] * constante > lim)
                {
                    matriz[i][j] = 255;
                }
                else{
                    matriz[i][j] = matriz[i][j] * constante;
                }
                
            }
        }
    }
}
