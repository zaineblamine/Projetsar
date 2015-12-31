//client
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;
public class GameClient {

	public static void main (String [] args) {

	     try{
                Registry reg = LocateRegistry.getRegistry("localhost",1099);
		FabGame fab = (FabGame) reg.lookup("Fabrique");
                Game gm;
                System.out.println("Bienvenue..");
                gm=(Game) fab.newGame();
                GameClient c1=new GameClient();
               // c1.jouerPartie(gm);
                boolean bool=c1.jouerPartie(gm);
                boolean continuer=true;
                if (bool==true){
                    int i=2;
                    while (bool==true && continuer==true){
                        System.out.println("Voulez-vous rejouer (O/N)");
                        Scanner sc=new Scanner(System.in);
                        String rep=sc.nextLine();
                        
                        if(rep.compareTo("O")==0) {
                            continuer=true;
                            
                            System.out.println("\n"+i+"ème partie: Bonne chance!");
                            Game gm2=(Game) fab.newGame();
                            bool=c1.jouerPartie(gm2);
                            i++;
                        }
                        else {
                            continuer=false;
                            System.out.println("Merci! bonne journée");
                        }                    
                    }                    
                }             
	     }
	     catch (Exception e) {
		System.out.println ("Erreur d'acces a l'objet distant.");
		System.out.println (e.toString());
	     }
     }
        public boolean jouerPartie(Game gm) throws RemoteException{
             boolean found=false;
                int coup=0;
                while(found==false && coup<10){
                  System.out.println("Donnez une combinaison de couleur ( R-> Rouge , J -> jaune ; V -> vert ; B -> bleu ; O-> orange ; BL -> blanc ; VI -> violet ; F -> fuchsia}");
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
                return found;//si reussi found=true pour qu'il puisse le choix de rejouer
        }
} 
