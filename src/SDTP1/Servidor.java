package SDTP1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * A classe Servidor faz as ligações em sistemas distribuídos entre os vários
 * tipos de clientes: Fornecedor, Administrador e Vendedor. Serve para que todos
 * os utilizadores possam ter acesso a variáveis globais do tipo Transacao,
 * Filme ou Utilizador.
 *
 * @author Daniel, Fernando e Adriana
 * @version 1.0
 */
public class Servidor extends java.rmi.server.UnicastRemoteObject implements ServidorInterface {

    //lista de clientes inscritos
    private static ClienteInterface client;

    /*
    * lista de filmes (PRINCIPAL) -- possui os filmes registados na loja e a sua
    * respetiva quantidade, assim como a quantidade de uniodades vendidas
     */
    private static ArrayList<Filme> filmes = new ArrayList<Filme>();

    //lista de vendas
    private static ArrayList<Transacao> vendas = new ArrayList<Transacao>();

    //lista de compras
    private static ArrayList<Transacao> compras = new ArrayList<Transacao>();

    //lista de utilizadores
    private static ArrayList<Utilizador> utilizadores = new ArrayList<Utilizador>();

    public Servidor() throws java.rmi.RemoteException {
        super();
    }

    //Método remoto
    public void printOnServer(String s) throws java.rmi.RemoteException {
        System.out.println(" AVISO : " + s);
    }

    @Override
    public void subscribe(String nomeCliente, ClienteInterface cliente) throws RemoteException {
        System.out.println("Subscribing: " + nomeCliente);
        client = cliente;
    }

    //Método remoto para registar um Filme 
    @Override
    public synchronized void registarFilme(Filme f) throws RemoteException {
        filmes.add(f);
    }

    //Método remoto para consultar a lista remota de filmes
    @Override
    public ArrayList<Filme> consultarFilmes() throws RemoteException {
        return filmes;
    }

