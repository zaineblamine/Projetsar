import java.rmi.*;
public class Servant extends Thread {
	private int time;
	private String param;
	private ICallback callback;
	public Servant(int time, String param, ICallback callback) { // le constructeur reçoit l’objet callback en paramètre
		this.time = time;
		this.param = param;
		this.callback = callback;
	}
	public void run() {// exécution du servant comme thread séparé
	try {
		Thread.sleep(1000*time);
		// attend le délai fixé (time exprimé en secondes)
	}
	catch(InterruptedException e) { }
        callback.doCallback(param);
	callback = null; // nettoyage
	System.gc();
	}
}
