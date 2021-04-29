package SDTP1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import static SDTP1.Filme.CATEGORIA.*;

/**
 *
 * @author Adriana, Daniel e Fernando
 */
public class Filme implements Serializable {

    //TODO: corrigir categorias
    enum CATEGORIA {
        DEFAULT, ACAO, ROMANCE, MISTERIO, AVENTURA, FANTASIA, FICCAOCIENTIFICA, COMEDIA, DRAMA 
    }

    private int id;
    private String nome;
    private CATEGORIA categoria;
    private int quantidade;
    private int ano;
    private double precoCompra;
    private double precoVenda;
    private String pais;
    private String realizador;
    private LocalDate dataCompraForn;
    private LocalDate dataVendaCliente;
    private int stockMin;

    public Filme() {
        this.id = 0;
        this.nome = "";
        this.categoria = DEFAULT;
        this.quantidade = 0;
        this.ano = 0;
        this.precoCompra = 0.0;
        this.precoVenda = 0.0;
        this.pais = "";
        this.realizador = "";
        this.dataCompraForn = LocalDate.now();
        this.dataVendaCliente = LocalDate.now();
        this.stockMin = 0;
    }

    public Filme(int id, String nome, CATEGORIA categoria, int quantidade, int ano, double precoCompra, double precoVenda, String pais, String realizador,  LocalDate dataCompraForn,  LocalDate dataVendaCliente, int stockMin) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.ano = ano;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.pais = pais;
        this.realizador = realizador;
        this.dataCompraForn = dataCompraForn;
        this.dataVendaCliente = dataVendaCliente;
        this.stockMin = stockMin;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CATEGORIA getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
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

    public LocalDate getDataCompraForn() {
        return dataCompraForn;
    }

    public  LocalDate getDataVendaCliente() {
        return dataVendaCliente;
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

    public void setCategoria(CATEGORIA categoria) {
        this.categoria = categoria;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    public void setDataCompraForn( LocalDate dataCompraForn) {
        this.dataCompraForn = dataCompraForn;
    }

    public void setDataVendaCliente( LocalDate dataVendaCliente) {
        this.dataVendaCliente = dataVendaCliente;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    @Override
    public String toString() {
        return "Filme{" + "id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", ano=" + ano + ", precoCompra=" + precoCompra + ", precoVenda=" + precoVenda + ", pais=" + pais + ", realizador=" + realizador + ", dataCompraForn=" + dataCompraForn + ", dataVendaCliente=" + dataVendaCliente + ", stockMin=" + stockMin + '}';
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
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.realizador, other.realizador)) {
            return false;
        }
        if (!Objects.equals(this.dataCompraForn, other.dataCompraForn)) {
            return false;
        }
        if (!Objects.equals(this.dataVendaCliente, other.dataVendaCliente)) {
            return false;
        }
        return true;
    }

    public Object clone() {
        Filme cloneFilme = new Filme(this.id, this.nome, this.categoria, this.quantidade, this.ano, this.precoCompra, this.precoVenda, this.pais, this.realizador, this.dataCompraForn, this.dataVendaCliente, this.stockMin);
        return cloneFilme;
    }
}
