package SDTP1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Adriana, Daniel e Fernando
 */
public class Filme implements Serializable {

    private int id;
    private String nome;
    private String categoria;
    private int ano;
    private double preco;
    private String pais;
    private String realizador;
    private Date dataCompraForn;
    private Date dataVendaCliente;
    private int aval;
    private int stockMin;

    public Filme() {
        this.id = 0;
        this.nome = "";
        this.categoria = "";
        this.ano = 0;
        this.preco = 0.0;
        this.pais = "";
        this.realizador = "";
        this.dataCompraForn = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();;
        this.dataVendaCliente = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();
        this.aval = 0;
        this.stockMin = 0;
    }

    public Filme(int id, String nome, String categoria, int ano, double preco, String pais, String realizador, Date dataCompraForn, Date dataVendaCliente, int aval, int stockMin) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.ano = ano;
        this.preco = preco;
        this.pais = pais;
        this.realizador = realizador;
        this.dataCompraForn = dataCompraForn;
        this.dataVendaCliente = dataVendaCliente;
        this.aval = aval;
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

    public int getAno() {
        return ano;
    }

    public double getPreco() {
        return preco;
    }
    
    public String getPais() {
        return pais;
    }

    public String getRealizador() {
        return realizador;
    }

    public Date getDataCompraForn() {
        return dataCompraForn;
    }

    public Date getDataVendaCliente() {
        return dataVendaCliente;
    }

    public int getAval() {
        return aval;
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

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setRealizador(String realizador) {
        this.realizador = realizador;
    }

    public void setDataCompraForn(Date dataCompraForn) {
        this.dataCompraForn = dataCompraForn;
    }

    public void setDataVendaCliente(Date dataVendaCliente) {
        this.dataVendaCliente = dataVendaCliente;
    }

    public void setAval(int aval) {
        this.aval = aval;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", ano=" + ano + ", preco=" + preco + ", pais=" + pais + ", realizador=" + realizador + ", dataCompraForn=" + dataCompraForn + ", dataVendaCliente=" + dataVendaCliente + ", aval=" + aval + ", stockMin=" + stockMin + '}';
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
        if (this.aval != other.aval) {
            return false;
        }
        if (this.stockMin != other.stockMin) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
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
    
    public Object clone(){
        Filme cloneFilme = new Filme(this.id, this.nome, this.categoria, this.ano, this.preco, this.pais, this.realizador, this.dataCompraForn, this.dataVendaCliente, this.aval, this.stockMin);
        return cloneFilme;
      }
    
}
