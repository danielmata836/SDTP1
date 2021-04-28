package SDTP1;
/**
 *
 * @author Daniel
 */
public interface ClienteInterface extends java.rmi.Remote{
    //TODO: dividir e criar interface para cada tipo de cliente (se necessário para a callback)
    public void printOnClient(String s) throws java.rmi.RemoteException;
}
