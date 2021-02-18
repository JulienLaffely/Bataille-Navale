import java.io.Serializable;

@SuppressWarnings("serial")
public class BombesIA implements Serializable{
	protected boolean horizontale;
	protected boolean verticale;
	protected boolean croix;
	/**
	 * Initialisation des bombes de l'IA
	 */
	protected BombesIA() {
			this.horizontale=true;
			this.verticale=true;
			this.croix=true;
	}
	
}
