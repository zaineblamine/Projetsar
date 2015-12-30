import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.StringTokenizer;
public class GameImp extends UnicastRemoteObject implements Game 
{
       public static int MAX= 4;
       private Couleur[] combinaison; 
       public String combinaisonSecrete;
       HashMap<String,Integer> resultat;
       public GameImp() throws RemoteException
       {
	    super();
            genererCombinaisonSecrete();
           //System.out.println("kkk"+combinaisonSecrete);
       }
       
    public void genererCombinaisonSecrete() throws RemoteException {
        combinaison = new Couleur[MAX];
		System.out.println("Nouvelle combinaison secrete");
		for (int i = 0 ; i < MAX ; i++) {
			combinaison[i] = Couleur.couleurAleatoire();
                         //combinaison[i] = Couleur.B;
		}
                combinaisonSecrete=toString(combinaison);
		System.out.println(combinaisonSecrete);
    }	
    public String getCombinaisonSecrete(){
        return combinaisonSecrete;
    }
    
    public String toString() {
		return toString(combinaison);
	}
	
	public String toString(Couleur []comb) {
		String str = "" + comb[0];
		for (int i = 1 ; i < MAX; i++) {
			str = str + " " + comb[i];
		}
		return str;
	}

    @Override
    public boolean CombinaisonEgale(String comb) throws RemoteException {//ok
      if (combinaisonSecrete.compareTo(comb)==0)
            return true;
      else
          return false;  
    }
    
    public Couleur[] stringToTabCol(String comb) throws RemoteException{//ok
        Couleur[] essaiClient = new Couleur[MAX];
	Couleur[] copieCombinasionCorrecte = combinaison.clone();
        StringTokenizer st = new StringTokenizer(comb);
        int i=0;
        while (st.hasMoreTokens()) {
           String coul=st.nextToken();
           essaiClient[i]=Couleur.convertirStringACouleur(coul);
           i++;
     }
        //System.out.println("i="+i);
     return essaiClient;
    }

 public void comparerCombinaison(Couleur[] coup) throws RemoteException{//ok
                int bienPlace = 0;
                int malPlace = 0;
                resultat=new HashMap<String,Integer>();
                resultat.put("bienPlace",0);
                resultat.put("malPlace",0);		
                Couleur[] copieCombinasionCorrecte = combinaison.clone();
		//System.out.println(toString(copieCombinasionCorrecte));
		Couleur[] copieCoup = coup.clone();
		System.out.println(toString(copieCoup));
		int n = copieCombinasionCorrecte.length;
		for (int i = 0; i < n; i++) {
			if (copieCombinasionCorrecte[i] == copieCoup[i]) {
				bienPlace++;
				copieCombinasionCorrecte[i] = null;
				copieCoup[i] = null;
			}
		}
		for ( int i  = 0 ; i < n; i++) {
			int j = 0;
			boolean exist = false;
			while ( (j < n) && !exist) {
				exist = ((copieCombinasionCorrecte[i] == copieCoup[j]) && (copieCombinasionCorrecte[i]!=null));
				j++;
			}
			if (exist) {
				copieCombinasionCorrecte[i] = null;
				copieCoup[j-1] = null;
				malPlace++;
			}
		}
                System.out.println("on a "+bienPlace+" bienPlace et +"+malPlace+"malPlace");
                resultat.put("bienPlace",bienPlace);
                resultat.put("malPlace",malPlace);
		/*if (bienPlace == 4) {
			r.estado = Jeu.GANGNAT;
		} else {
			r.estado = Jeu.RESULTAT_PARCIEL;
		}
		return r;
        }*/
              
} 
  public HashMap<String,Integer> getResultat(){
                        return resultat;
            }

  
}
