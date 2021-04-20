package SDTP1;

import static SDTP1.Utilizador.TIPO.NENHUM;
import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class Utilizador implements Serializable {
    enum TIPO {
        FORNECEDOR, VENDEDOR, NENHUM
    }
    private int id;
    private String nome;
    private TIPO tipo;
    private String password;

    public Utilizador() {
        this.nome = "";
        this.tipo = NENHUM;
        this.password = "";

    }

    public Utilizador(String nome, TIPO tipo, String password) {
        this.id = 0;
        this.nome = nome;
        this.tipo = tipo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilizador{" + "id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", password=" + password + '}';
    }
}
