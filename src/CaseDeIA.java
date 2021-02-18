
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;

@SuppressWarnings("serial")
public class CaseDeIA implements Serializable {
	protected JButton but;
	protected Jeu j;
	protected int x;
	protected int y;
	/**
	 * Ce constructeur fait en sorte que lorsque le joueur joue sur la case adverse la methode jouer du jeu soit lancer.
	 * L'IA joue instantanement après.
	 * Les plateaux sont actualisé après chaque coup.
	 * Après chaque tour la methode checkVictory verifie si le dernier coup était gagnant ou pas.
	 * @param x1 coordonnée x de la case touché par le joueur
	 * @param y1 coordonnée y de la case touché par le joueur
	 * @param j Jeu associé à la case
	 */
	protected CaseDeIA(int x1, int y1 , Jeu j) {
		this.x = x1;
		this.y = y1;
		this.j = j;
		this.but = new JButton();
		this.but.setPreferredSize(new Dimension(50, 50));
		this.but.setEnabled(true);
		this.but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				j.infoCoulerIA.setText(" ");
				j.infoCoulerJoueur.setText(" ");
				j.jouer(x, y);
				for (int h = 0; h < 10; ++h) {
					for (int i = 0; i < 10; ++i) {
						if (j.plateauIA[h][i] == 9) {
							j.CaseIA[h][i].but.setText("X");
							j.CaseIA[h][i].but.setEnabled(false);
						} else if (j.plateauIA[h][i] == 8) {
							j.CaseIA[h][i].but.setBackground(Color.RED);
							j.CaseIA[h][i].but.setEnabled(false);
						}
					}
				}
				if (!j.CheckVictory()) {
					j.jouer(x, y);
					for (int h = 0; h < 10; ++h) {
						for (int i = 0; i < 10; ++i) {
							if (j.plateauJ[h][i] == 9) {
								j.CaseJoueur[h][i].but.setText("X");
							} else if (j.plateauJ[h][i] == 8) {
								j.CaseJoueur[h][i].but.setBackground(Color.RED);
							}
						}
					}
					if (j.CheckVictory()) {
						for (int h = 0; h < 10; ++h) {
							for (int i = 0; i < 10; ++i) {
								j.CaseIA[h][i].but.setEnabled(false);
								j.victoire = "Vous avez perdu ...";
								j.Winner.setText(j.victoire);
								j.Fin.setVisible(true);
							}
						}
					}
				} else {
					for (int h = 0; h < 10; ++h) {
						for (int i = 0; i < 10; ++i) {
							j.CaseIA[h][i].but.setEnabled(false);
							j.victoire = "Vous avez gagnez !";
							j.Winner.setText(j.victoire);
							j.Fin.setVisible(true);
						}
					}

				}
			}

		});

	}
}
