package trabalhotcguloso;


import java.io.*;

import java.util.*;

public class TrabalhoTCGuloso {
    
    
    public static int custo(ArrayList<Integer> solucao, int pesoItem[], int lucroItem[], int capacidade){
        
        int valor = 0;
        int peso = 0;
        for(int i=0; i<solucao.size(); i++){
            if(solucao.get(i)==1){
                valor+= lucroItem[i];
                peso+= pesoItem[i];
            }
        }
        
        if(peso>capacidade) return -1;
        return valor;
    }
    
    public static int buscalocal(ArrayList<Integer> solucao, int pesoItem[], int lucroItem[], int capacidade){
        
        int valorAntigo = custo(solucao, pesoItem, lucroItem, capacidade);
        int valorAtual = valorAntigo;
        
        ArrayList<Integer> melhorSolucao = solucao;
      
        for(int i=0; i<solucao.size(); i++){
            
            if(solucao.get(i)==0){
                solucao.set(i, 1);
                int cv = custo(solucao, pesoItem, lucroItem, capacidade);
                if(cv > valorAtual){
                    valorAtual = cv;
                    melhorSolucao = solucao;
                }
                solucao.set(i, 0);
            }
        }
        
        if(valorAtual > valorAntigo) return buscalocal(melhorSolucao, pesoItem, lucroItem, capacidade);
        return valorAntigo;
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException{
        Scanner leitor = new Scanner(System.in);
        int a, k;
        
        a = leitor.nextInt();//quantidade de itens
        int pesoItem [] = new int [a];
        int lucroItem [] = new int [a];
        
       for(int i=0;i<a;i++){
          leitor.nextInt(); //id.
          lucroItem[i] = leitor.nextInt();
          pesoItem[i] = leitor.nextInt();
       }
       
       k = leitor.nextInt(); //capacidade da mochila
       
        Random random = new Random();
        int lucro = 0;
        
        ArrayList<Pair> razao = new ArrayList<Pair>();
        
        for(int i=0; i<a; i++){
            Pair x = new Pair();
            x.id = i;
            x.valor = lucroItem[i];
            x.peso = pesoItem[i];
            
            razao.add(x);
        }
        
         Collections.sort(razao, Collections.reverseOrder());
        
         int melhorSolucao = 0;
         ArrayList<Pair> rcopia = new ArrayList<Pair>();
         rcopia.addAll(razao);
         
         int capacidade = k;         
         
         /* Heuristica semi-gulosa */
         ArrayList<Integer> solucao = new ArrayList<Integer>(Collections.nCopies(razao.size(),0));
         razao.clear();
         razao.addAll(rcopia);
        
         k = capacidade;

        while(razao.size()>0){
            int i;
            if(razao.size()==1) i = random.nextInt(1);
            else i = random.nextInt(1);
           
            if(razao.get(i).peso <= k){
                solucao.set(razao.get(i).id, 1);
                k = k - razao.get(i).peso;
                lucro = lucro + razao.get(i).valor;
            }
            razao.remove(i);
        }
        
        razao.clear();
        razao.addAll(rcopia);
        int resposta = buscalocal(solucao, pesoItem, lucroItem, capacidade);
        if(resposta > melhorSolucao) melhorSolucao = resposta;
         
        System.out.println("Valor da melhor solucao: " + melhorSolucao);
    }
}