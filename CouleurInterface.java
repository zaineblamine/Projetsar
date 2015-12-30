import java.rmi.Remote;
import java.rmi.RemoteException;
public interface CouleurInterface extends Remote {
    Couleur couleurAleatoire() throws RemoteException;
    Couleur convertirStringACouleur (String c) throws RemoteException;
}
