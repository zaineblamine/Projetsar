import java.util.Random;
public enum Couleur {
R,J,V,B,O,BL,VI,F;
	static Random r = new Random();
	
	public static Couleur couleurAleatoire() {
		int n = r.nextInt(9);
		switch (n) {
		case 0: return R;
		case 1: return J;
		case 2: return V;
		case 3: return B;
		case 4: return O;
                case 5: return BL;
                case 6: return V;                   
		default: return F;
		}
	}
        public static Couleur convertirStringACouleur (String c) {
		switch (c) {
		case "R": return R;
		case "J": return J;
		case "V": return V;
		case "B": return B;
		case "O": return O;
                case "BL": return BL;//white
                case "VI": return VI;//mauve
		default: return F;
		}
	}
}