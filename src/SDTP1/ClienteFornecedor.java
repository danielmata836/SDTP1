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
    
    //Método local
    public static boolean verificarLogin(ArrayList<Utilizador> utilizadores, String username, String password){
        for(int i=0; i<utilizadores.size();i++){
            if(utilizadores.get(i).getTipo().equals(Utilizador.TIPO.FORNECEDOR) && utilizadores.get(i).getUsername().equals(username) && utilizadores.get(i).getPassword().equals(password)){
                 return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            ClienteFornecedor c = new ClienteFornecedor();

            ArrayList<Utilizador> utilizadores = s.consultarUtilizadores();
            boolean login = false;
            
            if(utilizadores.size()==0)
            
            do {
                System.out.println("----------\nLOGIN----------\nUsername:");
                String username = Ler.umaString();
                System.out.println("Password:");
                String password = Ler.umaString();
                             
                login = verificarLogin(utilizadores, username, password);
                    
            } while (login == false);
            
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
