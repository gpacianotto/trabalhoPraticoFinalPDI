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
public class ImagemPPM {
    
    
    private int[][][] matriz;
    private int[][][] imagemOriginal;
    private int linha;
    private int coluna;
    private int lim;
    private Scanner in;
    private String endereco;

    public ImagemPPM(String path) throws FileNotFoundException{
        endereco = path;
        System.out.println(endereco);
        in = new Scanner(new FileReader(endereco)); // cria um scanner para ler arquivos
        criaMatriz(in);
        
        imagemOriginal = new int[linha + 1][coluna + 1][3];
        
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                imagemOriginal[i][j][0] = matriz[i][j][0];
                imagemOriginal[i][j][1] = matriz[i][j][1];
                imagemOriginal[i][j][2] = matriz[i][j][2];
                
            }
        }
        
        
        
    }
    
    public void salvarImagem(int[][][] imagem, String nameFile){
        
        
        
        try {
        FileWriter fw = new FileWriter(nameFile);
        fw.write("P3");
        fw.write("\n");
        fw.write("# Created by Guilherme Pacianotto \n");
        fw.write(Integer.toString(coluna));
        fw.write(" ");
        fw.write(Integer.toString(linha));
        fw.write("\n");
        fw.write(Integer.toString(lim));
        fw.write("\n");
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                    fw.write(imagem[i][j][0] + " ");
                    fw.write(imagem[i][j][1] + " ");
                    fw.write(imagem[i][j][2] + " ");
            }
            //fw.write("\n");
        }
        fw.flush();
        } catch (IOException e) {}
    }
    
    public int[][][] getCurrentMatrix(){
        return matriz;
    }
    
    public void soVermelho(){
        
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                matriz[i][j][0] = imagemOriginal[i][j][0];
                matriz[i][j][1] = 0;
                matriz[i][j][2] = 0;
                
            }
        }
        
    }
    
    public void soVerde(){
        
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                matriz[i][j][0] = 0;
                matriz[i][j][1] = imagemOriginal[i][j][1];
                matriz[i][j][2] = 0;
                
            }
        }
        
    }
    
    public void soAzul(){
        
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                matriz[i][j][0] = 0;
                matriz[i][j][1] = 0;
                matriz[i][j][2] = imagemOriginal[i][j][2];
                
            }
        }
        
    }
    
    public void filtroExclusivoVermelho(){
        
        
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                if((matriz[i][j][0] > matriz[i][j][1]) && (matriz[i][j][0] > matriz[i][j][2]))
                {
                    if((matriz[i][j][0] + 10) > lim)
                    {
                        matriz[i][j][0] = lim;
                    }
                    else{
                        matriz[i][j][0] += 10;
                    }
                    
                }
                
                
            }
        }
        
    }
    
    public void filtroExclusivoVerde(){
        
        
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                if((matriz[i][j][1] > matriz[i][j][0]) && (matriz[i][j][1] > matriz[i][j][2]))
                {
                    if((matriz[i][j][1] + 10) > lim)
                    {
                        matriz[i][j][1] = lim;
                    }
                    else{
                        matriz[i][j][1] += 10;
                    }
                    
                }
                
                
            }
        }
        
    }
    public void filtroExclusivoAzul(){
        
        
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                if((matriz[i][j][2] > matriz[i][j][0]) && (matriz[i][j][2] > matriz[i][j][1]))
                {
                    if((matriz[i][j][2] + 10) > lim)
                    {
                        matriz[i][j][2] = lim;
                    }
                    else{
                        matriz[i][j][2] += 10;
                    }
                    
                }
                
                
            }
        }
        
    }
    
    
    
    public int getLinha(){
        return linha;
    }
    public int getColuna(){
        return coluna;
    }
    public void chamarImagemOriginal(){
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                matriz[i][j][0] = imagemOriginal[i][j][0];
                matriz[i][j][1] = imagemOriginal[i][j][1];
                matriz[i][j][2] = imagemOriginal[i][j][2];
                
            }
        }
    }
    
    public int[][][] getOriginalMatrix(){
        return imagemOriginal;
    }
    
    
    public void criaMatriz(Scanner i){
        
        String lixo = i.nextLine();
        //System.out.println(lixo);
        lixo = i.nextLine();
        //System.out.println(lixo);
        
        coluna = i.nextInt();
        linha = i.nextInt();
        
        
        matriz = new int[linha + 1][coluna + 1][3];
        
        
        lim = i.nextInt();
        
        for(int x = 0; x<linha; x++){
            for(int y = 0; y<coluna; y++)
            {
                matriz[x][y][0] = i.nextInt();
                matriz[x][y][1] = i.nextInt();
                matriz[x][y][2] = i.nextInt();
            }
            
        }
        System.out.println("Colunas: "+coluna+" Linhas: "+linha);
        
    }
    
}
