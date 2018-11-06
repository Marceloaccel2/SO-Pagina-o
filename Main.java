/*
  Projeto 2 SO - Aluno Marcelo Alves Gomes
  Entrada : primeiro número representa a quantidade de quadros de memória disponíveis na RAM 
            e os demais representam a sequência de referências às páginas, sempre um número por linha. 
 
  Saída   : número de faltas de páginas obtido com a utilização de cada um dos algoritmos.
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        /*Tratar possiveis errors , como NZEC ERROR */
        try{
            int num_quadros = 0;
            /*Abre o arquivo de paginas*/
            FileReader arq = new FileReader("src/dados.txt");
            Scanner dados = new Scanner(arq);
            
            /*Cria-se 2 listas diferentes para nao ocorrer sobreposicao de dados*/    
            ArrayList<Integer> listaInputs = new ArrayList<>();
            ArrayList<Integer> listaInputs_OTM = new ArrayList<>();
		
            /*Vai ler o primeiro valor , e esse valor será o numero de quadros na memoria*/
            if(dados.hasNext())
            {
                num_quadros = Integer.valueOf(dados.next()); 
            }
            /*Depois da primeira linha(que sera o numero de quadros) ,os proximos dados irao ser adicionados em na listaInput criada*/
            while(dados.hasNext())
            {			
                listaInputs.add(Integer.valueOf(dados.next()));
            }
            /*Esse for vai percorrer o tamanho da listaInputs , copiando os elementos da listaInput para a ListaInput_OTM 
             , excluindo consequentemente o primeiro dado que seria o numeroDeQuadros(tamanho da memoria para adicionar as paginas)*/
            for(int i=0; i<listaInputs.size(); i++)
            {
		listaInputs_OTM.add(listaInputs.get(i));
            }
            /*Cria um algoritmo que vai herdar todas as funcoes da classe Algrotimos , sendo assim , colocando como entrada os Inputs predefinidos
            e chamando sua funçao para que gere a saida pedida no projeto*/
            Algoritmos algoritmo = new Algoritmos(num_quadros);
	
            algoritmo.FIFO(listaInputs);
            algoritmo.OTM(listaInputs_OTM);
            algoritmo.LRU(listaInputs);
            
            }catch(Exception e){
                System.out.println(e.getMessage());
            }   
    }	
}
