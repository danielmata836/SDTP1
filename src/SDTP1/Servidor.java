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

    //TODO: dividir e criar interface para cada tipo de cliente (se necessário para a callback)
    //lista de clientes subscritos
    private static ArrayList<ClienteInterface> clientes = new ArrayList<ClienteInterface>();

    //lista de filmes
    private static ArrayList<Filme> filmes = new ArrayList<Filme>();

    //TODO: lista de filmes comprados e outra de filmes vendidos
    
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
