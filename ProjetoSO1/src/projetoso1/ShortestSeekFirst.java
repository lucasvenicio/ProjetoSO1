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
public class ShortestSeekFirst {
    public static void main(String[] args) {

    String nome = "C:\\Users\\Lucas.Nunes-PC\\Documents\\NetBeansProjects\\ProjetoSO\\build\\classes\\projetoso\\dadosDisco.txt"; 
    String linha = "";					//Variável auxiliar que captura a linha do arquivo;
    int nSetores = 0;					//Número de Setores do HD
    int pInicial = 0;					//Posição inicial do braço do HD
    int trilha = 0;						//Indica a trilha do HD
    int i = 0, j = 0, somatorio = 0;
    int difTrilhas = 0;
    List<Integer> trilhasPercorridas = new ArrayList<Integer>();	//Array que armazenará as trilhas    

    try {
        //Lendo o arquivo dadosDisco.txt
		FileReader arq = new FileReader(nome);
		BufferedReader lerArq = new BufferedReader(arq);

		linha = lerArq.readLine();				// lê a primeira linha
		nSetores = Integer.parseInt(linha);		// Converte pra inteiro
		linha = lerArq.readLine();				// lê a segunda  linha
		pInicial = Integer.parseInt(linha); 	// Converte para inteiro

		System.out.print("Número de Setores do HD: ");
		System.out.println(nSetores);

		System.out.print("Posição inicial do braço do HD: ");
		System.out.println(pInicial);
		trilhasPercorridas.add(pInicial);

		//Percorre todo resto do arquivo.
		linha = lerArq.readLine(); // lê da terceira até a última linha

		while (linha != null) {
		    trilha = Integer.parseInt(linha);
		    trilhasPercorridas.add(trilha);
		    linha = lerArq.readLine();
		}


		//Neste caso, o braço do HD irá para a posição mais proxima da atual e não para proxima posição da lista.
		//Criaremos variáveis chamadas menorDistancia, trilhaAuxTemp, trilhaAuxTemp2 e positionAux que por sua vez
		//são variáveis auxiliares que nos ajudaram a organizar o array de trilhas (trilhasPercorridas) de forma que
		//de uma posição para outra do array, teremos a menor distancia possível.

		int menorDistancia = 100000;	//Colocamos um valor exorbitante(100000) para que seja garantida a primeira entrada no if.
		int trilhaAuxTemp = 0, trilhaAuxTemp2 = 0;
		int positionAux = 0;
		
		for(i = 0 ; i < (trilhasPercorridas.size()-1) ; i++){
			
			//Este (for) irá rocurar a posição mais proxima da atual.
		    for(j = (i+1) ; j < trilhasPercorridas.size() ; j++){
				if(Math.abs(trilhasPercorridas.get(i)-trilhasPercorridas.get(j)) < menorDistancia){
				    trilhaAuxTemp = trilhasPercorridas.get(j);
				    positionAux = j;
				    menorDistancia = Math.abs(trilhasPercorridas.get(i)-trilhasPercorridas.get(j));
				}
		    }

		    //Aqui fazemos uma troca de posições do array de trilhas
		    trilhaAuxTemp2 = trilhasPercorridas.get(i+1);
		    trilhasPercorridas.set(i+1,trilhaAuxTemp);
		    trilhasPercorridas.set(positionAux, trilhaAuxTemp2);

		    menorDistancia = 100000;	//Colocamos um valor exorbitante(100000) para que seja garantida a primeira entrada no if.
		}

		//Imprimindo a sequencia de movimentos que será feita pelo braço
		System.out.println("\nTrilhas a Percorrer: ");
	    for(i = 0 ; i < trilhasPercorridas.size(); i++){
	        System.out.println("Movimento "+(i+1)+": "+trilhasPercorridas.get(i));
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

