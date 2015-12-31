//server
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
public interface Game extends Remote{
        public String couleurAleatoire() throws RemoteException;
        public void genererCombinaisonSecrete() throws RemoteException;
        public String[] stringToTabCol(String comb) throws RemoteException;
        public void comparerCombinaison(String[] coup) throws RemoteException;
         public HashMap<String,Integer> getResultat() throws RemoteException;
}