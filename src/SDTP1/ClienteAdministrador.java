package SDTP1;

import static java.lang.System.exit;
import java.rmi.Naming;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class ClienteAdministrador {

    //Método local
    public static void printMenuUtilizador() {
        System.out.println("----------\nMENU\n----------\n1-Registar\n2-Listar Utilizadores\n3-Sair\n----------");
    }

    //Método remoto
    public void printOnClient(String s) throws java.rmi.RemoteException {
        System.out.println("Message from server: " + s);
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            ClienteAdministrador c = new ClienteAdministrador();

            boolean login = false;

            do {
                System.out.println("----------\nLOGIN----------\nUsername:");
                String username = Ler.umaString();
                System.out.println("Password:");
                String password = Ler.umaString();

                /**
                 * As credenciais de administrador são fornecidas a um
                 * utilizador com superpoderes sobre o sistema
                 */
                if (username.equals("admin") && password.equals("admin")) {
                    login = true;
                }else{
                    System.out.println("Credenciais inválidas!");
                }
                    
            } while (login == false);
            
            int op = 0, userId = 0;
            while (op != 3) {
                printMenuUtilizador();
                op = Ler.umInt();
                switch (op) {
                    case 1:
                        Utilizador u = new Utilizador();
                        u.setId(userId);
                        userId++;
                        System.out.println("Username: ");
                        u.setUsername(Ler.umaString());
                        System.out.println("Password: ");
                        u.setPassword(Ler.umaString());
                        s.registarUtilizador(u);
                        break;
                    case 2:
                        ArrayList<Utilizador> received = s.consultarUtilizadores();
                        String st = "Lista de Utilizadores:\n";
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
            System.out.println("ClientAdministrador exception: " + ex.getMessage());
        }
    }
}
