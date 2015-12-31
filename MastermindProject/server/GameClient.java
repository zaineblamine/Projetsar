//client
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;
public class GameClient  /*extends Thread*/  {
    public GameClient(){
        super();
    }
    public GameClient (String [] args){

	     try{
                 if(System.getSecurityManager() == null) System.setSecurityManager(new RMISecurityManager());
                Callback cb=new Callback();
                Registry reg = LocateRegistry.getRegistry("localhost",1099);
		FabGame fab = (FabGame) reg.lookup("Fabrique");
                Game gm ;
                gm=(Game) fab.newGame();
                System.out.println("Bienvenue..");
                GameClient c1=new GameClient();
                boolean bool=c1.jouerPartie(gm,cb);
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
                            bool=c1.jouerPartie(gm2,cb);
                            i++;
                        }
                        else {
                            continuer=false;
                            System.out.println("Merci! bonne journée (Tapez CTRL c pour quitter)");
                        }                    
                    }                    
                }             
             }
               catch (Exception e) {
		System.out.println ("Erreur d'acces a l'objet distant.");
		System.out.println (e.toString());
               }
    }
             
      public boolean jouerPartie(Game gm, ICallback cb) throws RemoteException, InterruptedException{
             boolean found=false;
                int coup=0;
                boolean erreur=false;
                while(coup<10 && found==false && erreur==false){
                  coup++;
                  System.out.println("Donnez une combinaison de couleur ( R-> Rouge , J -> jaune ; V -> vert ; B -> bleu ; O-> orange ; BL -> blanc ; VI -> violet ; F -> fuchsia}");
                  Scanner sc=new Scanner(System.in);
                  String comb=sc.nextLine();
                  if(gm.erreurDeSaisie(comb,cb)){//message de la part du serveur va etre affiché
                      erreur=true;
                  }
                  //else
                  else{
                  found=gm.jouerCoup(cb, comb);
                  Thread.sleep((long) 50.0);//pour le message du Callback s'affiche avant
                  }
                }
                return found;//si reussi found=true pour qu'il puisse le choix de rejouer/ si non reussi ou erreur de saisie
              }
}