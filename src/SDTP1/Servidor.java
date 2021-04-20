package SDTP1;

import java.rmi.RemoteException;

/**
 *
 * @author Daniel
 */
public class Servidor extends java.rmi.server.UnicastRemoteObject implements ServerInterface {
    //private static ArrayList<> ;
    
    public Servidor() throws java.rmi.RemoteException {
        super();
    }
    
    public void subscribe(String s, ClientInterface cliente) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        
    }
}
