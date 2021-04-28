package SDTP1;

import java.util.ArrayList;

/**
 *
 * @author Adriana, Daniel e Fernando
 */

/**
 * Esta classe serve o propósito de representar as operações de compra e venda de filmes
 * 
 */
public class Transacao {
    //À posição 0 do ArrayList filmes corresponde a quantidade na posição 0 da ArrayList quantidade;
    private ArrayList<Filme> filmes;      //[0]Matrix | Shrek | ...
    private ArrayList<Integer> quantidade;//[0]  1    |   5   | ..
    //private ClienteVendedor vendedor; //no caso de se tratar de uma compra --> null
    //private ClienteFornecedor fornecedor; //no caso de se tratar de uma venda --> null

    public Transacao(ArrayList<Filme> filmes, ArrayList<Integer> quantidade/*, ClienteVendedor vendedor, ClienteFornecedor*/) {
        this.filmes = filmes;
        this.quantidade = quantidade;
        //this.vendedor = vendedor;
    }
    
    public Transacao() {
        this.filmes = new ArrayList<Filme>();
        this.quantidade = new  ArrayList<Integer>();
        //this.vendedor = vendedor;
        //this.fornecedor = vendedor;
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    public ArrayList<Integer> getQuantidade() {
        return quantidade;
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    public void setQuantidade(ArrayList<Integer> quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Venda{" + "filmes=" + filmes + ", quantidade=" + quantidade + '}';
    }  
}
