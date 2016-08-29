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
public class Elevator {

  public static void main(String[] args) {

    
    String nome = "C:\\Users\\Lucas.Nunes-PC\\Documents\\NetBeansProjects\\ProjetoSO\\build\\classes\\projetoso\\dadosDisco.txt";
    String linha = "";				//Variável auxiliar que captura a linha do arquivo;
    int nSetores = 0;				//Número de Setores do HD
    int pInicial = 0;				//Posição inicial do braço do HD
    int trilha = 0;					//Indica a trilha do HD
    int i = 0, j = 0, init = 0;
    int somatorio = 0;
    int auxTrilhaTemp = 0;			//Auxilia na troca de posições do array trilhasPercorridas
    
    List<Integer> trilhasPercorridas = new ArrayList<Integer>();
    List<Integer> difTrilhas = new ArrayList<Integer>();
    
    try {
    	//Lendo o arquivo dadosDisco.txt
    	FileReader arq = new FileReader(nome);
      	BufferedReader lerArq = new BufferedReader(arq);

	linha = lerArq.readLine();			// lê a primeira linha
	nSetores = Integer.parseInt(linha);	// Converte pra inteiro
	linha = lerArq.readLine();			// lê a segunda  linha
	pInicial = Integer.parseInt(linha); // Converte para inteiro
	
	System.out.print("Número de Setores do HD: ");
	System.out.println(nSetores);
	
	System.out.print("Posição inicial do braço do HD: ");
	System.out.println(pInicial);
	
	//Percorre todo resto do arquivo.
	linha = lerArq.readLine(); 		// lê da terceira até a última linha
	while (linha != null) {
            trilha = Integer.parseInt(linha);
	
            trilhasPercorridas.add(trilha);
		    
            linha = lerArq.readLine(); // lê da terceira até a última linha 
		}
	
		trilhasPercorridas.add(pInicial);
		
		/**
		 * 
		* Elevator
		* A   movimentação do braço de leitura é realizada em  apenas  uma  direção,  
		* até  que  a última trilha seja encontrada. Após  isso  a  movimentação  
		* passa  a  ser  no sentido contrário. (Por isso o nome: Elevador)
		* 
		*/
		
	for(i=0;i<(trilhasPercorridas.size());i++){
            for(j=i;j<(trilhasPercorridas.size());j++){
		if(trilhasPercorridas.get(i)>trilhasPercorridas.get(j)){
                    auxTrilhaTemp = trilhasPercorridas.get(i);
			trilhasPercorridas.set(i, trilhasPercorridas.get(j));
			trilhasPercorridas.set(j, auxTrilhaTemp);
			}
		}
	}
		
		
		/**
		 *  Encontrando o indice da posição inicial do braço.
		 */
	for(i=0;i<(trilhasPercorridas.size());i++){
            if(pInicial == trilhasPercorridas.get(i)){
		init = i;
		break;
			}
		}
	
		/**
		 * Serão dois (fors)
		 * um para percorrer as trilhas na direção decrescente em relação a posição inicial do braço
		 * e outro para percorrer as trilhas na direção crescente em relação a posição inicial do braço.
		 * 
		 * Perceba que ele só vai para as trilhas a  direita do braço, depois de percorrer todas as 
		 * trilhas a  esqueda, ou seja, comportamento similar ao de um elevador.
		 */
	System.out.println("\nTrilhas a Percorrer: ");
	j=0;
	for(i=init;i>=0;i--){		
            System.out.println("Movimento "+(j+1)+", trilha: "+trilhasPercorridas.get(i));	
		if(i>0){
                    difTrilhas.add(Math.abs(trilhasPercorridas.get(i) - trilhasPercorridas.get(i-1)));
			}
			j++;
		}		
		
		//A diferença entre a última trilha da esquerda e a primeira da direita será armazenda aqui.
                    difTrilhas.add(Math.abs(trilhasPercorridas.get(init+1) - trilhasPercorridas.get(0)));
		
	for(i=init+1;i<(trilhasPercorridas.size());i++){		
            System.out.println("Movimento "+(j+1)+", trilha: "+trilhasPercorridas.get(i));	
			
		if(i<(trilhasPercorridas.size()-1)){
			difTrilhas.add(Math.abs(trilhasPercorridas.get(i) - trilhasPercorridas.get(i+1)));
			}
			
			j++;
		}	
		
		System.out.println();
		
		//Exibindo as trilhas pecorridas em cada movimento do braço.
		//Faz o somatorio de todas as trilhas pecorridas pelo braço
	for(i = 0 ; i < difTrilhas.size(); i++){
            System.out.println("Trilhas Percorridas no Movimento "+(i+1)+": "+difTrilhas.get(i));
		somatorio += difTrilhas.get(i);
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

