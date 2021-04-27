package SDTP1;

/**
 *
 * @author Adriana, Daniel e Fernando
 */
import java.io.*;
import java.util.*;

public class Ficheiros {//utilizadores, vendas, compras

    public static void RegistarUtilizadores (ArrayList<Utilizador> lista_utilizadores) {

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Utilizadores.dat"));
            //escrever a lista no ficheiro             
            os.writeObject(lista_utilizadores);
            os.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<Utilizador> VerUtilizadores (ArrayList<Utilizador> lista_utilizadores) {
        //Ler e Devolver Array
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Utilizadores.dat"));

            lista_utilizadores = (ArrayList<Utilizador>) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lista_utilizadores;
    }

    public static void RegistarVenda (ArrayList<Vendas> lista_vendas) {

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Vendas.dat"));
            // escrever o objeto lista no ficheiro             
            os.writeObject(lista_vendas);
            os.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<Vendas> VerVendas (ArrayList<Vendas> lista_vendas) {
        //Ler e Devolver Array
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Vendas.dat"));

            lista_vendas = (ArrayList<Vendas>) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lista_vendas;
    }

    public static ArrayList<Compras> VerCompras (ArrayList<Compras> lista_compras) {
        //Ler e Devolver Array
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Compras.dat"));

            lista_compras = (ArrayList<Compras>) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lista_compras;
    }

    public static void RegistarCompras (ArrayList<Compras> lista_compras) {

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Compras.dat"));
            // escrever o objeto lista no ficheiro             
            os.writeObject(lista_compras);
            os.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}