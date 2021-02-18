import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;

public class BatailleNavale {
	/**
	 * Méthode principale qui gère l'affichage/interface et les actions sur les boutons(excepté les cases) ou encore sur le mouvement de la molette de la souris. 
	 * @throws IOException Envoie une erreur en cas de problème liée au fichier de sauvegarde
	 */
	public static void main(String[] args) throws IOException {

		JFrame start = new JFrame();
		start.setIconImage(Toolkit.getDefaultToolkit().getImage("IconeProjet.png"));
		start.setSize(410, 200);
		start.setLayout(new GridBagLayout());
		GridBagConstraints gdc = new GridBagConstraints();
		start.setTitle("Bienvenue sur le jeu de la Bataille Navale !");
		start.getContentPane().setBackground(Color.CYAN);

		JLabel Choix = new JLabel("        Que voulez vous faire ?");
		gdc.gridx = 0;
		gdc.gridy = 0;
		gdc.gridwidth = 3;
		start.add(Choix, gdc);

		gdc.gridy = 1;
		gdc.gridx = 1;
		gdc.gridwidth = 1;
		start.add(new JLabel("        "), gdc);

		JButton Charger = new JButton("Charger une partie");
		gdc.gridx = 0;
		gdc.gridy = 2;
		gdc.gridwidth = 1;
		start.add(Charger, gdc);

		gdc.gridy = 2;
		gdc.gridx = 1;
		gdc.gridwidth = 7;
		start.add(new JLabel("        "), gdc);

		JButton New = new JButton("Nouvelle partie");
		gdc.gridx = 2;
		gdc.gridy = 2;
		gdc.gridwidth = 1;
		start.add(New, gdc);

		start.setVisible(true);
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				start.setVisible(false);
				Jeu j = new Jeu();
				JFrame NewGame = new JFrame();
				NewGame.getContentPane().setBackground(new Color(51, 153, 255));
				GridBagConstraints gdc = new GridBagConstraints();
				NewGame.setTitle("A l'attaque !");
				NewGame.setIconImage(Toolkit.getDefaultToolkit().getImage("IconeProjet.png"));
				JPanel Plateau1 = new JPanel();
				JPanel Plateau2 = new JPanel();
				JPanel espace1 = new JPanel();
				JPanel espace2 = new JPanel();
				JPanel BombesJoueur = new JPanel();
				JPanel LignesJ = new JPanel();
				JPanel ColonnesJoueur = new JPanel();
				JPanel LignesIA = new JPanel();
				JPanel ColonnesIA = new JPanel();
				JPanel InfoJ = new JPanel();
				JPanel InfoIA = new JPanel();
				JPanel InfoCoulerJ = new JPanel();
				JPanel InfoCoulerIA = new JPanel();
				ButtonGroup bg = new ButtonGroup();

				Box box = Box.createVerticalBox();
				bg.add(j.J.butClassique);
				bg.add(j.J.butHorizontale);
				bg.add(j.J.butVerticale);
				bg.add(j.J.butCroix);
				j.J.butClassique.setBackground(Color.ORANGE);
				j.J.butHorizontale.setBackground(Color.ORANGE);
				j.J.butVerticale.setBackground(Color.ORANGE);
				j.J.butCroix.setBackground(Color.ORANGE);

				NewGame.setSize(1500, 800);

				NewGame.setLayout(new GridBagLayout());
				Plateau1.setLayout(new GridLayout(10, 10));
				Plateau2.setLayout(new GridLayout(10, 10));
				espace1.setLayout(new FlowLayout());
				espace2.setLayout(new FlowLayout());
				BombesJoueur.setLayout(new GridLayout(4, 1));
				BombesJoueur.setPreferredSize(new Dimension(250, 450));
				LignesJ.setLayout(new GridLayout(10, 1));
				LignesJ.setPreferredSize(new Dimension(20, 500));
				ColonnesJoueur.setLayout(new GridLayout(1, 10));
				ColonnesJoueur.setPreferredSize(new Dimension(500, 20));
				LignesIA.setLayout(new GridLayout(10, 1));
				LignesIA.setPreferredSize(new Dimension(20, 500));
				ColonnesIA.setLayout(new GridLayout(1, 10));
				ColonnesIA.setPreferredSize(new Dimension(500, 20));
				InfoJ.setLayout(new GridLayout(1, 1));
				InfoJ.setPreferredSize(new Dimension(500, 40));
				InfoIA.setLayout(new GridLayout(1, 1));
				InfoIA.setPreferredSize(new Dimension(500, 40));
				InfoCoulerJ.setLayout(new GridLayout(1, 1));
				InfoCoulerJ.setPreferredSize(new Dimension(500, 40));
				InfoCoulerIA.setLayout(new GridLayout(1, 1));
				InfoCoulerIA.setPreferredSize(new Dimension(500, 40));

				ColonnesIA.setBackground(Color.YELLOW);
				ColonnesJoueur.setBackground(Color.YELLOW);
				LignesIA.setBackground(Color.YELLOW);
				LignesJ.setBackground(Color.YELLOW);

				espace1.add(new JLabel("                   "));
				espace1.setBackground((new Color(51, 153, 255)));
				espace2.add(new JLabel("                    "));
				espace2.setBackground((new Color(51, 153, 255)));

				LignesJ.add(new JLabel("  1"));
				LignesJ.add(new JLabel("  2"));
				LignesJ.add(new JLabel("  3"));
				LignesJ.add(new JLabel("  4"));
				LignesJ.add(new JLabel("  5"));
				LignesJ.add(new JLabel("  6"));
				LignesJ.add(new JLabel("  7"));
				LignesJ.add(new JLabel("  8"));
				LignesJ.add(new JLabel("  9"));
				LignesJ.add(new JLabel(" 10"));

				ColonnesJoueur.add(new JLabel("       A"));
				ColonnesJoueur.add(new JLabel("       B"));
				ColonnesJoueur.add(new JLabel("       C"));
				ColonnesJoueur.add(new JLabel("       D"));
				ColonnesJoueur.add(new JLabel("       E"));
				ColonnesJoueur.add(new JLabel("       F"));
				ColonnesJoueur.add(new JLabel("       G"));
				ColonnesJoueur.add(new JLabel("       H"));
				ColonnesJoueur.add(new JLabel("       I"));
				ColonnesJoueur.add(new JLabel("       J"));

				LignesIA.add(new JLabel("  1"));
				LignesIA.add(new JLabel("  2"));
				LignesIA.add(new JLabel("  3"));
				LignesIA.add(new JLabel("  4"));
				LignesIA.add(new JLabel("  5"));
				LignesIA.add(new JLabel("  6"));
				LignesIA.add(new JLabel("  7"));
				LignesIA.add(new JLabel("  8"));
				LignesIA.add(new JLabel("  9"));
				LignesIA.add(new JLabel(" 10"));

				ColonnesIA.add(new JLabel("       A"));
				ColonnesIA.add(new JLabel("       B"));
				ColonnesIA.add(new JLabel("       C"));
				ColonnesIA.add(new JLabel("       D"));
				ColonnesIA.add(new JLabel("       E"));
				ColonnesIA.add(new JLabel("       F"));
				ColonnesIA.add(new JLabel("       G"));
				ColonnesIA.add(new JLabel("       H"));
				ColonnesIA.add(new JLabel("       I"));
				ColonnesIA.add(new JLabel("       J"));

				j.infoJoueur.setFont(new Font("Arial", Font.BOLD, 20));
				j.infoIA.setFont(new Font("Arial", Font.BOLD, 20));
				j.infoJoueur.setHorizontalAlignment(JLabel.CENTER);
				j.infoIA.setHorizontalAlignment(JLabel.CENTER);
				j.infoJoueur.setForeground(Color.WHITE);
				j.infoIA.setForeground(Color.WHITE);
				InfoJ.setBackground(Color.DARK_GRAY);
				InfoIA.setBackground(Color.DARK_GRAY);
				InfoJ.add(j.infoJoueur);
				InfoIA.add(j.infoIA);

				j.infoCoulerJoueur.setFont(new Font("Arial", Font.BOLD, 20));
				j.infoCoulerIA.setFont(new Font("Arial", Font.BOLD, 20));
				j.infoCoulerJoueur.setHorizontalAlignment(JLabel.CENTER);
				j.infoCoulerIA.setHorizontalAlignment(JLabel.CENTER);
				j.infoCoulerJoueur.setForeground(Color.WHITE);
				j.infoCoulerIA.setForeground(Color.WHITE);
				InfoCoulerJ.setBackground(Color.DARK_GRAY);
				InfoCoulerIA.setBackground(Color.DARK_GRAY);
				InfoCoulerJ.add(j.infoCoulerJoueur);
				InfoCoulerIA.add(j.infoCoulerIA);

				gdc.gridx = 3;
				gdc.gridy = 0;
				NewGame.add(ColonnesJoueur, gdc);
				gdc.gridx = 6;
				gdc.gridy = 0;
				NewGame.add(ColonnesIA, gdc);
				gdc.gridx = 0;
				gdc.gridy = 1;
				NewGame.add(BombesJoueur, gdc);
				gdc.gridx = 1;
				gdc.gridy = 1;
				NewGame.add(espace1, gdc);
				gdc.gridx = 2;
				gdc.gridy = 1;
				NewGame.add(LignesJ, gdc);
				gdc.gridx = 3;
				gdc.gridy = 1;
				NewGame.add(Plateau1, gdc);
				gdc.gridx = 4;
				gdc.gridy = 1;
				NewGame.add(espace2, gdc);
				gdc.gridx = 5;
				gdc.gridy = 1;
				NewGame.add(LignesIA, gdc);
				gdc.gridx = 6;
				gdc.gridy = 1;
				NewGame.add(Plateau2, gdc);
				gdc.gridx = 0;
				gdc.gridy = 2;
				NewGame.add(new JLabel(" "), gdc);
				gdc.gridx = 3;
				gdc.gridy = 3;
				NewGame.add(InfoJ, gdc);
				gdc.gridx = 6;
				gdc.gridy = 3;
				NewGame.add(InfoIA, gdc);
				gdc.gridx = 0;
				gdc.gridy = 4;
				NewGame.add(new JLabel(" "), gdc);
				gdc.gridx = 3;
				gdc.gridy = 5;
				NewGame.add(InfoCoulerJ, gdc);
				gdc.gridx = 6;
				gdc.gridy = 5;
				NewGame.add(InfoCoulerIA, gdc);

				JLabel Bombes = new JLabel("Vos bombes à disposition !");
				BombesJoueur.add(Bombes);
				Bombes.setHorizontalAlignment(JLabel.CENTER);
				Bombes.setFont(new Font("Arial", Font.BOLD, 18));
				Bombes.setForeground(Color.RED);
				box.add(j.J.butClassique);
				box.add(j.J.butHorizontale);
				box.add(j.J.butVerticale);
				box.add(j.J.butCroix);
				BombesJoueur.add(box);
				Box save = Box.createVerticalBox();
				JButton Sauvegarder = new JButton("Sauvegarder et Quitter");
				JButton QuitterSansSave = new JButton("Quitter sans sauvegarder");
				JButton Reinitialiser = new JButton("Reinitialiser");
				Sauvegarder.setAlignmentX(Component.CENTER_ALIGNMENT);
				QuitterSansSave.setAlignmentX(Component.CENTER_ALIGNMENT);
				Reinitialiser.setAlignmentX(Component.CENTER_ALIGNMENT);
				save.add(Reinitialiser);
				save.add(new JLabel(" "));
				save.add(Sauvegarder);
				save.add(new JLabel(" "));
				save.add(QuitterSansSave);
				BombesJoueur.add(save);
				JLabel sens = new JLabel(j.Sens);
				BombesJoueur.setBackground(Color.ORANGE);
				j.J.butClassique.setSelected(true);

				j.J.butClassique.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						j.BombeJoueur = 1;
					}
				});
				j.J.butVerticale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						j.BombeJoueur = 2;
					}
				});
				j.J.butHorizontale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						j.BombeJoueur = 3;
					}
				});
				j.J.butCroix.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						j.BombeJoueur = 4;
					}
				});

				Box Souris = Box.createVerticalBox();
				JLabel InstructionSouris1 = new JLabel("    (Bougez la molette de votre souris pour ");
				JLabel InstructionSouris2 = new JLabel("       choisir le sens du bateau à placer)");
				Souris.add(InstructionSouris1);
				Souris.add(InstructionSouris2);
				Souris.add(new JLabel(" "));
				Souris.add(sens);
				BombesJoueur.add(Souris);

				NewGame.addMouseWheelListener(new MouseWheelListener() {
					public void mouseWheelMoved(MouseWheelEvent e) {
						int d = e.getWheelRotation();
						if (d < 0) {
							if (j.bat == 6)
								Souris.setVisible(false);
							j.Sens = "               Sens du bateau : Verticale";
							sens.setText(j.Sens);
						} else if (d > 0) {
							if (j.bat == 6)
								Souris.setVisible(false);
							j.Sens = "             Sens du bateau : Horizontale";
							sens.setText(j.Sens);
						}
					}
				});

				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 10; y++) {
						CaseDuJoueur c1 = new CaseDuJoueur(x, y, j);
						j.CaseJoueur[x][y] = c1;
						Plateau1.add(c1.but);
					}
				}

				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 10; y++) {
						CaseDeIA c2 = new CaseDeIA(x, y, j);
						j.CaseIA[x][y] = c2;
						Plateau2.add(c2.but);
						j.CaseIA[x][y].but.setEnabled(false);
					}
				}

				gdc.gridx = 0;
				gdc.gridy = 1;
				gdc.gridwidth = 1;
				j.Fin.add(new JLabel("        "), gdc);
				JButton Quitter = new JButton("Quitter");
				gdc.gridx = 0;
				gdc.gridy = 2;
				gdc.gridwidth = 1;
				j.Fin.add(Quitter, gdc);

				gdc.gridx = 1;
				gdc.gridy = 2;
				gdc.gridwidth = 1;
				j.Fin.add(new JLabel("        "), gdc);

				gdc.gridx = 2;
				gdc.gridy = 2;
				gdc.gridwidth = 1;
				j.Fin.add(New, gdc);

				j.Fin.getContentPane().setBackground(Color.CYAN);
				j.Fin.setIconImage(Toolkit.getDefaultToolkit().getImage("IconeProjet.png"));

				QuitterSansSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						NewGame.setVisible(false);
						System.exit(1);
					}
				});

				Quitter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						j.Fin.setVisible(false);
						NewGame.setVisible(false);
						System.exit(1);
					}
				});
				New.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						j.Fin.setVisible(false);
						NewGame.setVisible(false);
					}
				});
				Reinitialiser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						JFrame rein = new JFrame();
						rein.setSize(410, 200);
						rein.setLayout(new GridBagLayout());
						GridBagConstraints gdc = new GridBagConstraints();
						rein.setTitle("");
						rein.getContentPane().setBackground(Color.CYAN);

						JLabel Choix = new JLabel("Etes vous sur de vouloir réinitialiser ?");
						gdc.gridx = 0;
						gdc.gridy = 0;
						gdc.gridwidth = 4;
						rein.add(Choix, gdc);

						gdc.gridx = 0;
						gdc.gridy = 1;
						gdc.gridwidth = 1;
						rein.add(new JLabel("        "), gdc);

						New.setText("Oui");
						gdc.gridx = 1;
						gdc.gridy = 2;
						gdc.gridwidth = 1;
						rein.add(New, gdc);

						gdc.gridx = 2;
						gdc.gridy = 2;
						gdc.gridwidth = 1;
						rein.add(new JLabel("        "), gdc);

						JButton Return = new JButton("Non");
						gdc.gridx = 3;
						gdc.gridy = 2;
						gdc.gridwidth = 1;
						rein.add(Return, gdc);

						Return.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evenement) {
								rein.setVisible(false);
							}
						});
						New.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evenement) {
								rein.setVisible(false);
							}
						});
						rein.setVisible(true);
						rein.setLocationRelativeTo(null);
						rein.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				});
				NewGame.setVisible(true);
				NewGame.setLocationRelativeTo(null);
				NewGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.Fin.setLocationRelativeTo(null);
				j.Fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Sauvegarder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evenement) {
						try {
							FileOutputStream fichier = null;
							fichier = new FileOutputStream(new File("Sauvegarde.txt"));
							ObjectOutputStream JeuSauvegarder = new ObjectOutputStream(fichier);

							JeuSauvegarder.writeObject(j);

							JeuSauvegarder.close();
							fichier.close();
							NewGame.setVisible(false);
							System.exit(1);
						} catch (FileNotFoundException e) {
							System.out.println("File not found");
						} catch (IOException e) {
							System.out.println("Error initializing stream");
						}
					}
				});

			}
		});
		Charger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				try {
					start.setVisible(false);
					FileInputStream fichier = new FileInputStream("Sauvegarde.txt");
					ObjectInputStream ChargerJeu = new ObjectInputStream(fichier);
					Jeu jSave = (Jeu) ChargerJeu.readObject();
					jSave.victoire = "";
					JFrame PreGame = new JFrame();
					PreGame.getContentPane().setBackground(new Color(51, 153, 255));
					GridBagConstraints gdc = new GridBagConstraints();
					PreGame.setTitle("A l'attaque !");
					PreGame.setIconImage(Toolkit.getDefaultToolkit().getImage("IconeProjet.png"));
					JPanel Plateau1 = new JPanel();
					JPanel Plateau2 = new JPanel();
					JPanel espace1 = new JPanel();
					JPanel espace2 = new JPanel();
					JPanel BombesJoueur = new JPanel();
					JPanel LignesJ = new JPanel();
					JPanel ColonnesJoueur = new JPanel();
					JPanel LignesIA = new JPanel();
					JPanel ColonnesIA = new JPanel();
					JPanel InfoJ = new JPanel();
					JPanel InfoIA = new JPanel();
					JPanel InfoCoulerJ = new JPanel();
					JPanel InfoCoulerIA = new JPanel();
					ButtonGroup bg = new ButtonGroup();

					Box box = Box.createVerticalBox();
					bg.add(jSave.J.butClassique);
					bg.add(jSave.J.butHorizontale);
					bg.add(jSave.J.butVerticale);
					bg.add(jSave.J.butCroix);

					PreGame.setSize(1500, 800);

					PreGame.setLayout(new GridBagLayout());
					Plateau1.setLayout(new GridLayout(10, 10));
					Plateau2.setLayout(new GridLayout(10, 10));
					espace1.setLayout(new FlowLayout());
					espace2.setLayout(new FlowLayout());
					BombesJoueur.setLayout(new GridLayout(4, 1));
					BombesJoueur.setPreferredSize(new Dimension(250, 450));
					LignesJ.setLayout(new GridLayout(10, 1));
					LignesJ.setPreferredSize(new Dimension(20, 500));
					ColonnesJoueur.setLayout(new GridLayout(1, 10));
					ColonnesJoueur.setPreferredSize(new Dimension(500, 20));
					LignesIA.setLayout(new GridLayout(10, 1));
					LignesIA.setPreferredSize(new Dimension(20, 500));
					ColonnesIA.setLayout(new GridLayout(1, 10));
					ColonnesIA.setPreferredSize(new Dimension(500, 20));
					InfoJ.setLayout(new GridLayout(1, 1));
					InfoJ.setPreferredSize(new Dimension(500, 40));
					InfoIA.setLayout(new GridLayout(1, 1));
					InfoIA.setPreferredSize(new Dimension(500, 40));
					InfoCoulerJ.setLayout(new GridLayout(1, 1));
					InfoCoulerJ.setPreferredSize(new Dimension(500, 40));
					InfoCoulerIA.setLayout(new GridLayout(1, 1));
					InfoCoulerIA.setPreferredSize(new Dimension(500, 40));

					ColonnesIA.setBackground(Color.YELLOW);
					ColonnesJoueur.setBackground(Color.YELLOW);
					LignesIA.setBackground(Color.YELLOW);
					LignesJ.setBackground(Color.YELLOW);

					espace1.add(new JLabel("                   "));
					espace1.setBackground((new Color(51, 153, 255)));
					espace2.add(new JLabel("                    "));
					espace2.setBackground((new Color(51, 153, 255)));

					LignesJ.add(new JLabel("1"));
					LignesJ.add(new JLabel("2"));
					LignesJ.add(new JLabel("3"));
					LignesJ.add(new JLabel("4"));
					LignesJ.add(new JLabel("5"));
					LignesJ.add(new JLabel("6"));
					LignesJ.add(new JLabel("7"));
					LignesJ.add(new JLabel("8"));
					LignesJ.add(new JLabel("9"));
					LignesJ.add(new JLabel("10"));

					ColonnesJoueur.add(new JLabel("       A"));
					ColonnesJoueur.add(new JLabel("       B"));
					ColonnesJoueur.add(new JLabel("       C"));
					ColonnesJoueur.add(new JLabel("       D"));
					ColonnesJoueur.add(new JLabel("       E"));
					ColonnesJoueur.add(new JLabel("       F"));
					ColonnesJoueur.add(new JLabel("       G"));
					ColonnesJoueur.add(new JLabel("       H"));
					ColonnesJoueur.add(new JLabel("       I"));
					ColonnesJoueur.add(new JLabel("       J"));

					LignesIA.add(new JLabel("1"));
					LignesIA.add(new JLabel("2"));
					LignesIA.add(new JLabel("3"));
					LignesIA.add(new JLabel("4"));
					LignesIA.add(new JLabel("5"));
					LignesIA.add(new JLabel("6"));
					LignesIA.add(new JLabel("7"));
					LignesIA.add(new JLabel("8"));
					LignesIA.add(new JLabel("9"));
					LignesIA.add(new JLabel("10"));

					ColonnesIA.add(new JLabel("       A"));
					ColonnesIA.add(new JLabel("       B"));
					ColonnesIA.add(new JLabel("       C"));
					ColonnesIA.add(new JLabel("       D"));
					ColonnesIA.add(new JLabel("       E"));
					ColonnesIA.add(new JLabel("       F"));
					ColonnesIA.add(new JLabel("       G"));
					ColonnesIA.add(new JLabel("       H"));
					ColonnesIA.add(new JLabel("       I"));
					ColonnesIA.add(new JLabel("       J"));

					jSave.infoJoueur.setFont(new Font("Arial", Font.BOLD, 20));
					jSave.infoIA.setFont(new Font("Arial", Font.BOLD, 20));
					jSave.infoJoueur.setHorizontalAlignment(JLabel.CENTER);
					jSave.infoIA.setHorizontalAlignment(JLabel.CENTER);
					jSave.infoJoueur.setForeground(Color.WHITE);
					jSave.infoIA.setForeground(Color.WHITE);
					InfoJ.setBackground(Color.DARK_GRAY);
					InfoIA.setBackground(Color.DARK_GRAY);
					InfoJ.add(jSave.infoJoueur);
					InfoIA.add(jSave.infoIA);

					jSave.infoCoulerJoueur.setFont(new Font("Arial", Font.BOLD, 20));
					jSave.infoCoulerIA.setFont(new Font("Arial", Font.BOLD, 20));
					jSave.infoCoulerJoueur.setHorizontalAlignment(JLabel.CENTER);
					jSave.infoCoulerIA.setHorizontalAlignment(JLabel.CENTER);
					jSave.infoCoulerJoueur.setForeground(Color.WHITE);
					jSave.infoCoulerIA.setForeground(Color.WHITE);
					InfoCoulerJ.setBackground(Color.DARK_GRAY);
					InfoCoulerIA.setBackground(Color.DARK_GRAY);
					InfoCoulerJ.add(jSave.infoCoulerJoueur);
					InfoCoulerIA.add(jSave.infoCoulerIA);

					gdc.gridx = 3;
					gdc.gridy = 0;
					PreGame.add(ColonnesJoueur, gdc);
					gdc.gridx = 6;
					gdc.gridy = 0;
					PreGame.add(ColonnesIA, gdc);
					gdc.gridx = 0;
					gdc.gridy = 1;
					PreGame.add(BombesJoueur, gdc);
					gdc.gridx = 1;
					gdc.gridy = 1;
					PreGame.add(espace1, gdc);
					gdc.gridx = 2;
					gdc.gridy = 1;
					PreGame.add(LignesJ, gdc);
					gdc.gridx = 3;
					gdc.gridy = 1;
					PreGame.add(Plateau1, gdc);
					gdc.gridx = 4;
					gdc.gridy = 1;
					PreGame.add(espace2, gdc);
					gdc.gridx = 5;
					gdc.gridy = 1;
					PreGame.add(LignesIA, gdc);
					gdc.gridx = 6;
					gdc.gridy = 1;
					PreGame.add(Plateau2, gdc);
					gdc.gridx = 0;
					gdc.gridy = 2;
					PreGame.add(new JLabel(" "), gdc);
					gdc.gridx = 3;
					gdc.gridy = 3;
					PreGame.add(InfoJ, gdc);
					gdc.gridx = 6;
					gdc.gridy = 3;
					PreGame.add(InfoIA, gdc);
					gdc.gridx = 0;
					gdc.gridy = 4;
					PreGame.add(new JLabel(" "), gdc);
					gdc.gridx = 3;
					gdc.gridy = 5;
					PreGame.add(InfoCoulerJ, gdc);
					gdc.gridx = 6;
					gdc.gridy = 5;
					PreGame.add(InfoCoulerIA, gdc);

					JLabel Bombes = new JLabel("Vos bombes à disposition !");
					BombesJoueur.add(Bombes);
					Bombes.setHorizontalAlignment(JLabel.CENTER);
					Bombes.setFont(new Font("Arial", Font.BOLD, 18));
					Bombes.setForeground(Color.RED);
					box.add(jSave.J.butClassique);
					box.add(jSave.J.butHorizontale);
					box.add(jSave.J.butVerticale);
					box.add(jSave.J.butCroix);
					BombesJoueur.add(box);
					Box save = Box.createVerticalBox();
					JButton Sauvegarder = new JButton("Sauvegarder et Quitter");
					JButton QuitterSansSave = new JButton("Quitter sans sauvegarder");
					JButton Reinitialiser = new JButton("Reinitialiser");
					Sauvegarder.setAlignmentX(Component.CENTER_ALIGNMENT);
					QuitterSansSave.setAlignmentX(Component.CENTER_ALIGNMENT);
					Reinitialiser.setAlignmentX(Component.CENTER_ALIGNMENT);
					save.add(Reinitialiser);
					save.add(new JLabel(" "));
					save.add(Sauvegarder);
					save.add(new JLabel(" "));
					save.add(QuitterSansSave);
					BombesJoueur.add(save);
					JLabel sens = new JLabel(jSave.Sens);
					BombesJoueur.add(sens);
					BombesJoueur.setBackground(Color.ORANGE);
					jSave.J.butClassique.setSelected(true);

					jSave.J.butClassique.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							jSave.BombeJoueur = 1;
						}
					});
					jSave.J.butVerticale.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							jSave.BombeJoueur = 2;
						}
					});
					jSave.J.butHorizontale.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							jSave.BombeJoueur = 3;
						}
					});
					jSave.J.butCroix.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							jSave.BombeJoueur = 4;
						}
					});

					Box Souris = Box.createVerticalBox();
					JLabel InstructionSouris1 = new JLabel("    (Bougez la molette de votre souris pour ");
					JLabel InstructionSouris2 = new JLabel("       choisir le sens du bateau à placer)");
					Souris.add(InstructionSouris1);
					Souris.add(InstructionSouris2);
					Souris.add(new JLabel(" "));
					Souris.add(sens);
					BombesJoueur.add(Souris);

					PreGame.addMouseWheelListener(new MouseWheelListener() {
						public void mouseWheelMoved(MouseWheelEvent e) {
							int d = e.getWheelRotation();
							if (d < 0) {
								if (jSave.bat == 6)
									Souris.setVisible(false);
								jSave.Sens = "               Sens du bateau : Verticale";
								sens.setText(jSave.Sens);
							} else if (d > 0) {
								if (jSave.bat == 6)
									Souris.setVisible(false);
								jSave.Sens = "             Sens du bateau : Horizontale";
								sens.setText(jSave.Sens);
							}
						}
					});

					for (int x = 0; x < 10; x++) {
						for (int y = 0; y < 10; y++) {
							CaseDuJoueur c1 = new CaseDuJoueur(x, y,jSave);
							jSave.CaseJoueur[x][y] = c1;
							Plateau1.add(c1.but);
							if (jSave.bat < 6)
								jSave.CaseJoueur[x][y].but.setEnabled(true);
							else
								jSave.CaseJoueur[x][y].but.setEnabled(false);
							if (jSave.plateauJ[x][y] >= 2 && jSave.plateauJ[x][y] <= 6) {
								jSave.CaseJoueur[x][y].but.setBackground(Color.GREEN);
								jSave.CaseJoueur[x][y].but.setEnabled(false);
							}
							if (jSave.plateauJ[x][y] == 8) {
								jSave.CaseJoueur[x][y].but.setBackground(Color.RED);
								jSave.CaseJoueur[x][y].but.setEnabled(false);
							}
							if (jSave.plateauJ[x][y] == 9) {
								jSave.CaseJoueur[x][y].but.setText("X");
								jSave.CaseJoueur[x][y].but.setEnabled(false);
							}
						}
					}

					for (int x = 0; x < 10; x++) {
						for (int y = 0; y < 10; y++) {
							CaseDeIA c2 = new CaseDeIA(x, y, jSave);
							jSave.CaseIA[x][y] = c2;
							Plateau2.add(c2.but);
							if (jSave.bat < 6)
								jSave.CaseIA[x][y].but.setEnabled(false);
							else
								jSave.CaseIA[x][y].but.setEnabled(true);
							if (jSave.plateauIA[x][y] == 8) {
								jSave.CaseIA[x][y].but.setBackground(Color.RED);
								jSave.CaseIA[x][y].but.setEnabled(false);
							}
							if (jSave.plateauIA[x][y] == 9) {
								jSave.CaseIA[x][y].but.setText("X");
								jSave.CaseIA[x][y].but.setEnabled(false);
							}
						}
					}

					QuitterSansSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							PreGame.setVisible(false);
							System.exit(1);
						}
					});

					jSave.Fin = new JFrame();
					jSave.Fin.setSize(410, 200);
					jSave.Fin.setLayout(new GridBagLayout());
					jSave.Fin.getContentPane().setBackground(Color.CYAN);
					jSave.Fin.setIconImage(Toolkit.getDefaultToolkit().getImage("IconeProjet.png"));
					jSave.Fin.setTitle("La partie est terminée !");
					jSave.Winner = new JLabel("        " + jSave.victoire);
					gdc.gridx = 0;
					gdc.gridy = 0;
					gdc.gridwidth = 3;
					jSave.Fin.add(jSave.Winner, gdc);
					jSave.Fin.setVisible(false);

					gdc.gridy = 1;
					gdc.gridx = 1;
					gdc.gridwidth = 1;
					jSave.Fin.add(new JLabel("        "), gdc);
					JButton Quitter = new JButton("Quitter");
					gdc.gridx = 0;
					gdc.gridy = 2;
					gdc.gridwidth = 1;
					jSave.Fin.add(Quitter, gdc);

					gdc.gridy = 2;
					gdc.gridx = 1;
					gdc.gridwidth = 7;
					jSave.Fin.add(new JLabel("        "), gdc);

					New.setText("Nouvelle Partie");
					gdc.gridx = 2;
					gdc.gridy = 2;
					gdc.gridwidth = 1;
					jSave.Fin.add(New, gdc);

					Quitter.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							jSave.Fin.setVisible(false);
							PreGame.setVisible(false);
							System.exit(1);
						}
					});

					New.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							jSave.Fin.setVisible(false);
							PreGame.setVisible(false);
						}
					});
					PreGame.setVisible(true);
					PreGame.setLocationRelativeTo(null);
					PreGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					jSave.Fin.setLocationRelativeTo(null);
					jSave.Fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					Sauvegarder.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							try {
								FileOutputStream fichier = null;
								fichier = new FileOutputStream(new File("Sauvegarde.txt"));
								ObjectOutputStream JeuSauvegarder = new ObjectOutputStream(fichier);

								JeuSauvegarder.writeObject(jSave);

								JeuSauvegarder.close();
								fichier.close();
								PreGame.setVisible(false);
								System.exit(1);
							} catch (FileNotFoundException e) {
								System.out.println("File not found");
							} catch (IOException e) {
								System.out.println("Error initializing stream");
							}
						}
					});
					ChargerJeu.close();
					Reinitialiser.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							JFrame rein = new JFrame();
							rein.setSize(410, 200);
							rein.setLayout(new GridBagLayout());
							GridBagConstraints gdc = new GridBagConstraints();
							rein.setTitle("");
							rein.getContentPane().setBackground(Color.CYAN);

							JLabel Choix = new JLabel("Etes vous sur de vouloir réinitialiser ?");
							gdc.gridx = 0;
							gdc.gridy = 0;
							gdc.gridwidth = 4;
							rein.add(Choix, gdc);

							gdc.gridx = 0;
							gdc.gridy = 1;
							gdc.gridwidth = 1;
							rein.add(new JLabel("        "), gdc);

							New.setText("Oui");
							gdc.gridx = 1;
							gdc.gridy = 2;
							gdc.gridwidth = 1;
							rein.add(New, gdc);

							gdc.gridx = 2;
							gdc.gridy = 2;
							gdc.gridwidth = 1;
							rein.add(new JLabel("        "), gdc);

							JButton Return = new JButton("Non");
							gdc.gridx = 3;
							gdc.gridy = 2;
							gdc.gridwidth = 1;
							rein.add(Return, gdc);

							Return.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evenement) {
									rein.setVisible(false);
								}
							});
							New.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evenement) {
									rein.setVisible(false);
								}
							});
							rein.setVisible(true);
							rein.setLocationRelativeTo(null);
							rein.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}
					});
				} catch (FileNotFoundException e) {
					JFrame start2 = new JFrame();
					start2.setIconImage(Toolkit.getDefaultToolkit().getImage("IconeProjet.png"));
					start2.setSize(450, 200);
					start2.setLayout(new GridBagLayout());
					GridBagConstraints gdc = new GridBagConstraints();
					start2.setTitle("Bienvenue sur le jeu de la Bataille Navale !");
					start2.getContentPane().setBackground(Color.CYAN);

					JLabel Choix = new JLabel("        Aucune sauvegarde existante, veuillez retourner au menu.");
					gdc.gridx = 0;
					gdc.gridy = 0;
					gdc.gridwidth = 3;
					start2.add(Choix, gdc);

					gdc.gridx = 0;
					gdc.gridy = 1;
					gdc.gridwidth = 1;
					start2.add(new JLabel("        "), gdc);

					JButton Menu = new JButton("Menu");
					gdc.gridx = 2;
					gdc.gridy = 2;
					gdc.gridwidth = 1;
					start2.add(Menu, gdc);
					Menu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evenement) {
							start2.setVisible(false);
							start.setVisible(true);
						}
					});

					start2.setVisible(true);
					start2.setLocationRelativeTo(null);
					start2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		start.setLocationRelativeTo(null);
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
