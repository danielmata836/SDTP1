package SDTP1;
/**
 *
 * @author Daniel
 */
public interface ClienteInterface extends java.rmi.Remote{
    public void printOnClient(String s) throws java.rmi.RemoteException;
}
