package SDTP1;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta classe serve o propósito de representar as operações de compra e venda de filmes.
 * @author Adriana, Daniel e Fernando
 * @version 1.0
 */

public class Transacao implements Serializable {
    private Filme filme;     
    private int quantidade;
    //Por implementar:
    //private ClienteVendedor vendedor; //no caso de se tratar de uma compra --> null
    //private ClienteFornecedor fornecedor; //no caso de se tratar de uma venda --> null
    private LocalDate dataNegocio;

    public Transacao(Filme filme, int quantidade,LocalDate data/*, ClienteVendedor vendedor, ClienteFornecedor*/) {
        this.filme = filme;
        this.quantidade = quantidade;
        this.dataNegocio=data;
        //this.vendedor = vendedor;
    }
    
    public Transacao() {
        this.filme = new Filme();
        this.quantidade = 0;
        this.dataNegocio=LocalDate.now();
        //this.vendedor = vendedor;
        //this.fornecedor = vendedor;
    }

    public Filme getFilme() {
        return filme;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public LocalDate getData() {
        return dataNegocio;
    }
    

    public void setFilmes(Filme filme) {
        this.filme = filme;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setData(LocalDate data) {
        this.dataNegocio = data;
    }

    
    @Override
    public String toString() {
        return "Venda{" + "filmes=" + filme + ", quantidade=" + quantidade + '}';
    }  
}