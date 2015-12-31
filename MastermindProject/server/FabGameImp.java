//server
import java.rmi.*;
import java.rmi.server.*;
public class FabGameImp extends UnicastRemoteObject implements FabGame{
    public FabGameImp()throws RemoteException {};
    public Game newGame() throws RemoteException {
        return new GameImp();
    }
}
