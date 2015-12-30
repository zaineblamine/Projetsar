   import java.rmi.*;
import java.util.HashMap;
import java.util.Scanner;
public class GameClient {

	public static void main (String [] args) {

	     try{
		Game gm = (Game) Naming.lookup("rmi://localhost:1099/MyGame");
                GameClient c1=new GameClient();
                c1.jouerPartie(gm);
	     }
	     catch (Exception e) {
		System.out.println ("Erreur d'acces a l'objet distant.");
		System.out.println (e.toString());
	     }
     }
        public void jouerPartie(Game gm) throws RemoteException{
             boolean found=false;
                int coup=0;
                while(found==false && coup<10){
                  System.out.println("Donnez une combinaison de couleur ( R-> Rouge , J -> jaune ; V -> vert ; B -> bleu ; O-> orange ; BL -> blanc ; V -> violet ; F -> fuchsia}");
                  Scanner sc=new Scanner(System.in);
                  String comb=sc.nextLine();
                  System.out.println ("La combinaison est "+comb);
                  String[] tab=gm.stringToTabCol(comb);
                  gm.comparerCombinaison(tab);
                  HashMap<String,Integer> resultat=gm.getResultat();
                  System.out.println("Vous avez "+resultat.get("bienPlace")+" couleurs bien placée(s) et "+resultat.get("malPlace")+"mal placeé(s)");//ok
                  coup++;
                  if (resultat.get("bienPlace")==4){
                      found=true;
                      if (coup==1)
                          System.out.println("Félicitation vous avez gagner dès la "+coup+"ére coup");
                      else
                          System.out.println("Félicitation vous avez gagner à la "+coup+"ème coup");
                  }
                      
             }
        }
} 
