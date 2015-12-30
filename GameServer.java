import java.rmi.*;
import java.rmi.server.*;
public class GameServer {
	public static void main(String[] args) {
	       try {
		System.out.println( "Serveur : Construction de l'implementation ");
		GameImp gm= new GameImp();
		
		//System.out.println("Objet Game lie dans le RMIregistry");
		Naming.rebind("MyGame", gm);
		System.out.println("Attente des invocations des clients ...");
	       }
	       catch (Exception e) {
		System.out.println("Erreur de liaison de l'objet Game");
		System.out.println(e.toString());
	       }
	} 
}
