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
    private String username;
    private TIPO tipo;
    private String password;

    public Utilizador() {
        this.username = "";
        this.tipo = NENHUM;
        this.password = "";

    }

    public Utilizador(String username, TIPO tipo, String password) {
        this.id = 0;
        this.username = username;
        this.tipo = tipo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilizador{" + "id=" + id + ", username=" + username + ", tipo=" + tipo + ", password=" + password + '}';
    }
}
