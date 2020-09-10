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
import java.util.ArrayList;
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
    private int[] histograma;
    private int lim;
    private Scanner in;
    
    public ImagemPGM(String path) throws FileNotFoundException{
        
        endereco = path;
        System.out.println(endereco);
        in = new Scanner(new FileReader(endereco)); // cria um scanner para ler arquivos
        matriz = criarMatriz(in);
        
        histograma = calcularHistograma();
        
        imagemOriginal = new int[linha + 1][coluna + 1];
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j < coluna; j++)
            {
                
                imagemOriginal[i][j] = matriz[i][j];
                
            }
        }
        
        
    }
    public int calculaDivisor(int[][] matriz){
        int divisor=0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                divisor += matriz[i][j];
            }
        }   
        return divisor;
    }
    
    public void filtragemEspacial(int tamanhoFiltro){ 
        
        int[][] matrizFiltro = gerarFiltro(tamanhoFiltro);
        
        int divisor = calculaDivisor(matrizFiltro); //Calc o divisor,somando os elementos da matriz filtro
        int j, i, novopx = 0;
        int novamatriz[][] = matriz; 
        int distancia = matrizFiltro.length / 2; //distancia do centro da matriz ate a borda
        int aux; //deixa a distancia negativa para posicionar a leitura no lugar correto
        int aux1, aux2;
        aux1 = aux2 = 0;   
        int vetorpx[] = new int[linha*coluna];	
        //percorrendo a matriz original
        for (i = distancia; i < linha-distancia; i++){ 
            for(j = distancia; j < coluna-distancia; j++){
                aux = -distancia; 
                aux1 = aux;
                for(int x = 0; x < (distancia*2) + 1; x++){
                    aux2 = aux;
                    for(int y = 0; y < (distancia * 2) + 1; y++){
                      novopx += (matriz[i + aux1][j + aux2] * matrizFiltro[x][y]);
                      aux2++; 
                    }
                    aux1++;
                }
                novamatriz[i][j] = novopx/divisor;
                novopx=0;
            }
        }
        int k = 0;
        for (i = 0; i < linha; i++){
            for(j=0; j < coluna; j++){
                vetorpx[k] = novamatriz[i][j];
                k++;
            }
        }
        
        
        matriz = convertArraytoMatriz(vetorpx);
        
    }
    
    public void filtroMediana(){
        int [][] mat = new int[linha][coluna];
        for(int i=0;i<linha;i++){
            for(int j=0;j<coluna;j++){
                matriz[i][j] = pixelMediano(i, j);
            }
        }
        
    }
    
    public boolean posiValida(int [][] mat, int i, int j){
        try{
            mat[i][j] = mat[i][j];
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
    public int pixelMediano(int I, int J){
        ArrayList<Integer> vetor = new ArrayList<Integer>();
        for(int i=I-1;i<I+2;i++){
            for(int j=J-1;j<J+2;j++){
                if(posiValida(getCurrentMatrix(), i, j)){
                    vetor.add(getValorNaMatrizPGM(i, j));
                }
            }
        }
        vetor.sort(null);
        return vetor.get(vetor.size()/2);
    }
    
    public int[][] convertArraytoMatriz(int [] arranjo){
        int[][] matriX = new int[linha][coluna];
        
        int k = 0;
        
        for(int i = 0; i<linha; i++)
        {
            for(int j = 0; j<coluna; j++)
            {
                matriX[i][j] = arranjo[k];
                k++;
            }
        }
        
        return matriX;
    }
    
    public int[][] gerarFiltro(int dim){
        int matriz[][] = new int[dim][dim];
             for(int i=0;i < dim;i++){
                for(int j = 0; j < dim; j++){
                    matriz[i][j] = 1;
                }
            }
        return matriz;
    }
    public int[] calcularHistograma() {
        
        
        int[] histograma1 = new int[lim + 1];
        

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                
                histograma1[matriz[i][j]] += 1;
            }
        }

        return histograma1;
    }
    
    public void equalizarHistograma() {
        
        int mn = linha * coluna;
        float[] pr = new float[lim + 1];
        int[] sk = new int[lim + 1];

        for (int i = 0; i < pr.length; i++) {
            pr[i] = ((float) histograma[i]) / mn;
        }
        
        float acumulado = 0.0f;
        for (int i = 0; i < sk.length; i++) {
            acumulado += pr[i];
            sk[i] = (int) Math.round(lim * acumulado);
        }

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                matriz[i][j] = sk[matriz[i][j]];
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
    
    public void fatiamentoBinario(int valor1, int valor2, int valorintervalo, int valorFora){
        
        //System.out.println("Colunas: "+coluna+" Linhas: "+linha);
        
        for(int x = 0; x < linha; x++)
        {
            for(int y = 0; y < coluna; y++)
            {
                
                if((matriz[x][y] > valor1) && (matriz[x][y] < valor2))
                {
                    matriz[x][y] = valorintervalo;
                }
                else{
                    matriz[x][y] = valorFora;
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
