import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;

@SuppressWarnings("serial")
public class CaseDuJoueur implements Serializable {
	protected JButton but;
	protected Jeu j;
	protected int x;
	protected int y;
	/**
	 * Lorsque le joueur place ces bateaux : il clique sur une de ces cases et le place si c'est possible grâce à la méthode
	 * createBateauJoueur().Lorsque le bateau est placé dans la grille, le plateau du joueur est actualiser et les cases où
	 * viens d'être placé le bateau passent en vert et ne sont plus cliquables.Une fois que tous les bateaux seront placé
	 * j.bat sera égal à 6 et le joueur n'aura plus accès à son plateau mais à celui de son adversaire pour débuter l'attaque.
	 * @param x1 coordonées x de la case
	 * @param y1 coordonées x de la case
	 * @param j Jeu associé à la case
	 */
	protected CaseDuJoueur(int x1, int y1, Jeu j) {
		this.x = x1;
		this.y = y1;
		this.j = j;
		this.but = new JButton();
		this.but.setPreferredSize(new Dimension(50, 50));
		this.but.setEnabled(true);

		if (j.bat != 6) {
			this.but.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evenement) {
					int verti;
					if (j.Sens == "               Sens du bateau : Verticale") {
						verti = 1;
					} else {
						verti = 0;
					}
					j.createBateauJoueur(x, y, verti);
					if (j.placementOK) {
						but.setEnabled(false);
						for (int h = 0; h < 10; ++h) {
							for (int i = 0; i < 10; ++i) {
								if (j.plateauJ[h][i] != 0 && j.plateauJ[h][i] != 1) {
									j.CaseJoueur[h][i].but.setBackground(Color.GREEN);
									j.CaseJoueur[h][i].but.setEnabled(false);
									changePlacement(j.bat);
								}
							}
						}
					} else
						++j.bat;
					if (j.bat == 6) {
						for (int h = 0; h < 10; ++h) {
							for (int i = 0; i < 10; ++i) {
								j.CaseJoueur[h][i].but.setEnabled(false);
								j.CaseIA[h][i].but.setEnabled(true);
							}
						}
					}
				}
			});
		}
	}
    /**
     * @param bat correspond au n-ième bateau qui doit être placé afin de modifier l'affichage informatif pour le joueur
     */
	protected void changePlacement(int bat) { 
		if (bat == 2) {
			j.infoJoueur.setText("Placez votre Croiseur ! (4 cases)");
		} else if (bat == 3) {
			j.infoJoueur.setText("Placez votre Contre-Torpilleur n°1 ! (3 cases)");
		} else if (bat == 4) {
			j.infoJoueur.setText("Placez votre Contre-Torpilleur n°2 ! (3 cases)");
		} else if (bat == 5) {
			j.infoJoueur.setText("Placez votre Torpilleur ! (2 cases)");
		} else if (bat == 6) {
			j.infoJoueur.setText(" ");
		}
	}
}
