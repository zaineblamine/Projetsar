    //server
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
public class GameServer {
	public static void main(String[] args) {
	       try {
                Registry registry = LocateRegistry.createRegistry(1099);
		System.out.println( "Serveur : Construction de l'implementation ");
		FabGameImp fab= new FabGameImp();
		System.out.println("Objet Fabrique lie dans le RMIregistry");
		//System.out.println("Objet Game lie dans le RMIregistry");
		Naming.rebind("Fabrique", fab);
                System.out.println ("Serveur prêt.") ;
		System.out.println("Attente des invocations des clients ...");
	       }
	       catch (Exception e) {
		System.out.println("Erreur de liaison de l'objet Game");
		System.out.println(e.toString());
	       }
	} 
}
