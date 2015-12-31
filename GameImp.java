//server
import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;
public class GameImp extends UnicastRemoteObject implements Game 
{
       public static int MAX= 4;
       static Random r = new Random();
       private String[] combinaison; 
       public String combinaisonSecrete;
       HashMap<String,Integer> resultat;
       public GameImp() throws RemoteException
       {
	    super();
            genererCombinaisonSecrete();
            
       }
       public String couleurAleatoire() throws RemoteException{
		int n = r.nextInt(9);
		switch (n) {
		case 0: return "R";//rouge
		case 1: return "J";//jaune
		case 2: return "V";//vert
		case 3: return "B";//bleu
		case 4: return "O";//orange
                case 5: return "BL";//blanc
                case 6: return "VI";//violet                  
		default: return "F";//fichsia
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
   /* public String getCombinaisonSecrete(){
        return combinaisonSecrete;
    }
    public boolean CombinaisonEgale(String comb) throws RemoteException {//ok
      if (combinaisonSecrete.compareTo(comb)==0)
            return true;
      else
          return false;  
    }*/
    public String[] stringToTabCol(String comb) throws RemoteException{//done
        String[] essaiClient = new String[MAX];
	String[] copieCombinasionCorrecte = combinaison.clone();
        StringTokenizer st = new StringTokenizer(comb);
        int i=0;
        while (st.hasMoreTokens()) {
           String coul=st.nextToken();
           essaiClient[i]=coul;
           i++;
     }
     return essaiClient;
     
    }

 public void comparerCombinaison(String[] coup) throws RemoteException{//ok
                int bienPlace = 0;
                int malPlace = 0;
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
}
