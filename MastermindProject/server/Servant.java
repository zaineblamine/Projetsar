import java.rmi.*;
import java.util.logging.Level;
//server
import java.util.logging.Logger;
public class Servant extends Thread {
	private int time;
	private String param;
	private ICallback callback;
	public Servant(String param, ICallback callback) { // le constructeur reçoit l’objet callback en paramètre
		this.time = time;
		this.param = param;
		this.callback = callback;
	}
	public void run() {// exécution du servant comme thread sépar
            try {
                callback.doCallback(param);
            } catch (RemoteException ex) {
                Logger.getLogger(Servant.class.getName()).log(Level.SEVERE, null, ex);
            }
	callback = null; // nettoyage
	System.gc();
	}
}
