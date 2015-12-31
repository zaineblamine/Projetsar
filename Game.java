//server
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
public interface Game extends Remote{
        public String couleurAleatoire() throws RemoteException;
        public void genererCombinaisonSecrete() throws RemoteException;
        public void comparerCombinaison(String coup) throws RemoteException;
        public HashMap<String,Integer> getResultat() throws RemoteException;
        public boolean CouleurExiste(String coul)throws RemoteException;
        public boolean erreurDeSaisie(String combinaison) throws RemoteException;

}