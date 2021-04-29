package SDTP1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Servidor extends java.rmi.server.UnicastRemoteObject implements ServidorInterface {

    //lista de clientes subscritos
    private static ArrayList<ClienteInterface> clientes = new ArrayList<ClienteInterface>();
    
    //lista de filmes (PRINCIPAL)
    private static ArrayList<Filme> filmes = new ArrayList<Filme>();
    //TODO-ADRIANA: ler os filmes do ficheiro no arraque do servidor 
    //o mesmo para todos os outros
    
    //lista de vendas
    private static ArrayList<Transacao> vendas = new ArrayList<Transacao>();

    //lista de compras
    private static ArrayList<Transacao> compras = new ArrayList<Transacao>();

    //lista de utilizadores
    private static ArrayList<Utilizador> utilizadores = new ArrayList<Utilizador>();

    public Servidor() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public void subscribe(String nomeCliente, ClienteInterface cliente) throws RemoteException {
        System.out.println("Subscribing: " + nomeCliente);
        clientes.add(cliente);
        //no finm da execução do servidor faz reset à lista de clientes subscritos pelo servidor
        clientes.clear();

    }

    //Métodos remotas para Filmes
    @Override
    public void registarFilme(Filme f) throws RemoteException {
        filmes.add(f);
    }

    @Override
    public ArrayList<Filme> consultarFilmes() throws RemoteException {
        return filmes;
    }

    //Métodos remotas para Utilizador
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

    @Override
    public void registarUtilizador(Utilizador u) throws RemoteException {
        utilizadores.add(u);
    }

    @Override
    public ArrayList<Utilizador> consultarUtilizadores() throws RemoteException {
        return utilizadores;
    }

    @Override
    public boolean removerUtilizador(Utilizador utilizador) throws RemoteException {
        String name;
        for (int i = 0; i < utilizadores.size(); i++) {
            name = utilizadores.get(i).getUsername();
            if (utilizadores.get(i).getUsername().equals(name)) {
                utilizadores.remove(utilizadores.get(i));
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Transacao> consultarCompras() throws RemoteException {
        return compras;
    }

    @Override
    public ArrayList<Transacao> consultarVendas() throws RemoteException {
        return vendas;
    }

    @Override
    public void adicionarStock(Filme filme, Integer num) throws RemoteException {
        int stock = 0, q=0;
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equals(filme.getNome())) {
                q=filmes.get(i).getQuantidade();
                filmes.get(i).setQuantidade(q+(int) num);
            }
        }
    }

    @Override
    public boolean eliminarFilme(Filme filme) throws RemoteException {
        String name;
        for (int i = 0; i < filmes.size(); i++) {
            name = filmes.get(i).getNome();
            if (filmes.get(i).getNome().equals(name)) {
                filmes.remove(filmes.get(i));
                return true;
            }
        }
        return false;
    }

    @Override
    public void registarCompra(Transacao t) throws RemoteException {
        compras.add(t);
    }

    @Override
    public void registarVenda(Transacao t) throws RemoteException {
        vendas.add(t);
    }

    public static void main(String[] args) {

        System.setSecurityManager(new SecurityManager());

        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            System.out.println("Exception starting RMI registry:");
            System.out.println(ex.getMessage());
        }
        System.out.println("RMI registry ready.");

        try {
            Servidor h = new Servidor();
            Naming.rebind(Constants.RMI_ID, h);
        } catch (RemoteException ex) {
            System.out.println("Exception in server" + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Exception in server - URL");
        }

    }
}
