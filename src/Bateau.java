import java.io.Serializable;

@SuppressWarnings("serial")
public class Bateau implements Serializable{
	protected int id;
	protected int taille ;
	protected int ligne ;
	protected int colonne ;
	protected boolean verticale ;
	/**
	 * 
	 * @param id 2=porte-avion,3=croiseur,4=CTn°1,5=CTn°2,6=torpilleur
	 * @param taille taille du bateau
	 * @param ligne coordonné x de la premierecase du bateau
	 * @param colonne coordonné y de la premierecase du bateau
	 * @param verticale direction du placement du bateau
	 */
	protected Bateau(int id ,int taille , int ligne , int colonne , boolean verticale) {
		this.id=id;
		this.taille = taille;
		this.colonne=colonne;
		this.ligne = ligne ;
		this.verticale=verticale;
	}		
}
