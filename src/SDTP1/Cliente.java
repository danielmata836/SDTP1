package SDTP1;

import java.rmi.Naming;
import java.util.ArrayList;
/**
 *
 * @author Daniel
 */
public class Cliente {

    //Método local
    public static void printMenuFilme() {
        System.out.println("----------\nMENU\n----------\n1-Registar\n2-Listar\n3-Sair\n----------");
    }
    
    //Método local
    public static void printMenuUtilizador() {
        System.out.println("----------\nMENU\n----------\n1-Entrar\n2-Registar\n3-Sair\n----------");
    }
    
    //Método remoto
    public void printOnClient(String s) throws java.rmi.RemoteException {
        System.out.println("Message from server: " + s);
    }
    
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            Cliente c = new Cliente();
            
            int op1=0, op2=0, userId=0;
            boolean loginOk = false;
            while(op1!=3 && op2!=3){
                printMenuUtilizador();
                op1 = Ler.umInt();
                switch(op1){
                    case 1:
                        Utilizador u = new Utilizador();
                        u.setId(userId);
                        userId++;
                        System.out.println("Username: ");
                        u.setUsername(Ler.umaString());
                        System.out.println("Password: ");
                        u.setPassword(Ler.umaString());
                        //TODO: método remoto de registo no servidor
                        break;
                    case 2:
                        //TODO: método remoto de verificação de login
                        break;
                    case 3:
                        break;
                }
                printMenuFilme();
                op2 = Ler.umInt();
                switch(op2){
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
                }
            }
            
        } catch (Exception ex) {
            System.out.println("Client exception: " + ex.getMessage());
        }
    }
}