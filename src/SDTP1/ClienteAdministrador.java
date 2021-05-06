package SDTP1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * A classe ClienteAdministrador implementa um programa que permite a gestão
 * (Registo, listagem e remoção) dos utilizadores do sistema, por isso possui
 * privilégios face aos restantes clientes.
 *
 * @author Adriana, Daniel e Fernando
 * @version 1.0
 */
public class ClienteAdministrador {

    //NOTA: ao invés de criar uma lista local, nós consultamos o objeto remoto cada vez que for necessário
    /**
     * Método local usado para escrever o menu deste cliente no stdout.
     *
     * @return Nada.
     */
    public static void printMenuUtilizador() {
        System.out.println("----------\nMENU--ADMINISTRADOR\n----------\n1-Registar\n2-Listar Utilizadores\n3-Remover Utilizador\n4-Sair\n----------");
    }

    /**
     * Este é o método principal que faz uso do método local dedinido em cima,
     * assim como implementa toda a lógica do programa.
     *
     * @param args não usado
     * @return Nada.
     * @exception Exception
     * @see Exception
     */
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            //Link para ligar em duas máquinas diferentes: https://stackoverflow.com/questions/35403765/java-rmi-for-remote-ip-host
            //String serverIP = "192.168.2.10";
            //Registry registry = LocateRegistry.getRegistry(serverIP, Constants.RMI_PORT);
            //Remote Method Invocation -- instâncias
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            //Máquinas diferentes
            //ServidorInterface s = (ServidorInterface) registry.lookup(Constants.RMI_ID);
            ClienteAdministrador c = new ClienteAdministrador();

            //Ficheiro 
            FileOutputStream fos = new FileOutputStream(new File("./SDTP1/Utilizadores.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            /*
            * Login
            * As credenciais de login são definidas como "admin" e "admin", por defeito.
             */
            boolean login = false;

            System.out.println("ADMINISTRADOR\n----------\nLOGIN\n----------\nUsername:");
            String username = Ler.umaString();
            System.out.println("Password:");
            String password = Ler.umaString();

            /*
             * As credenciais de administrador são fornecidas a um funcionário
             * com um cargo de chefia dentro da loja.
             */
            if (username.equals("admin") && password.equals("admin")) {
                login = true;
            } else {
                System.out.println("Credenciais inválidas!");
            }

            /*
            * Caso o logim seja bem sucedido, então prossegue-se para o menu, 
            * caso contrário, o programa fecha. O utilizador nunca pode errar o login, 
            * por motivos de segurança (para evitar ataques ao sistema).
             */
            if (login == true) {
                int op = 0, userId = 0;

                while (op != 4) {

                    printMenuUtilizador();
                    op = Ler.umInt();
                    switch (op) {

                        /*
                        * Registar utilizador: são pedidas todas as informações 
                        * de forma a preenchar os respetivos atributos da classe Utilizador.
                         */
                        case 1:
                            Utilizador u = new Utilizador();
                            u.setId(userId);
                            userId++;

                            System.out.println("Username: ");
                            u.setUsername(Ler.umaString());
                            System.out.println("Password: ");
                            u.setPassword(Ler.umaString());
                            System.out.println("Tipo de funcionário (1-FORNECEDOR 2-VENDEDOR)");
                            int opt = Ler.umInt();
                            if (opt == 1) {
                                u.setTipo(Utilizador.TIPO.FORNECEDOR);
                            }
                            if (opt == 2) {
                                u.setTipo(Utilizador.TIPO.VENDEDOR);
                            }

                            //Registar na lista do servidor (Remota)
                            s.registarUtilizador(u);

                            //Guardar no ficheiro a nova lista que contém o novo utilizador
                            oos.writeObject(s.consultarUtilizadores());
                            oos.flush();

                            break;

                        //Listar utilizadores   
                        case 2:
                            /*
                             * Para listar basta ir à lista de utilizadores do
                             * servidor que é constantemente atualizada sempre
                             * que é adicionado um elemento na execução atual
                             * (assim como adicionado ao ficheiro). Os elementos
                             * de execuções antigas foram lidos para o servidor
                             * no seu arranque.
                             */
                            System.out.println(s.consultarUtilizadores().toString());
                            break;

                        //Remover utilzador: é pedido username e criado
                        case 3:
                            System.out.println("Introduza o nome do utilizador: ");
                            String n = Ler.umaString();
                            /*O utilizador u é criado para ser passado como parâmetro 
                            * ao método remoto. Seria evitado se o método recebesse uma string como parâmetro.
                             */
                            Utilizador ut = new Utilizador();
                            ut.setUsername(n);
                            
                            /*for (int i = 0; i < s.consultarUtilizadores().size(); i++) {
                                if (s.consultarUtilizadores().get(i).getUsername().equals(n)) {
                                    s.consultarUtilizadores().remove(i);
                                }
                            }*/
                            //Remover da lista do servidor através da invocação do método remoto respetivo
                            if (s.removerUtilizador(ut)) {
                                System.out.println("Utilizador eliminado!");
                            } else {
                                System.out.println("Utilizador não existe na lista.");
                            }
                            /*
                            * Remover do ficheiro --> define o conteúdo do ficheiro 
                            * para a lista do servidor já sem o utilizador eliminado
                            */
                            oos.writeObject(s.consultarUtilizadores());
                            oos.flush();
                            break;

                        //Sair
                        case 4:
                            break;
                        /*
                        * Caso o utilizador não selecione nenhuma das opções acima, 
                        * então será a presentada a mensagem seguinte e terá de selecionar umas das opções válidas
                        */
                        default:
                            System.out.println("Opção inválida!");
                    }
                }
            }
            //Fechar as Streams dos ficheiros
            oos.close();
            fos.close();
        } catch (Exception ex) {
            System.out.println("ClientAdministrador exception: " + ex.getMessage());
        }
    }
}
