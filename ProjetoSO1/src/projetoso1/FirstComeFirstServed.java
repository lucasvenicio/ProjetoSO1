/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas e Douglas
 */
public class FirstComeFirstServed {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Lendo o arquivo dadosDisco.txt
    String nome = "C:\\Users\\Lucas.Nunes-PC\\Documents\\NetBeansProjects\\ProjetoSO\\build\\classes\\projetoso\\dadosDisco.txt";	//Nome do arquivo que seria lido.
    String linha = "";			//Variável auxiliar que captura a linha do arquivo;
    int nSetores = 0;			//Número de Setores do HD
    int pInicial = 0;			//Posição inicial do braço do HD
    int trilha = 0;			//Indica a trilha do HD
    int i = 1;
    int somatorio = 0;
    int difTrilhas = 0;
    List<Integer> trilhasPercorridas = new ArrayList<Integer>(); //Array que armazenará as trilhas

    try {
	FileReader arq = new FileReader(nome);
      	BufferedReader lerArq = new BufferedReader(arq);

	linha = lerArq.readLine();		// lê a primeira linha
      	nSetores = Integer.parseInt(linha);	// Converte pra inteiro
	linha = lerArq.readLine();		// lê a segunda  linha
	pInicial = Integer.parseInt(linha); 	// Converte para inteiro

	System.out.print("Número de Setores do HD: ");
	System.out.println(nSetores);

	System.out.print("Posição inicial do braço do HD: ");
	System.out.println(pInicial);


	System.out.println("\nTrilhas a Percorrer: ");
	//Percorre todo resto do arquivo.
	linha = lerArq.readLine(); // lê da terceira até a úlltima linha
	while (linha != null) {
            trilha = Integer.parseInt(linha);

	    trilhasPercorridas.add(trilha);

            System.out.print("Movimento "+(i++)+", trilha: ");
            System.out.println(trilha);

	    linha = lerArq.readLine(); // lê da terceira até a última linha 

	}

	System.out.println();

	//Fazendo o somatório de trilhas percorridas pelo braço do HD
	somatorio = Math.abs(trilhasPercorridas.get(0) - pInicial);

	System.out.println("Trilhas Percorridas no Movimento 1: "+somatorio);

	for(i = 1 ; i < trilhasPercorridas.size(); i++){

	    difTrilhas = Math.abs(trilhasPercorridas.get(i) - trilhasPercorridas.get(i-1));

	    System.out.println("Trilhas Percorridas no Movimento "+(i+1)+": "+difTrilhas);

	    somatorio += difTrilhas;
	}

	System.out.println();

	System.out.println("O total de trilhas Percorridas foi: "+somatorio);

	//Fecha o arquivo...
      	arq.close();
    } catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
    }
 
    System.out.println();
  }
}
    
