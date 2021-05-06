package SDTP1;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Daniel
 * @version 1.0
 */
public interface ServidorInterface extends java.rmi.Remote {
    //Protótipos dos métodos para os filmes
    public void registarFilme(Filme f) throws RemoteException;
    public ArrayList<Filme> consultarFilmes() throws RemoteException;
    public void registarUtilizador(Utilizador u) throws RemoteException;
    public void adicionarStock(Filme filme, Integer num) throws RemoteException;
    public void adicionarVendidos(Filme filme, Integer num) throws RemoteException;
    public boolean eliminarFilme(Filme filme) throws RemoteException;
        
    //Protótipos dos métodos para os utilizadores
    public boolean login(String nome, String password) throws RemoteException;
    public ArrayList<Utilizador> consultarUtilizadores() throws RemoteException;
    public boolean removerUtilizador(Utilizador utilizador) throws RemoteException;
    
    //Transações: compras / vendas
    public ArrayList<Transacao> consultarCompras() throws RemoteException;
    public ArrayList<Transacao> consultarVendas() throws RemoteException;
    public void registarCompra(Transacao t) throws RemoteException;
    public void registarVenda(Transacao t) throws RemoteException;
    
     public double produtoCaro()throws RemoteException;
    
    //Callback
    //public void printOnServer(String s) throws java.rmi.RemoteException;
    //primeira função subscribe que já tinha sido adicionada pelo Daniel Mata
    public void subscribe(String s, ClienteInterface cliente) throws RemoteException;
    //public void chamada(String s) throws java.rmi.RemoteException;
    
}