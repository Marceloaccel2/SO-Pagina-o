/*
  Projeto 2 SO - Aluno Marcelo Alves Gomes
  Entrada : primeiro número representa a quantidade de quadros de memória disponíveis na RAM 
            e os demais representam a sequência de referências às páginas, sempre um número por linha. 
 
  Saída   : número de faltas de páginas obtido com a utilização de cada um dos algoritmos.
 */

import java.util.*;

public class Algoritmos {
    private int num_quadros;
    private int num_erros;
    ArrayList<Integer> quadros;
    
    /*Construtor que vai pegar o num_quadros do main (1°linha da entrada = num_quadros)*/
    public Algoritmos(int num_quadros){
            this.num_quadros = num_quadros;
            num_erros = 0;
    }
    
    /*Algoritmo FIFO se baseia no primeira pagina que entra , é a primeira que sai */
    /*Parâmetro de entrada irá ser uma lista do tipo inteiro*/
    public void FIFO(List<Integer> list){
        /*Indice antigo*/
        int firstIn = 0;	
        num_erros = 0;

        quadros = new ArrayList<>();

        /*Pecorre toda a lista*/
        for(int i=0; i<list.size(); i++)
        {
            int num_pagina = list.get(i);

            //Checagem do num_pagina
            if(!quadros.contains(num_pagina))
            {
                /*Se estiver espaço livre , armazena normalmente uma nova pagina e incrementa o num_error*/
                if(quadros.size() < num_quadros)	
                {
                    quadros.add(num_pagina);
                    num_erros++;
                    continue;
                }
                /*Se estiver espaço cheio ,remove a mais antiga e substitui pela mais nova*/
                else	
                {
                    quadros.remove(firstIn);
                    quadros.add(firstIn, num_pagina);
                    firstIn++;
                    num_erros++;

                    /*Caso ele for armazenar uma nova pagina em um espaço cheio e essa nova pagina ja esteja armazenada */		
                    if(firstIn == num_quadros)
                    {
                        firstIn = 0;
                    }
                }
            }
        }
    /* Print de saída */
    System.out.println("FIFO " + num_erros);
        
    }
    /*Algoritmo Otimo(OTM) se baseia em qual vai demorar mais pra se repetir , substituindo-o por uma nova pagina*/    
    /*Parâmetro de entrada irá ser uma lista do tipo inteiro*/
    public void OTM(List<Integer> list){
        int base = 0;
        int max_index = 0;
        int quadro_index = 0;
        int tamanho = list.size();
        num_erros = 0;
        quadros = new ArrayList<>();
	
        for(int i=0; i<tamanho; i++)
        {
            int num_pagina = list.get(0);
            list.remove(base);
		
                /*Checagem do numero de pagina*/
            if(!quadros.contains(num_pagina))	
            {
                /*Se estiver espaço livre , armazena normalmente uma nova pagina e incrementa o num_error*/
                if(quadros.size() < num_quadros)
                {
                    quadros.add(num_pagina);
                    num_erros++;
                    continue;
                }
                    /*Já se caso nao exista espaco vazio , vai ver se a pagina já está na memoria , se nao estiver , vai procurar a pagina
                    que demorará mais para ser executada*/
				
                max_index = -1;
				
                for(int j=0; j<quadros.size(); j++)
                {
                    /*O método Java.util.LinkedList.indexOf (Object element) é usado para verificar e localizar a ocorrência de um 
                     determinado elemento na lista. Se o elemento estiver presente, o índice da primeira ocorrência do elemento será retornado
                     ,caso contrário -1 será retornado se a lista não contiver o elemento.*/
                        
                    if(list.indexOf(quadros.get(j)) == -1)
                    {
                        quadro_index = j;
                        break;
                    }
					
                    if(list.indexOf(quadros.get(j)) > max_index)
                    {
                        quadro_index = j;
                         max_index = list.indexOf(quadros.get(j));
                    }
                }
				
                quadros.set(quadro_index, num_pagina);
                num_erros++;
            }			
        }
        /*Print de saida*/
        System.out.println("OTM " + num_erros);
    }
    
    
    /*O algoritmo LRU funcionando substituindo a pagina menos recentemente usada por uma nova */
    /*Parâmetro de entrada irá ser uma lista do tipo inteiro*/
    /*Na logica , o menos recentemente usado vai ta mais para a base da pilha , e o mais recente , vai ta no topo da pilha*/
    public void LRU(List<Integer> list){
        
            /*Pilha vai iniciar vazia , mas seu tamanho maximo , vai ser o valor do num_quadros , ou seja
              ,seu tamMax sera o primeiro valor na tabela de entrada ja separada no MAIN*/
            ArrayList<Integer> pilha = new ArrayList<>(num_quadros);
            
            /*Variavel de controle*/
            boolean aux;
            num_erros = 0;
            quadros = new ArrayList<>();
            
            
            for(int i=0; i<list.size(); i++)
            {
		int num_pagina = list.get(i);
                aux = false;
                /*Busca se valor já está na pilha e atualiza*/
		
                /*No começo , pilha.size() possui tamanho 0 , logo nao vai entrar nesse for
                  Depois que todas as 4 primeiras paginas foram adicionadas , ele vai rodar
                  esse for , caso uma nova pagina que entrar for igual a que ja esta na pilha
                  , se isso acontecer , vai se atualizar*/
                for(int j=0; j<pilha.size(); j++)		
                {
                    if(pilha.get(j) == num_pagina)
                    {

			pilha.remove(j);
			pilha.add(num_pagina);
                        aux = true;
			break;
                    }
                }		
		
                /*Executa primeiro*/
		if(!aux)
		{
                /*Se estiver espaço livre , armazena normalmente uma nova pagina e incrementa o num_error*/
                    if(pilha.size() < num_quadros)								
                    {
			pilha.add(num_pagina);
			num_erros++;
                    }

                    /*Caso contrário, substitui quadro menos recente usado 
                      ,se a nova pagina que for entrar nao estiver na pilha(nao seja um valor igual),
                      ela vai cair nesse caso do else , vai ocorrer a substituiçao pela menos recentemente 
                      usada*/
                    else							
                        {
                            pilha.remove(0);
                            pilha.add(num_pagina);
                            num_erros++;
			}
		}
			
            }
            /*Print de saída*/
            System.out.println("LRU " + num_erros);
    }
    
}
