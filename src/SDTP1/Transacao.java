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
    private ArrayList<Filme> filmes;
    private int quantidade;  
    //private ClienteVendedor vendedor; //no caso de se tratar de uma compra --> null
    //private ClienteFornecedor fornecedor; //no caso de se tratar de uma venda --> null

    public Transacao(ArrayList<Filme> filmes, int quantidade/*, ClienteVendedor vendedor, ClienteFornecedor*/) {
        this.filmes = filmes;
        this.quantidade = quantidade;
        //this.vendedor = vendedor;
    }
    
    public Transacao(/*, ClienteVendedor vendedor, ClienteFornecedor*/) {
        this.filmes = new ArrayList<Filme>();
        this.quantidade = 0;
        //this.vendedor = vendedor;
        //this.fornecedor = vendedor;
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Venda{" + "filmes=" + filmes + ", quantidade=" + quantidade + '}';
    }  
}
