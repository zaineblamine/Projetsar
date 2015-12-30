import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
public interface Game extends Remote{
	void genererCombinaisonSecrete() throws RemoteException;
        boolean CombinaisonEgale(String comb) throws RemoteException;
        String getCombinaisonSecrete() throws RemoteException;
        Couleur[] stringToTabCol(String comb) throws RemoteException;
        void comparerCombinaison(Couleur[] coup)throws RemoteException;
        HashMap<String,Integer> getResultat() throws RemoteException;
}