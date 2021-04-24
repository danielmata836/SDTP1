package SDTP1;

import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class ClienteFornecedor {

    //Método local
    public static void printMenuFilme() {
        System.out.println("----------\nMENU\n----------\n1-Registar\n2-Listar\n3-Sair\n----------");
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            ClienteFornecedor c = new ClienteFornecedor();

            //TODO: login
            
            int op = 0;
            while (op != 3) {
                printMenuFilme();
                op = Ler.umInt();
                switch (op) {
                    case 1:
                        //Utilizador u = new Utilizador();
                        Filme filme = new Filme();
                        System.out.println("Introduza o nome: ");
                        filme.setNome(Ler.umaString());
                        System.out.println("Introduza a categoria: ");
                        filme.setCategoria(Ler.umaString());
                        s.registarFilme(filme);
                        break;
                    case 2:
                        ArrayList<Filme> received = s.consultarFilmes();
                        String st = "Lista de Filmes:\n";
                        for (int i = 0; i < received.size(); i++) {
                            st += received.get(i).toString() + "\n";
                        }
                        System.out.println(st);
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception ex) {
            System.out.println("ClienteFornecedor exception: " + ex.getMessage());
        }

    }
}
