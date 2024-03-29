package SDTP1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Filme é a classe principal deste programa. As principais variáveis adicionadas, 
 * além das informações de cada filme, são a quantidade que regista o total de filmes existentes na base de dados.
 * @author Adriana, Daniel e Fernando
 * @version 1.0
 */
public class Filme implements Serializable {

    enum CATEGORIA {
        ACAO, TERROR, AVENTURA, COMEDIA
    }

    private int id;
    private String nome;
    private String categoria;
    private int quantidade;//quantidade total de vendidos e não vendidos
    private int vendidos;;//Esta variável indica o número total de exemplares vendidos.
    private int ano;
    private double precoCompra;
    private double precoVenda;
    private String pais;
    private String realizador;
    private int stockMin;//O stockMin é a variável responsável pelo callback.

    public Filme() {
        this.id = 0;
        this.nome = "";
        this.categoria = "";
        this.quantidade = 0;
        this.vendidos=0;
        this.ano = 0;
        this.precoCompra = 0.0;
        this.precoVenda = 0.0;
        this.pais = "";
        this.realizador = "";
        this.stockMin = 0;
    }

    public Filme(int id, String nome, String categoria, int quantidade, int vendidos, int ano, double precoCompra, double precoVenda, String pais, String realizador, int stockMin) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.vendidos= vendidos;
        this.ano = ano;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.pais = pais;
        this.realizador = realizador;
        this.stockMin = stockMin;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public int getVendidos(){
        return vendidos;
    }

    public int getAno() {
        return ano;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public String getPais() {
        return pais;
    }

    public String getRealizador() {
        return realizador;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setVendidos (int vendidos){
        this.vendidos = vendidos;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setRealizador(String realizador) {
        this.realizador = realizador;
    }
    
    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", ano=" + ano + ", precoCompra=" + precoCompra + ", precoVenda=" + precoVenda + ", pais=" + pais + ", realizador=" + realizador +", quantidade=" + quantidade +", stockMin=" + stockMin + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Filme other = (Filme) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ano != other.ano) {
            return false;
        }
        if (this.stockMin != other.stockMin) {
            return false;
        }
        if (this.precoCompra != other.precoCompra) {
            return false;
        } 
        if (this.precoVenda != other.precoVenda) {
            return false;
        } 
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        
        if (!Objects.equals(this.vendidos, other.vendidos)){
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.realizador, other.realizador)) {
            return false;
        }
        return true;
    }

    public Object clone() {
        Filme cloneFilme = new Filme(this.id, this.nome, this.categoria, this.quantidade, this.vendidos, this.ano, this.precoCompra, this.precoVenda, this.pais, this.realizador, this.stockMin);
        return cloneFilme;
    }
}
