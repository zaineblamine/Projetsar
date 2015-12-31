//server
import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
public class GameImp extends UnicastRemoteObject implements Game 
{
       public static int MAX= 4;
       public static int NBCOL= 8;
       static Random r = new Random();
       private String[] combinaison; 
       public String combinaisonSecrete;
       HashMap<String,Integer> resultat;
       public String[] couleur={"R","J","V","B","O","BL","VI","F"};
       public GameImp() throws RemoteException
       {
	    super();
            genererCombinaisonSecrete();
       }
       
       public String couleurAleatoire() throws RemoteException{
		int n = r.nextInt(9);
		switch (n){
		case 0: return couleur[0];//rouge
		case 1: return couleur[1];//jaune
		case 2: return couleur[2];//vert
		case 3: return couleur[3];//bleu
		case 4: return couleur[4];//orange
                case 5: return couleur[5];//blanc
                case 6: return couleur[6];//violet                  
		default: return couleur[7];//fichsia
		}
	}
       
    public void genererCombinaisonSecrete() throws RemoteException {//done
        combinaison = new String[MAX];
        combinaisonSecrete="";
		System.out.println("Nouvelle combinaison secrete");
		for (int i = 0 ; i < MAX ; i++) {
			combinaison[i] = couleurAleatoire();
                        combinaisonSecrete=combinaisonSecrete+combinaison[i]+" ";
		}
		System.out.println(combinaisonSecrete);
    }	
 public void comparerCombinaison(String comb) throws RemoteException{//ok
                int bienPlace = 0;
                int malPlace = 0;
                String []coup=new String[MAX];
                coup=comb.split(" ");
                resultat=new HashMap<String,Integer>();
                resultat.put("bienPlace",0);
                resultat.put("malPlace",0);		
                String[] copieCombinasionCorrecte = combinaison.clone();
		String[] copieCoup =coup.clone();
				for (int i = 0; i < MAX; i++) {
			if (copieCombinasionCorrecte[i].compareTo(copieCoup[i])==0) {
				bienPlace++;
				copieCombinasionCorrecte[i] ="x";
				copieCoup[i] ="x";
			}
		}
		for ( int i  = 0 ; i < MAX; i++) {
			int j = 0;
			boolean exist = false;
			while ( (j < MAX) && !exist) {
				exist = ((copieCombinasionCorrecte[i].compareTo(copieCoup[j])==0) && (copieCombinasionCorrecte[i]!="x"));
				j++;
			}
			if (exist) {
				copieCombinasionCorrecte[i] ="x";
				copieCoup[j-1] ="x";
				malPlace++;
			}
		}
                System.out.println("On a "+bienPlace+" couleur(s) bien placée(s) et +"+malPlace+" mal Placée(s)");
                resultat.put("bienPlace",bienPlace);
                resultat.put("malPlace",malPlace);
              
} 
  public HashMap<String,Integer> getResultat()throws RemoteException{
                        return resultat;
            }
  
  public boolean CouleurExiste(String coul)throws RemoteException {
      boolean exist=false;
      int i=0;
      while(i<NBCOL && exist==false){
              if (couleur[i].compareTo(coul)==0)
                  exist=true;
              else i++;
      }
          
  return exist;
}
  public boolean erreurDeSaisie(String combinaison,ICallback cb) throws RemoteException{
      boolean erreur=false;
      int i=0;
      String[] coul=combinaison.split(" ");
      while(i<MAX && erreur==false){
          if (CouleurExiste(coul[i]))
              i++;
          else{
              erreur=true;
              System.out.println("Erreur de saisie!!fin du jeu..");
              callMeBack("Erreur de saisie!!fin du jeu... tapez CTRL c pour quitter",cb); 
          }
              
      }
      return erreur;
  }
  public void callMeBack(String param,ICallback callback)throws RemoteException{
	Servant servant = new Servant(param,callback);
	// création du servant
	servant.start();
	// démarrage du servant
	}
       public boolean jouerCoup(ICallback cb,String comb) throws RemoteException{
             boolean gagne=false;
             //boolean erreur=erreurDeSaisie(comb,cb);
             int coup=0;
                 if(!erreurDeSaisie(comb,cb)){                        
                      comparerCombinaison(comb);
                        HashMap<String,Integer> resultat=getResultat();
                        String res0="Vous avez "+resultat.get("bienPlace")+" couleurs bien placée(s) et "+resultat.get("malPlace")+" mal placeé(s)";
                        callMeBack(res0,cb);
                        if (resultat.get("bienPlace")==4){
                              gagne=true;
                              String res1="Félicitation! Vous avez gagner";                             
                              callMeBack(res1,cb);
                        }
                 }          
              return gagne;//si reussi found=true pour qu'il puisse le choix de rejouer
              }
}