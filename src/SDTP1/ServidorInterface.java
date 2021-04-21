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
    
    //Utilizadores
    public boolean login(String nome, String password) throws RemoteException;
}
