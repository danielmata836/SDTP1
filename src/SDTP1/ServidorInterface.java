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
    public ArrayList<Utilizador> consultarUtilizadores() throws RemoteException;
    
    //Utilizadores
    public boolean login(String nome, String password) throws RemoteException;
}
