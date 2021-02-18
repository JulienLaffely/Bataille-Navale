import java.io.Serializable;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class BombesJ implements Serializable{
	protected boolean horizontale;
	protected boolean verticale;
	protected boolean croix;
	protected JRadioButton butClassique ;
	protected JRadioButton butHorizontale ;
	protected JRadioButton butVerticale;
	protected JRadioButton butCroix;
	/**
	 * Initialisation des bombes du joueur
	 */
	protected BombesJ() {
			this.horizontale=true;
			this.verticale=true;
			this.croix=true;
			this.butClassique = new JRadioButton("Bombe classique");
			this.butHorizontale = new JRadioButton("Bombe horizontale");
			this.butVerticale = new JRadioButton("Bombe verticale");
			this.butCroix = new JRadioButton("Bombe en croix");
	}
}
