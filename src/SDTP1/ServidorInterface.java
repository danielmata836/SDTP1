package SDTP1;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public interface ServidorInterface extends java.rmi.Remote {
    //Filmes
    public void subscribe(String s, ClienteInterface cliente) throws RemoteException;
    public void registarFilme(Filme f) throws RemoteException;
    public ArrayList<Filme> consultarFilmes() throws RemoteException;
    public void registarUtilizador(Utilizador u) throws RemoteException;
    public void adicionarStock(Filme filme, Integer num) throws RemoteException;
    public boolean eliminarFilme(Filme filme) throws RemoteException;
        
    //Utilizadores
    public boolean login(String nome, String password) throws RemoteException;
    public ArrayList<Utilizador> consultarUtilizadores() throws RemoteException;
    public boolean removerUtilizador(Utilizador utilizador) throws RemoteException;
    
    //Transações: compras / vendas
    public ArrayList<Transacao> consultarCompras() throws RemoteException;
    public ArrayList<Transacao> consultarVendas() throws RemoteException;
    public void registarCompra(Transacao t) throws RemoteException;
    public void registarVenda(Transacao t) throws RemoteException;
}