    //---Métodos remotas para Utilizador---
    //Método remoto para login
    @Override
    public boolean login(String username, String password) throws RemoteException {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getUsername().equals(username)
                    && utilizadores.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    //Método remoto para registo de utilizador
    @Override
    public synchronized void registarUtilizador(Utilizador u) throws RemoteException {
        utilizadores.add(u);
    }

    //Método remoto para consulta da lista de utilizador
    @Override
    public ArrayList<Utilizador> consultarUtilizadores() throws RemoteException {
        return utilizadores;
    }

    //Método remoto para remover um Utilizador dqa lista 
    @Override
    public synchronized boolean removerUtilizador(Utilizador utilizador) throws RemoteException {
        String name;
        for (int i = 0; i < utilizadores.size(); i++) {
            name = utilizadores.get(i).getUsername();
            if (utilizador.equals(name)) {
                utilizadores.remove(utilizadores.get(i));
                return true;
            }
        }
        return false;
    }

    //Método remoto para consultar a lista de compras do servidor
    @Override
    public ArrayList<Transacao> consultarCompras() throws RemoteException {
        return compras;
    }

    //Método remoto para consultar a lista de vendas do servidor
    @Override
    public ArrayList<Transacao> consultarVendas() throws RemoteException {
        return vendas;
    }

    //Método remoto para incrementar o stock de um determinado filme passado por parâmetro
    @Override
    public synchronized void adicionarStock(Filme filme, Integer num) throws RemoteException {
        int stock = 0, q = 0;
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equals(filme.getNome())) {
                q = filmes.get(i).getQuantidade();
                filmes.get(i).setQuantidade(q + (int) num);
            }
        }
    }
    
    public double produtoCaro()throws RemoteException{
        double p  =0;
      
        for (int i = 0 ; i < filmes.size(); i++){
           
            if (filmes.get(i).getPrecoVenda() > p){
                p = filmes.get(i).getPrecoVenda();
                
            }
        }
        return p;
    }
    
    //Método remoto para incrementar o número de unidades vendidas de um determinado filme
    @Override
    public synchronized void adicionarVendidos(Filme filme, Integer num) throws RemoteException {
        int q = 0;
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equals(filme.getNome())) {
                q = filmes.get(i).getVendidos();
                filmes.get(i).setVendidos(q + (int) num);
            }
        }
    }

    //Método remoto para eliminar um filme da lista principal de filmes
    @Override
    public synchronized boolean eliminarFilme(Filme filme) throws RemoteException {
        String name;
        for (int i = 0; i < filmes.size(); i++) {
            name = filmes.get(i).getNome();
            if (filme.getNome().equals(name)) {
                filmes.remove(filmes.get(i));
                return true;
            }
        }
        return false;
    }

    /*
    * Método remoto para registo de uma compra na lista remota -- adicionar um 
    * elemento do tipo transação à lista de compras
     */
    @Override
    public synchronized void registarCompra(Transacao t) throws RemoteException {
        compras.add(t);
    }

    //Método remoto para registo de uma venda na lista remota
    @Override
    public synchronized void registarVenda(Transacao t) throws RemoteException {
        vendas.add(t);
    }

    //Método local para leitura da informação dos ficheiros
    /*public static void lerDoFicheiro(String nomeDoFicheiro) {
        try {
            FileInputStream finU = new FileInputStream(new File(nomeDoFicheiro));
            ObjectInputStream oisU = new ObjectInputStream(finU);
            
            utilizadores = (ArrayList<Utilizador>) oisU.readObject();
            //imprime o conteúdo do servidor para debugging
            System.out.println(utilizadores.toString());
            
            finU.close();  
            oisU.close();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public static void main(String[] args) throws ClassNotFoundException {

        System.setSecurityManager(new SecurityManager());

        try {
            java.rmi.registry.LocateRegistry.createRegistry(Constants.RMI_PORT);
        } catch (RemoteException ex) {
            System.out.println("Exception starting RMI registry:");
            System.out.println(ex.getMessage());
        }
        System.out.println("SERVIDOR\nRMI registry ready.");

        /*
             * As credenciais de administrador são fornecidas a um funcionário
             * com um cargo de chefia dentro da loja.
         */
        boolean login = false;
        System.out.println("\n----------\nLOGIN\n----------\nUsername:");
        String username = Ler.umaString();
        System.out.println("Password:");
        String password = Ler.umaString();

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
            try {
                    //Ficheiros
                    //Imprimir os ficheiros da diretoria onde estão os ficheiros que guradam os dados 
                    //(apenas para debugging)
                    /*File file = new File("./SDTP1");

                for (String fileNames : file.list()) {
                    System.out.println(fileNames);
                }
                     */
                /*
                 * MUITO IMPORTANTE: 
                 * SE der erro:null então significa que o
                 * ficheiro Utilizadores.txt, etc não foi criado e isso obriga a
                 * executar primeiro o cliente respetivo e adicionar um ovjeto
                 */
                
                //Leituta do ficheiro no arranque do servidor
/*
                //Utilizador--------------------------------------------------------
                FileInputStream finU = new FileInputStream(new File("./SDTP1/Utilizadores.txt"));
                ObjectInputStream oisU = new ObjectInputStream(finU);

                utilizadores = (ArrayList<Utilizador>) oisU.readObject();
                //imprime o conteúdo do servidor para debugging
                System.out.println(utilizadores.toString());

                finU.close();
                oisU.close();

                
                //Filmes----------------------------------------------------------
                FileInputStream finF = new FileInputStream(new File("./SDTP1/Filmes.txt"));
                ObjectInputStream oisF = new ObjectInputStream(finF);

                filmes = (ArrayList<Filme>) oisF.readObject();
                System.out.println("Aqui");
                //imprime o conteúdo do servidor para debugging
                System.out.println(filmes.toString());
                
                finF.close();
                oisF.close();
                
                
                //Compras-----------------------------------------------------------
                FileInputStream finC = new FileInputStream(new File("./SDTP1/Compras.txt"));
                ObjectInputStream oisC = new ObjectInputStream(finC);

                compras = (ArrayList<Transacao>) oisC.readObject();
                //imprime o conteúdo do servidor para debugging
                System.out.println(compras.toString());

                finC.close();
                oisC.close();

                //Vendas------------------------------------------------------------
                FileInputStream finV = new FileInputStream(new File("./SDTP1/Vendas.txt"));
                ObjectInputStream oisV = new ObjectInputStream(finV);

                filmes = (ArrayList<Filme>) oisV.readObject();
                //imprime o conteúdo do servidor para debugging
                System.out.println(filmes.toString());

                finV.close();
                oisV.close();
                */
               //------------------------------------------------------------------
                System.out.println("Servidor em execução...");
                Servidor h = new Servidor();
                Naming.rebind(Constants.RMI_ID, h);
                //client.printOnClient("AQUI");

            } catch (RemoteException ex) {
                System.out.println("Exception in server" + ex.getMessage());
            } catch (MalformedURLException ex) {
                System.out.println("Exception in server - URL");
            } catch (IOException ex) {
                System.out.println("Erro:" + ex.getMessage());
            }/*catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }*/
        }
        return;
    }

    /*
    //s é a mensagem a dizer
    public void chamada(String s){
    try {
            Servidor h = new Servidor();
            Naming.rebind("Hello", h);

            while (true) {
                client.printOnClient(s);
            }
        } catch (RemoteException r) {
            System.out.println("Exception in server" + r.getMessage());
        } catch (java.net.MalformedURLException u) {
            System.out.println("Exception in server - URL");
        }
    
    }*/
}
