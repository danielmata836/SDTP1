package SDTP1;

import static SDTP1.Utilizador.TIPO.NENHUM;
import java.io.Serializable;

/**
 * A classe Utilizador implementa uma classe que representa um funcionário da 
 * loja no sistema. Um objeto desta classe permite gerir as permissões e o sistema de login.
 * @author Adriana, Daniel e Fernando
 * @version 1.0
 */
public class Utilizador implements Serializable {
    
    /*
    * O tipo de Utilizador permite fazer a gestão de permissões, na medida em que,
    * um utilizador só pode aceder ao processo cliente que corresponde à sua categoria.
    */
    enum TIPO {
        FORNECEDOR, VENDEDOR, NENHUM
    }
    private int id;
    private String username;
    private TIPO tipo;
    private String password;

    //Construtores
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

    //Getters
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

    //Setters
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
