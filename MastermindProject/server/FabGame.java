//server
import java.rmi.*;
public interface FabGame extends Remote {
public Game newGame() throws RemoteException ;
}

