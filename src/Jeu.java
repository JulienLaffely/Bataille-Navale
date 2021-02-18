import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Jeu implements Serializable {
	protected JFrame Fin;
	protected JLabel Winner;
	protected JLabel infoJoueur;
	protected JLabel infoIA;
	protected JLabel infoCoulerJoueur;
	protected JLabel infoCoulerIA;
	protected String Sens;
	protected int BombeJoueur;
	protected int[][] plateauJ;
	protected int[][] plateauIA;
	protected CaseDuJoueur[][] CaseJoueur;
	protected CaseDeIA[][] CaseIA;
	private int nbBateauJoueur;
	private int nbBateauIA;
	protected int joueur;
	protected int bat;
	protected String victoire;
	protected boolean placementOK;
	protected BombesJ J;
	protected BombesIA IA;
	private Bateau porteAvionJ;
	private Bateau CroiseurJ;
	private Bateau contreTorpilleur1J;
	private Bateau contreTorpilleur2J;
	private Bateau torpilleurJ;
	private Bateau porteAvionIA;
	private Bateau CroiseurIA;
	private Bateau contreTorpilleur1IA;
	private Bateau contreTorpilleur2IA;
	private Bateau torpilleurIA;
	/**
	 *  Initialisation de la partie.
	 *  Fin est le Frame qui s’ouvre en fin de partie.
	 *  Winner contient la phrase qui va indiquer si le joueur à perdu ou gagner.
	 *  infoJoueur et infoIA vont afficher « raté » ou « coulé » en dessous de chaque grille.
	 *  infoCoulerJoueur et infoCoulerIA vont afficher le bateau coulé en dessous de chaque grille.
	 *  Sens contient l’information en temps réel de la position du bateau en fonction du mouvement de la molette.
	 *  BombeJoueur contient l’information de quel bombe le joueur à selectionner (1.classique ,2.verticale, 3.horizontale, 4.croix).
	 *  plateauJ et plateauIA représentent les grilles de chaque joueur avec des chiffres (0-case vide, 1-case adjacente d’un bateau, entre 2 et 6 ce sont les id de chaque bateau le 2 représentant le plus grand et le 6 le plus petit, 8-bateau touché, 9-case touché mais pas de bateau touché).
	 *  CaseJoueur et CaseIA repésentent les grilles de chaque joueur mais avec les vraies cases.
	 *  nbBateauJoueur et nbBateauIA representes les nombres de bateau restant dans chaque camp afin de déterminer la victoire d’un joueur plus tard lorsqu’un des deux sera égal à 0.
	 *  Joueur permet d’identifier le joueur qui joue (0 pour l’ordinateur et 1 pour le joueur).
	 *  bat permet de determiner quel bateau le joueur place.
	 *  victoire contient la phrase afficher en fin de partie.
	 *  placementOK lui permettra de déterminer si le bateau peut se placer à l’endroit voulu par le Joueur.
	 *  Les derniers éléments sont les bombes et les bateaux de chaque joueur.
	 */
	public Jeu() {
		this.Fin = new JFrame();
		this.Fin.setSize(410, 200);
		this.Fin.setLayout(new GridBagLayout());
		this.Fin.setTitle("La partie est terminée !");
		GridBagConstraints gdc = new GridBagConstraints();
		this.Winner = new JLabel("        " + this.victoire);
		gdc.gridx = 0;
		gdc.gridy = 0;
		gdc.gridwidth = 3;
		this.Fin.add(Winner, gdc);
		this.Fin.setVisible(false);
		this.infoIA = new JLabel(" ");
		this.infoJoueur = new JLabel("Placez votre Porte-Avion ! (5 cases)");
		this.infoCoulerIA = new JLabel(" ");
		this.infoCoulerJoueur = new JLabel(" ");

		this.BombeJoueur = 1;
		this.Sens = "               Sens du bateau : Verticale";
		this.plateauJ = new int[10][10];
		this.plateauIA = new int[10][10];
		this.CaseIA = new CaseDeIA[10][10];
		this.CaseJoueur = new CaseDuJoueur[10][10];
		this.nbBateauJoueur = 5;
		this.nbBateauIA = 5;
		this.victoire = " ";
		this.placementOK = false;
		this.bat = 1;
		this.J = new BombesJ();
		this.IA = new BombesIA();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.plateauJ[i][j] = 0;
				this.plateauIA[i][j] = 0;
			}
		}
		this.createBateauIA();
		this.joueur = 1;
	}
	/**
	 * Place les bateau du joueur sur sa grille tout en verifiant que l'emplacement est libre, pas adjacent à un autre bateau et que la taille du bateau ne dépasse pas la grille.
	 * @param x coordonnée x de la premiere case du bateau
	 * @param y coordonnée y de la premiere case du bateau
	 * @param vert direction dans lequel le bateau est placé (1:verticale 2:horizontale)
	 */
	protected void createBateauJoueur(int x, int y, int vert) {
		boolean verti = false;
		if (this.bat == 1) {
			if (vert == 1) {
				verti = true;
			} else {
				verti = false;
			}
			this.porteAvionJ = new Bateau(2, 5, x, y, verti);
			if (this.porteAvionJ.verticale) {
				if (this.porteAvionJ.ligne + this.porteAvionJ.taille > 10 || !emplacementVideJ(this.porteAvionJ)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			} else {
				if (this.porteAvionJ.colonne + this.porteAvionJ.taille > 10 || !emplacementVideJ(this.porteAvionJ)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			}
			if (this.bat == 1) {
				placementJ(this.porteAvionJ);
				++this.bat;
				this.placementOK = true;
			}
		}

		else if (this.bat == 2) {
			if (vert == 1) {
				verti = true;
			} else {
				verti = false;
			}
			this.CroiseurJ = new Bateau(3, 4, x, y, verti);
			if (this.CroiseurJ.verticale) {
				if (this.CroiseurJ.ligne + this.CroiseurJ.taille > 10 || !emplacementVideJ(this.CroiseurJ)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			} else {
				if (this.CroiseurJ.colonne + this.CroiseurJ.taille > 10 || !emplacementVideJ(this.CroiseurJ)) {
					System.out.println(this.CroiseurJ.colonne + this.CroiseurJ.taille);
					this.bat -= 1;
					this.placementOK = false;
				}
			}
			if (this.bat == 2) {
				placementJ(this.CroiseurJ);
				++this.bat;
				this.placementOK = true;
			}
		}

		else if (this.bat == 3) {
			if (vert == 1) {
				verti = true;
			} else {
				verti = false;
			}
			this.contreTorpilleur1J = new Bateau(4, 3, x, y, verti);
			if (this.contreTorpilleur1J.verticale) {
				if (this.contreTorpilleur1J.ligne + this.contreTorpilleur1J.taille > 10
						|| !emplacementVideJ(this.contreTorpilleur1J)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			} else {
				if (this.contreTorpilleur1J.colonne + this.contreTorpilleur1J.taille > 10
						|| !emplacementVideJ(this.contreTorpilleur1J)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			}
			if (this.bat == 3) {
				placementJ(this.contreTorpilleur1J);
				++this.bat;
				this.placementOK = true;
			}
		}

		else if (this.bat == 4) {
			if (vert == 1) {
				verti = true;
			} else {
				verti = false;
			}
			this.contreTorpilleur2J = new Bateau(5, 3, x, y, verti);
			if (this.contreTorpilleur2J.verticale) {
				if (this.contreTorpilleur2J.ligne + this.contreTorpilleur2J.taille > 10
						|| !emplacementVideJ(this.contreTorpilleur2J)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			} else {
				if (this.contreTorpilleur2J.colonne + this.contreTorpilleur2J.taille > 10
						|| !emplacementVideJ(this.contreTorpilleur2J)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			}
			if (this.bat == 4) {
				placementJ(this.contreTorpilleur2J);
				++this.bat;
				this.placementOK = true;
			}
		}

		else if (this.bat == 5) {
			if (vert == 1) {
				verti = true;
			} else {
				verti = false;
			}
			this.torpilleurJ = new Bateau(6, 2, x, y, verti);
			if (this.torpilleurJ.verticale) {
				if (this.torpilleurJ.ligne + this.torpilleurJ.taille > 10 || !emplacementVideJ(this.torpilleurJ)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			} else {
				if (this.torpilleurJ.colonne + this.torpilleurJ.taille > 10 || !emplacementVideJ(this.torpilleurJ)) {
					this.bat -= 1;
					this.placementOK = false;
				}
			}
			if (this.bat == 5) {
				placementJ(this.torpilleurJ);
				++this.bat;
				this.placementOK = true;
			}
		}
	}
	/**
	 * Place les bateau de l'IA aléatoirement sur sa grille tout en verifiant que l'emplacement est libre, pas adjacent à un autre bateau et que la taille du bateau ne dépasse pas la grille.
	 */
	protected void createBateauIA() {
		Random rand = new Random();

		this.porteAvionIA = new Bateau(2, 5, rand.nextInt(10), rand.nextInt(10), rand.nextBoolean());
		if (this.porteAvionIA.verticale) {
			while (this.porteAvionIA.ligne + this.porteAvionIA.taille > 10 || !emplacementVideIA(this.porteAvionIA)) {
				this.porteAvionIA.ligne = rand.nextInt(10);
				this.porteAvionIA.colonne = rand.nextInt(10);
			}
		} else {
			while (this.porteAvionIA.colonne + this.porteAvionIA.taille > 10 || !emplacementVideIA(this.porteAvionIA)) {
				this.porteAvionIA.colonne = rand.nextInt(10);
				this.porteAvionIA.ligne = rand.nextInt(10);
			}
		}
		placementIA(this.porteAvionIA);

		this.CroiseurIA = new Bateau(3, 4, rand.nextInt(10), rand.nextInt(10), rand.nextBoolean());
		if (this.CroiseurIA.verticale) {
			while (this.CroiseurIA.ligne + this.CroiseurIA.taille > 10 || !emplacementVideIA(this.CroiseurIA)) {
				this.CroiseurIA.ligne = rand.nextInt(10);
				this.CroiseurIA.colonne = rand.nextInt(10);
			}
		} else {
			while (this.CroiseurIA.colonne + this.CroiseurIA.taille > 10 || !emplacementVideIA(this.CroiseurIA)) {
				this.CroiseurIA.colonne = rand.nextInt(10);
				this.CroiseurIA.ligne = rand.nextInt(10);
			}
		}
		placementIA(this.CroiseurIA);

		this.contreTorpilleur1IA = new Bateau(4, 3, rand.nextInt(10), rand.nextInt(10), rand.nextBoolean());
		if (this.contreTorpilleur1IA.verticale) {
			while (this.contreTorpilleur1IA.ligne + this.contreTorpilleur1IA.taille > 10
					|| !emplacementVideIA(this.contreTorpilleur1IA)) {
				this.contreTorpilleur1IA.ligne = rand.nextInt(10);
				this.contreTorpilleur1IA.colonne = rand.nextInt(10);
			}
		} else {
			while (this.contreTorpilleur1IA.colonne + this.contreTorpilleur1IA.taille > 10
					|| !emplacementVideIA(this.contreTorpilleur1IA)) {
				this.contreTorpilleur1IA.colonne = rand.nextInt(10);
				this.contreTorpilleur1IA.ligne = rand.nextInt(10);
			}
		}
		placementIA(this.contreTorpilleur1IA);

		this.contreTorpilleur2IA = new Bateau(5, 3, rand.nextInt(10), rand.nextInt(10), rand.nextBoolean());
		if (this.contreTorpilleur2IA.verticale) {
			while (this.contreTorpilleur2IA.ligne + this.contreTorpilleur2IA.taille > 10
					|| !emplacementVideIA(this.contreTorpilleur2IA)) {
				this.contreTorpilleur2IA.ligne = rand.nextInt(10);
				this.contreTorpilleur2IA.colonne = rand.nextInt(10);
			}
		} else {
			while (this.contreTorpilleur2IA.colonne + this.contreTorpilleur2IA.taille > 10
					|| !emplacementVideIA(this.contreTorpilleur2IA)) {
				this.contreTorpilleur2IA.colonne = rand.nextInt(10);
				this.contreTorpilleur2IA.ligne = rand.nextInt(10);
			}
		}
		placementIA(this.contreTorpilleur2IA);

		this.torpilleurIA = new Bateau(6, 2, rand.nextInt(10), rand.nextInt(10), rand.nextBoolean());
		if (this.torpilleurIA.verticale) {
			while (this.torpilleurIA.ligne + this.torpilleurIA.taille > 10 || !emplacementVideIA(this.torpilleurIA)) {
				this.torpilleurIA.ligne = rand.nextInt(10);
				this.torpilleurIA.colonne = rand.nextInt(10);
			}
		} else {
			while (this.torpilleurIA.colonne + this.torpilleurIA.taille > 10 || !emplacementVideIA(this.torpilleurIA)) {
				this.torpilleurIA.ligne = rand.nextInt(10);
				this.torpilleurIA.colonne = rand.nextInt(10);
			}
		}
		placementIA(this.torpilleurIA);

	}

	/**
	 * Vérifie que le bateau de l'IA peut être placé à l'endroit choisi
	 * @param b Bateau de l'IA que l'on desire placer
	 * @return vrai si l'emplacement est libre pour placer le bateau b sinon faux
	 */
	protected boolean emplacementVideIA(Bateau b) {
		if (b.verticale) {
			if (b.ligne + b.taille > 10) {
				return false;
			}
			for (int i = b.ligne; i < b.ligne + b.taille; i++) {
				if (this.plateauIA[i][b.colonne] != 0)
					return false;
			}
		} else {
			if (b.colonne + b.taille > 10) {
				return false;
			}
			for (int i = b.colonne; i < b.colonne + b.taille; i++) {
				if (this.plateauIA[b.ligne][i] != 0)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Vérifie que le bateau du joueur peut être placé à l'endroit choisi
	 * @param b Bateau du joueur que l'on desire placer
	 * @return vrai si l'emplacement est libre pour placer le bateau b sinon faux
	 */
	
	protected boolean emplacementVideJ(Bateau b) {
		if (b.verticale) {
			if (b.ligne + b.taille > 10) {
				return false;
			}
			for (int i = b.ligne; i < b.ligne + b.taille; i++) {
				if (this.plateauJ[i][b.colonne] != 0)
					return false;
			}
		} else {
			if (b.colonne + b.taille > 10) {
				return false;
			}
			for (int i = b.colonne; i < b.colonne + b.taille; i++) {
				if (this.plateauJ[b.ligne][i] != 0)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Cette méthode place le bateau du joueur sur la grille en modifier les valeurs à l'endroit ou le bateau est placé (entre 2 et 6 en fonction du bateau)
	 * et en modifiant les cases adjacentes(1).
	 * @param b bateau du joueur à placé
	 */

	protected void placementJ(Bateau b) {
		if (b.verticale) {
			for (int i = b.ligne; i < b.ligne + b.taille; i++) {
				this.plateauJ[i][b.colonne] = b.id;
				if (b.colonne - 1 >= 0) {
					this.plateauJ[i][b.colonne - 1] = 1;
				}
				if (b.colonne + 1 <= 9) {
					this.plateauJ[i][b.colonne + 1] = 1;
				}
				if (i == b.ligne && i - 1 >= 0) {
					this.plateauJ[i - 1][b.colonne] = 1;
				}
				if (i == b.ligne + b.taille - 1 && i + 1 <= 9) {
					this.plateauJ[i + 1][b.colonne] = 1;
				}
			}
		} else {
			for (int i = b.colonne; i < b.colonne + b.taille; i++) {
				this.plateauJ[b.ligne][i] = b.id;
				if (b.ligne - 1 >= 0) {
					this.plateauJ[b.ligne - 1][i] = 1;
				}
				if (b.ligne + 1 <= 9) {
					this.plateauJ[b.ligne + 1][i] = 1;
				}
				if (i == b.colonne && b.colonne - 1 >= 0) {
					this.plateauJ[b.ligne][i - 1] = 1;
				}
				if (i == b.colonne + b.taille - 1 && i + 1 <= 9) {
					this.plateauJ[b.ligne][i + 1] = 1;
				}
			}
		}
	}
	
	/**
	 * Cette méthode place le bateau de l'IA sur la grille en modifier les valeurs à l'endroit ou le bateau est placé (entre 2 et 6 en fonction du bateau)
	 * et en modifiant les cases adjacentes(1).
	 * @param b bateau de l'IA à placé
	 */

	protected void placementIA(Bateau b) {
		if (b.verticale) {
			for (int i = b.ligne; i < b.ligne + b.taille; i++) {
				this.plateauIA[i][b.colonne] = b.id;
				if (b.colonne - 1 >= 0) {
					this.plateauIA[i][b.colonne - 1] = 1;
				}
				if (b.colonne + 1 <= 9) {
					this.plateauIA[i][b.colonne + 1] = 1;
				}
				if (i == b.ligne && i - 1 >= 0) {
					this.plateauIA[i - 1][b.colonne] = 1;
				}
				if (i == b.ligne + b.taille - 1 && i + 1 <= 9) {
					this.plateauIA[i + 1][b.colonne] = 1;
				}
			}
		} else {
			for (int i = b.colonne; i < b.colonne + b.taille; i++) {
				this.plateauIA[b.ligne][i] = b.id;
				if (b.ligne - 1 >= 0) {
					this.plateauIA[b.ligne - 1][i] = 1;
				}
				if (b.ligne + 1 <= 9) {
					this.plateauIA[b.ligne + 1][i] = 1;
				}
				if (i == b.colonne && b.colonne - 1 >= 0) {
					this.plateauIA[b.ligne][i - 1] = 1;
				}
				if (i == b.colonne + b.taille - 1 && i + 1 <= 9) {
					this.plateauIA[b.ligne][i + 1] = 1;
				}
			}
		}
	}
	
	/**
	 * 
	 * Cette méthode permet d'actualiser la grille de chiffre lorsque les coups sont jouer en fonction des bombes séléctionner.
	 * 0: case vide, 1: case adjacente d'un bateau, Entre 2 et 6 : bateau non toucher, 8: bateau toucher , 9: case touché mais cible raté
	 * 
	 * @param x coordonnées x de la case ou le joueur à jouer quand c'est à son tour
	 * @param y coordonnées y de la case ou le joueur à jouer quand c'est à son tour
	 */
	
	protected void jouer(int x, int y) {
		boolean toucher = false;
		if (this.joueur == 0) {
			Random choix = new Random();
			boolean Ajouer = true;
			while (Ajouer) {
				int ChooseBomb = choix.nextInt((4 - 1) + 1) + 1;
				if (ChooseBomb == 1) {
					boolean dejaToucher = true;
					while (dejaToucher) {
						int xIA = choix.nextInt((9 - 0) + 1) + 0;
						int yIA = choix.nextInt((9 - 0) + 1) + 0;
						if (this.plateauJ[xIA][yIA] < 8) {
							if (this.plateauJ[xIA][yIA] == 0 || this.plateauJ[xIA][yIA] == 1) {
								this.plateauJ[xIA][yIA] = 9;
							} else {
								toucherJ(this.plateauJ[xIA][yIA]);
								this.plateauJ[xIA][yIA] = 8;
								toucher = true;
							}
							dejaToucher = false;
						}
					}
					Ajouer = false;
				}
				if (ChooseBomb == 2) {
					if (this.IA.verticale == true) {
						int choixColonne = choix.nextInt((9 - 0) + 1) + 0;
						for (int i = 0; i < 10; i++) {
							if (this.plateauJ[i][choixColonne] >= 2 && this.plateauJ[i][choixColonne] <= 6) {
								toucherJ(this.plateauJ[i][choixColonne]);
								this.plateauJ[i][choixColonne] = 8;
								toucher = true;
							} else if (this.plateauJ[i][choixColonne] != 8)
								this.plateauJ[i][choixColonne] = 9;
						}
						this.IA.verticale = false;
						Ajouer = false;
					}
				}
				if (ChooseBomb == 3) {
					if (this.IA.horizontale == true) {
						int choixLigne = choix.nextInt((9 - 0) + 1) + 0;
						for (int i = 0; i < 10; i++) {
							if (this.plateauJ[choixLigne][i] >= 2 && this.plateauJ[choixLigne][i] <= 6) {
								toucherJ(this.plateauJ[choixLigne][i]);
								this.plateauJ[choixLigne][i] = 8;
								toucher = true;
							} else if (this.plateauJ[choixLigne][i] != 8)
								this.plateauJ[choixLigne][i] = 9;
						}
						this.IA.horizontale = false;
						Ajouer = false;
					}
				}
				if (ChooseBomb == 4) {
					if (this.IA.croix == true) {
						boolean dejaToucher = true;
						while (dejaToucher) {
							int xIA = choix.nextInt((9 - 0) + 1) + 0;
							int yIA = choix.nextInt((9 - 0) + 1) + 0;
							if (this.plateauJ[xIA][yIA] < 8) {
								if (this.plateauJ[xIA][yIA] == 0 || this.plateauJ[xIA][yIA] == 1) {
									this.plateauJ[xIA][yIA] = 9;
								} else {
									toucherJ(this.plateauJ[xIA][yIA]);
									this.plateauJ[xIA][yIA] = 8;
									toucher = true;
								}
								if (xIA - 1 < 10 && xIA - 1 >= 0 && this.plateauJ[xIA - 1][yIA] < 8) {
									if ((this.plateauJ[xIA - 1][yIA] == 0 || this.plateauJ[xIA - 1][yIA] == 1)) {
										this.plateauJ[xIA - 1][yIA] = 9;
									} else {
										toucherJ(this.plateauJ[xIA - 1][yIA]);
										this.plateauJ[xIA - 1][yIA] = 8;
										toucher = true;
									}
								}
								if (xIA + 1 < 10 && xIA + 1 >= 0 && this.plateauJ[xIA + 1][yIA] < 8) {
									if (this.plateauJ[xIA + 1][yIA] == 0 || this.plateauJ[xIA + 1][yIA] == 1) {
										this.plateauJ[xIA + 1][yIA] = 9;
									} else {
										toucherJ(this.plateauJ[xIA + 1][yIA]);
										this.plateauJ[xIA + 1][yIA] = 8;
										toucher = true;
									}
								}
								if (yIA - 1 < 10 && yIA - 1 >= 0 && this.plateauJ[xIA][yIA - 1] < 8) {
									if (this.plateauJ[xIA][yIA - 1] == 0 || this.plateauJ[xIA][yIA - 1] == 1) {
										this.plateauJ[xIA][yIA - 1] = 9;
									} else {
										toucherJ(this.plateauJ[xIA][yIA - 1]);
										this.plateauJ[xIA][yIA - 1] = 8;
										toucher = true;
									}
								}
								if (yIA + 1 < 10 && yIA + 1 >= 0 && this.plateauJ[xIA][yIA + 1] < 8) {
									if (this.plateauJ[xIA][yIA + 1] == 0 || this.plateauJ[xIA][yIA + 1] == 1) {
										this.plateauJ[xIA][yIA + 1] = 9;
									} else {
										toucherJ(this.plateauJ[xIA][yIA + 1]);
										this.plateauJ[xIA][yIA + 1] = 8;
										toucher = true;
									}
								}
								dejaToucher = false;
							}
						}
						this.IA.croix = false;
						Ajouer = false;
					}
				}
			}
			if (toucher) {
				this.infoJoueur.setText("Touché !");
			} else
				this.infoJoueur.setText("Raté !");
			this.joueur = 1;
		} else if (this.joueur == 1) {
			boolean Ajouer = true;
			while (Ajouer) {
				if (this.BombeJoueur == 1) {
					boolean dejaToucher = true;
					while (dejaToucher) {
						if (this.plateauIA[x][y] < 8) {
							if (this.plateauIA[x][y] == 0 || this.plateauIA[x][y] == 1) {
								this.plateauIA[x][y] = 9;
							} else {
								toucherIA(this.plateauIA[x][y]);
								this.plateauIA[x][y] = 8;
								toucher = true;
							}
							dejaToucher = false;
						}
					}
					Ajouer = false;
				}
				if (this.BombeJoueur == 2) {
					if (this.J.verticale == true) {
						for (int i = 0; i < 10; i++) {
							if (this.plateauIA[i][y] >= 2 && this.plateauIA[i][y] <= 6) {
								toucherIA(this.plateauIA[i][y]);
								this.plateauIA[i][y] = 8;
								toucher = true;
							} else if (this.plateauIA[i][y] != 8)
								this.plateauIA[i][y] = 9;
						}
						this.J.verticale = false;
						this.J.butVerticale.setEnabled(false);
						this.J.butClassique.setSelected(true);
						this.BombeJoueur = 1;
						Ajouer = false;
					}
				}
				if (this.BombeJoueur == 3) {
					if (this.J.horizontale == true) {
						for (int i = 0; i < 10; i++) {
							if (this.plateauIA[x][i] >= 2 && this.plateauIA[x][i] <= 6) {
								toucherIA(this.plateauIA[x][i]);
								this.plateauIA[x][i] = 8;
								toucher = true;
							} else if (this.plateauIA[x][i] != 8)
								this.plateauIA[x][i] = 9;
						}
						this.J.horizontale = false;
						this.J.butHorizontale.setEnabled(false);
						this.J.butClassique.setSelected(true);
						this.BombeJoueur = 1;
						Ajouer = false;
					}
				}
				if (this.BombeJoueur == 4) {
					if (this.J.croix == true) {
						boolean dejaToucher = true;
						while (dejaToucher) {
							if (this.plateauIA[x][y] < 8) {
								if (this.plateauIA[x][y] == 0 || this.plateauIA[x][y] == 1) {
									this.plateauIA[x][y] = 9;
								} else {
									toucherIA(this.plateauIA[x][y]);
									this.plateauIA[x][y] = 8;
									toucher = true;
								}
								if (x - 1 < 10 && x - 1 >= 0 && this.plateauIA[x - 1][y] < 8) {
									if ((this.plateauIA[x - 1][y] == 0 || this.plateauIA[x - 1][y] == 1)) {
										this.plateauIA[x - 1][y] = 9;
									} else {
										toucherIA(this.plateauIA[x - 1][y]);
										this.plateauIA[x - 1][y] = 8;
										toucher = true;
									}
								}
								if (x + 1 < 10 && x + 1 >= 0 && this.plateauIA[x + 1][y] < 8) {
									if (this.plateauIA[x + 1][y] == 0 || this.plateauIA[x + 1][y] == 1) {
										this.plateauIA[x + 1][y] = 9;
									} else {
										toucherIA(this.plateauIA[x + 1][y]);
										this.plateauIA[x + 1][y] = 8;
										toucher = true;
									}
								}
								if (y - 1 < 10 && y - 1 >= 0 && this.plateauIA[x][y - 1] < 8) {
									if (this.plateauIA[x][y - 1] == 0 || this.plateauIA[x][y - 1] == 1) {
										this.plateauIA[x][y - 1] = 9;
									} else {
										toucherIA(this.plateauIA[x][y - 1]);
										this.plateauIA[x][y - 1] = 8;
										toucher = true;
									}
								}
								if (y + 1 < 10 && y + 1 >= 0 && this.plateauIA[x][y + 1] < 8) {
									if (this.plateauIA[x][y + 1] == 0 || this.plateauIA[x][y + 1] == 1) {
										this.plateauIA[x][y + 1] = 9;
									} else {
										toucherIA(this.plateauIA[x][y + 1]);
										this.plateauIA[x][y + 1] = 8;
										toucher = true;
									}
								}
								dejaToucher = false;
							}
						}
						this.J.croix = false;
						this.J.butCroix.setEnabled(false);
						this.J.butClassique.setSelected(true);
						this.BombeJoueur = 1;
						Ajouer = false;
					}
				}
			}
			if (toucher) {
				this.infoIA.setText("Touché !");
			} else
				this.infoIA.setText("Raté !");
			this.joueur = 0;
		}
	}
	
	/**
	 * Cette méthode consiste à afficher un message lorsque un bateau à été couler après avoir été toucher.
	 * Elle permet aussi de compter le nombre restant de bateau au joueur.
	 * 
	 * @param id permet d'identifier le bateau toucher
	 */

	protected void toucherJ(int id) {
		if (this.porteAvionJ.id == id) {
			if (this.porteAvionJ.taille != 0) {
				this.porteAvionJ.taille -= 1;
				if (this.porteAvionJ.taille == 0) {
					this.infoCoulerJoueur.setText("Porte Avion allier couler !");
					this.nbBateauJoueur -= 1;
				}
			}
		}
		if (this.CroiseurJ.id == id) {
			if (this.CroiseurJ.taille != 0) {
				this.CroiseurJ.taille -= 1;
				if (this.CroiseurJ.taille == 0) {
					this.infoCoulerJoueur.setText("Croiseur allier couler !");
					this.nbBateauJoueur -= 1;
				}
			}
		}
		if (this.contreTorpilleur1J.id == id) {
			if (this.contreTorpilleur1J.taille != 0) {
				this.contreTorpilleur1J.taille -= 1;
				if (this.contreTorpilleur1J.taille == 0) {
					this.infoCoulerJoueur.setText("Contre Torpilleur allier couler !");
					this.nbBateauJoueur -= 1;
				}
			}
		}
		if (this.contreTorpilleur2J.id == id) {
			if (this.contreTorpilleur2J.taille != 0) {
				this.contreTorpilleur2J.taille -= 1;
				if (this.contreTorpilleur2J.taille == 0) {
					this.infoCoulerJoueur.setText("Contre Torpilleur allier couler !");
					this.nbBateauJoueur -= 1;
				}
			}
		}
		if (this.torpilleurJ.id == id) {
			if (this.torpilleurJ.taille != 0) {
				this.torpilleurJ.taille -= 1;
				if (this.torpilleurJ.taille == 0) {
					this.infoCoulerJoueur.setText("Torpilleur allier couler !");
					this.nbBateauJoueur -= 1;
				}
			}
		}
	}
	
	/**
	 * Cette méthode consiste à afficher un message lorsque un bateau à été couler après avoir été toucher.
	 * Elle permet aussi de compter le nombre restant de bateau à l'IA.
	 * 
	 * @param id permet d'identifier le bateau toucher
	 */

	protected void toucherIA(int id) {
		if (this.porteAvionIA.id == id) {
			if (this.porteAvionIA.taille != 0) {
				this.porteAvionIA.taille -= 1;
				if (this.porteAvionIA.taille == 0) {
					this.infoCoulerIA.setText("Porte Avion ennemi couler !");
					this.nbBateauIA -= 1;
				}
			}
		}
		if (this.CroiseurIA.id == id) {
			if (this.CroiseurIA.taille != 0) {
				this.CroiseurIA.taille -= 1;
				if (this.CroiseurIA.taille == 0) {
					this.infoCoulerIA.setText("Croiseur ennemi couler !");
					this.nbBateauIA -= 1;
				}
			}
		}
		if (this.contreTorpilleur1IA.id == id) {
			if (this.contreTorpilleur1IA.taille != 0) {
				this.contreTorpilleur1IA.taille -= 1;
				if (this.contreTorpilleur1IA.taille == 0) {
					this.infoCoulerIA.setText("Contre Torpilleur ennemi couler !");
					this.nbBateauIA -= 1;
				}
			}
		}
		if (this.contreTorpilleur2IA.id == id) {
			if (this.contreTorpilleur2IA.taille != 0) {
				this.contreTorpilleur2IA.taille -= 1;
				if (this.contreTorpilleur2IA.taille == 0) {
					this.infoCoulerIA.setText("Contre Torpilleur ennemi couler !");
					this.nbBateauIA -= 1;
				}
			}
		}
		if (this.torpilleurIA.id == id) {
			if (this.torpilleurIA.taille != 0) {
				this.torpilleurIA.taille -= 1;
				if (this.torpilleurIA.taille == 0) {
					this.infoCoulerIA.setText("Torpilleur ennemi couler !");
					this.nbBateauIA -= 1;
				}
			}
		}
	}
	/**
	 * Méthode qui regarde si la partie est terminé ou non
	 * 
	 * @return vrai si un des joueurs à gagner la partie sinon faux
	 */

	protected boolean CheckVictory() {
		if (this.nbBateauJoueur == 0 || this.nbBateauIA == 0) {
			return true;
		} else
			return false;
	}
}
