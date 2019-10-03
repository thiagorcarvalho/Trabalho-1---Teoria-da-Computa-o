/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhotcguloso;

/**
 *
 * @author thiago
 */
public class Pair implements Comparable<Pair> {
    public int id, valor, peso;
    
    public int compareTo(Pair x){
        return valor/peso - x.valor/x.peso;
    }
}
