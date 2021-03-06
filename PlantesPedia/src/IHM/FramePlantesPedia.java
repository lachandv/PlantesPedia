package IHM;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;


public class FramePlantesPedia extends JFrame {
	
	List<String> tabType = new ArrayList<String>();
	boolean trouve = false;
	boolean suppr = false;
	
	JComboBox comboBoxTypeAjout = new JComboBox<String>();
	JComboBox comboBoxTypeSupprime = new JComboBox<String>();
	private static JTextField textFieldHabitantsMin;
	private static JTextField textFieldHabitantsMax;
	private static JTextField textFieldCodePostal;
	private static JTextField textFieldSuperficieMin;
	private static JTextField textFieldSuperficieMax;
	JComboBox<String> comboBoxRegions = new JComboBox<String>();
	static JLabel lblDepartement = new JLabel("  Departement");
	static JLabel lblHabitants = new JLabel("< Nombre d'habitants <");
	static JLabel lblCodePostal = new JLabel("Code Postal");
	static JLabel lblSuperficie = new JLabel("< superficie (km\u00B2) <");
	private JTextField textFieldDepartement;
        static JLabel lblReponses = new JLabel("Villes correspondantes :");
        String[] columnNames = {"Nom de la ville"};
        JTextArea areaReponse = new JTextArea(40, 10);
        
	
	public FramePlantesPedia() {
		this.setSize(630, 500);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{133, 149, 111, 0};
		gbl_panel.rowHeights = new int[]{25, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JTextArea txtrTapezVotreRecherche = new JTextArea();
		//txtrTapezVotreRecherche.setForeground(Color.LIGHT_GRAY);
		txtrTapezVotreRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtrTapezVotreRecherche.setText("");
			}
		});
		txtrTapezVotreRecherche.setText("Tapez votre recherche...");
		GridBagConstraints gbc_txtrTapezVotreRecherche = new GridBagConstraints();
		gbc_txtrTapezVotreRecherche.insets = new Insets(0, 0, 5, 5);
		gbc_txtrTapezVotreRecherche.fill = GridBagConstraints.BOTH;
		gbc_txtrTapezVotreRecherche.gridx = 1;
		gbc_txtrTapezVotreRecherche.gridy = 0;
		panel.add(txtrTapezVotreRecherche, gbc_txtrTapezVotreRecherche);
		
		JButton btnNewButton_1 = new JButton("Rechercher");
                btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			String nom = null;
                        String region = null;
                        String dep = null;
                        String cp = null;
                        String hab_min = null;
                        String hab_max = null;
                        String sup_min = null;
                        String sup_max = null;
                        System.out.println("J'AFFFFIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIICHE"+txtrTapezVotreRecherche.getText().length());
                        if (!txtrTapezVotreRecherche.getText().equals("Tapez votre recherche...")){
                            nom = (String) txtrTapezVotreRecherche.getText();
                        }
                        if (comboBoxRegions.isEnabled()){
                            region = (String) comboBoxRegions.getSelectedItem();
                        }
                        if (textFieldDepartement.isEnabled()){
                            dep = (String) textFieldDepartement.getText();
                        }
                        if (textFieldCodePostal.isEnabled()){
                            cp = (String) textFieldCodePostal.getText();
                        }
                        if (textFieldHabitantsMin.isEnabled()){
                            hab_min = (String) textFieldHabitantsMin.getText();
                        }
                        if (textFieldHabitantsMax.isEnabled()){
                            hab_max = (String) textFieldHabitantsMax.getText();
                        }
                        if (textFieldSuperficieMin.isEnabled()){
                            sup_min = (String) textFieldSuperficieMin.getText();
                        }
                        if (textFieldSuperficieMax.isEnabled()){
                            sup_max = (String) textFieldSuperficieMax.getText();
                        }
			String requete = model.OpenDBpedia.getRequete(nom, region, dep, cp, hab_min, hab_max, sup_min,sup_max);
                        ArrayList<String> tabVille = new ArrayList<String>();
                        try {
                                tabVille = model.OpenDBpedia.getVilles(requete);
                            } catch (RepositoryException ex) {
                                Logger.getLogger(FramePlantesPedia.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (MalformedQueryException ex) {
                                Logger.getLogger(FramePlantesPedia.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (QueryEvaluationException ex) {
                                Logger.getLogger(FramePlantesPedia.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        System.out.println(nom);
                        Iterator it = tabVille.iterator();
                        String tmp = "";
                        areaReponse.setText("");
                        while(it.hasNext()){
                            areaReponse.append(it.next().toString() + "\n");
                        }
                        
                    }
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JLabel label = new JLabel("Ajout de filtre");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("Supression de filtre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		
		/*
		 * ComboBox contenant les filtres a ajouter pour la recherche
		 */
		GridBagConstraints gbc_comboBoxTypeAjout = new GridBagConstraints();
		gbc_comboBoxTypeAjout.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTypeAjout.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTypeAjout.gridx = 0;
		gbc_comboBoxTypeAjout.gridy = 2;
		comboBoxTypeAjout.addItem("Region");
		comboBoxTypeAjout.addItem("Departement");
		comboBoxTypeAjout.addItem("Nombre d'habitants");
		comboBoxTypeAjout.addItem("Code Postal");
		comboBoxTypeAjout.addItem("Superficie");
		panel.add(comboBoxTypeAjout, gbc_comboBoxTypeAjout);
		
		/*
		 * ComboBox contenant les filtres a supprimer pour la recherche
		 */
		GridBagConstraints gbc_comboBoxTypeSupprime = new GridBagConstraints();
		gbc_comboBoxTypeSupprime.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxTypeSupprime.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTypeSupprime.gridx = 2;
		gbc_comboBoxTypeSupprime.gridy = 2;
		panel.add(comboBoxTypeSupprime, gbc_comboBoxTypeSupprime);
		
		/*
		 * Bouton pour l'ajout des filtres pour la recherche
		 */
		JButton btnAjout = new JButton("Ajouter");
		btnAjout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String temp = comboBoxTypeAjout.getSelectedItem().toString();
				verifierDoublon(tabType, temp);
				if(trouve == false) {
					tabType.add(temp);
					mettreTypeDansComboBox(temp);
					comboBoxTypeSupprime.setSelectedItem(null);
				} else {
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Impossible d'ajouter un type d�j� existant", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				switch (temp) {
				case "Departement":
					lblDepartement.setEnabled(true);
					textFieldDepartement.setEnabled(true);
					break;
				case "Region":
					comboBoxRegions.setEnabled(true);
					break;
				case "Nombre d'habitants":
					textFieldHabitantsMax.setEnabled(true);
					textFieldHabitantsMin.setEnabled(true);
					lblHabitants.setEnabled(true);
					break;
				case "Code Postal":
					lblCodePostal.setEnabled(true);
					textFieldCodePostal.setEnabled(true);
					break;
				case "Superficie":
					lblSuperficie.setEnabled(true);
					textFieldSuperficieMax.setEnabled(true);
					textFieldSuperficieMin.setEnabled(true);
					break;
				}
			}
		});
		GridBagConstraints gbc_btnAjout = new GridBagConstraints();
		gbc_btnAjout.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAjout.insets = new Insets(0, 0, 5, 5);
		gbc_btnAjout.gridx = 0;
		gbc_btnAjout.gridy = 3;
		panel.add(btnAjout, gbc_btnAjout);
		
		/*
		 * Bouton pour supprimer un filtre
		 */
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String temp = comboBoxTypeSupprime.getSelectedItem().toString();
				suppr = verifierExiste(temp);
				if (suppr == true) {
					supprimerTypeDuTableau(temp);
					comboBoxTypeSupprime.removeItem(temp);
					comboBoxTypeSupprime.setSelectedItem(null);
				} else {
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Impossible de supprimer un type non existant", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				switch (temp) {
				case "Departement":
					lblDepartement.setEnabled(false);
					textFieldDepartement.setEnabled(false);
					break;
				case "Region":
					comboBoxRegions.setEnabled(false);
					break;
				case "Nombre d'habitants":
					textFieldHabitantsMax.setEnabled(false);
					textFieldHabitantsMin.setEnabled(false);
					lblHabitants.setEnabled(false);
					break;
				case "Code Postal":
					lblCodePostal.setEnabled(false);
					textFieldCodePostal.setEnabled(false);
					break;
				case "Superficie":
					lblSuperficie.setEnabled(false);
					textFieldSuperficieMax.setEnabled(false);
					textFieldSuperficieMin.setEnabled(false);
					break;
				}
				
			}
		});
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.insets = new Insets(0, 0, 5, 0);
		gbc_btnSupprimer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSupprimer.gridx = 2;
		gbc_btnSupprimer.gridy = 3;
		panel.add(btnSupprimer, gbc_btnSupprimer);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 4;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{222, 298, 199, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		/*
		 * ComboBox contenant toutes les r�gions de france pour filtrer la recherche
		 */
		GridBagConstraints gbc_comboBoxRegions = new GridBagConstraints();
		gbc_comboBoxRegions.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRegions.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRegions.gridx = 1;
		gbc_comboBoxRegions.gridy = 0;
		comboBoxRegions.setEnabled(false);
		comboBoxRegions.addItem("Alsace");
		comboBoxRegions.addItem("Aquitaine");
		comboBoxRegions.addItem("Auvergne_(region)");
		comboBoxRegions.addItem("Lower_Normandy");
		comboBoxRegions.addItem("Burgundy");
		comboBoxRegions.addItem("Bretagne");
		comboBoxRegions.addItem("Centre_(French_region)");
		comboBoxRegions.addItem("Champagne-Ardenne");
		comboBoxRegions.addItem("Corsica");
		comboBoxRegions.addItem("Franche-Comt%C3%A9");
		comboBoxRegions.addItem("Upper_Normandy");
		comboBoxRegions.addItem("�le-De-France");
		comboBoxRegions.addItem("Languedoc-Roussillon");
		comboBoxRegions.addItem("Limousin");
		comboBoxRegions.addItem("Lorraine");
		comboBoxRegions.addItem("Midi-Pyr%C3%A9n%C3%A9es");
		comboBoxRegions.addItem("Nord-Pas-de-Calais");
		comboBoxRegions.addItem("Pays_de_la_Loire");
		comboBoxRegions.addItem("Picardy");
		comboBoxRegions.addItem("Poitou-Charentes");
		comboBoxRegions.addItem("Provence-Alpes-C%C3%B4te_d'Azur");
		comboBoxRegions.addItem("Rh%C3%B4ne-Alpes");
		panel_1.add(comboBoxRegions, gbc_comboBoxRegions);
		
		/*
		 * Label d�partement
		 */
		GridBagConstraints gbc_lblDepartement = new GridBagConstraints();
		gbc_lblDepartement.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartement.anchor = GridBagConstraints.EAST;
		gbc_lblDepartement.gridx = 0;
		gbc_lblDepartement.gridy = 1;
		lblDepartement.setEnabled(false);
		panel_1.add(lblDepartement, gbc_lblDepartement);
		
		/*
		 * Champ de texte pour selectionner un departement
		 */
		textFieldDepartement = new JTextField();
		textFieldDepartement.setEnabled(false);
		GridBagConstraints gbc_textFieldDepartement = new GridBagConstraints();
		gbc_textFieldDepartement.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartement.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartement.gridx = 1;
		gbc_textFieldDepartement.gridy = 1;
		panel_1.add(textFieldDepartement, gbc_textFieldDepartement);
		textFieldDepartement.setColumns(10);
		
		/*
		 * Champ de texte pour selectionner le nombre minimal d'habitant a prendre en compte pour la recherche
		 */
		textFieldHabitantsMin = new JTextField();
		textFieldHabitantsMin.setEnabled(false);
		GridBagConstraints gbc_textFieldHabitantsMin = new GridBagConstraints();
		gbc_textFieldHabitantsMin.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHabitantsMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHabitantsMin.gridx = 0;
		gbc_textFieldHabitantsMin.gridy = 2;
		panel_1.add(textFieldHabitantsMin, gbc_textFieldHabitantsMin);
		textFieldHabitantsMin.setColumns(10);
		
		
		GridBagConstraints gbc_lblHabitants = new GridBagConstraints();
		gbc_lblHabitants.insets = new Insets(0, 0, 5, 5);
		gbc_lblHabitants.gridx = 1;
		gbc_lblHabitants.gridy = 2;
		lblHabitants.setEnabled(false);
		panel_1.add(lblHabitants, gbc_lblHabitants);
		
		/*
		 * Champ de texte pour selectionner le nombre maximal d'habitant a prendre en compte pour la recherche
		 */
		textFieldHabitantsMax = new JTextField();
		textFieldHabitantsMax.setEnabled(false);
		GridBagConstraints gbc_textFieldHabitantsMax = new GridBagConstraints();
		gbc_textFieldHabitantsMax.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHabitantsMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHabitantsMax.gridx = 2;
		gbc_textFieldHabitantsMax.gridy = 2;
		panel_1.add(textFieldHabitantsMax, gbc_textFieldHabitantsMax);
		textFieldHabitantsMax.setColumns(10);
		
		
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.EAST;
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 0;
		gbc_lblCodePostal.gridy = 3;
		lblCodePostal.setEnabled(false);
		panel_1.add(lblCodePostal, gbc_lblCodePostal);
		
		/*
		 * Champ de texte pour selectionner le nombre minimal d'habitant a prendre en compte pour la recherche
		 */
		textFieldCodePostal = new JTextField();
		textFieldCodePostal.setEnabled(false);
		GridBagConstraints gbc_textFieldCodePostal = new GridBagConstraints();
		gbc_textFieldCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCodePostal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCodePostal.gridx = 1;
		gbc_textFieldCodePostal.gridy = 3;
		panel_1.add(textFieldCodePostal, gbc_textFieldCodePostal);
		textFieldCodePostal.setColumns(10);
		
		/*
		 * Champ de texte pour selectionner la superficie minimale pour la recherche
		 */
		textFieldSuperficieMin = new JTextField();
		textFieldSuperficieMin.setEnabled(false);
		GridBagConstraints gbc_textFieldSuperficieMin = new GridBagConstraints();
		gbc_textFieldSuperficieMin.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldSuperficieMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSuperficieMin.gridx = 0;
		gbc_textFieldSuperficieMin.gridy = 4;
		panel_1.add(textFieldSuperficieMin, gbc_textFieldSuperficieMin);
		textFieldSuperficieMin.setColumns(10);
		
		
		GridBagConstraints gbc_lblSuperficie = new GridBagConstraints();
		gbc_lblSuperficie.insets = new Insets(0, 0, 0, 5);
		gbc_lblSuperficie.gridx = 1;
		gbc_lblSuperficie.gridy = 4;
		lblSuperficie.setEnabled(false);
		panel_1.add(lblSuperficie, gbc_lblSuperficie);
		
		
		/*
		 * Champ de texte pour selectionner la superficie maximale pour la recherche
		 */
		textFieldSuperficieMax = new JTextField();
		textFieldSuperficieMax.setEnabled(false);
		GridBagConstraints gbc_textFieldSuperficieMax = new GridBagConstraints();
		gbc_textFieldSuperficieMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSuperficieMax.gridx = 2;
		gbc_textFieldSuperficieMax.gridy = 4;
		panel_1.add(textFieldSuperficieMax, gbc_textFieldSuperficieMax);
		textFieldSuperficieMax.setColumns(10);
                
        GridBagConstraints gbc_lblReponse = new GridBagConstraints();
		gbc_lblReponse.insets = new Insets(0, 0, 0, 5);
		gbc_lblReponse.gridx = 1;
		gbc_lblReponse.gridy = 5;
		lblReponses.setEnabled(false);
		panel_1.add(lblReponses, gbc_lblReponse);
                
        GridBagConstraints gbc_tabReponse = new GridBagConstraints();
		gbc_tabReponse.insets = new Insets(0, 0, 0, 5);
		gbc_tabReponse.gridx = 1;
		gbc_tabReponse.gridy = 6;
		areaReponse.setEnabled(false);
                panel_1.add(areaReponse, gbc_tabReponse);
                
	}

	/*
	 * Fonction qui indique si une valeur est pr�sente dans un tableau
	 */
	public void verifierDoublon(List<String> tab, String valeur) {
		if  (tab.isEmpty() == false) {
			for (int i=0; i<tab.size();i++) {
				if (tabType.get(i).equals(valeur)) {
					System.out.println("Doublon");
					trouve = true;
					break;
				}
			trouve = false;
			}
		}
	}
	
	/*
	 * Fonction qui affiche un tableau pass� en param�tre
	 */
	public void afficherTableau(List<String> tab) {
		System.out.println("Tableau :");
		for (int i=0; i<tab.size();i++) {
			System.out.println(tab.get(i).toString());
		}
	}
	
	/*
	 * Fonction qui supprime un type du tableau des filtres pour la recherche
	 */
	public void supprimerTypeDuTableau(String temp) {
		for (int i=0; i<tabType.size();i++) {
			if (tabType.get(i).equals(temp)) {
				tabType.remove(i);
				suppr = true;
				break;
			}
			suppr = false;
		}
	}
	
	/*
	 * Fonction qui mets les filtres dans la comboBox des types
	 */
	public void mettreTypeDansComboBox(String temp) {
		comboBoxTypeSupprime.addItem(temp);
	}
	
	/*
	 * Fonction qui verifie si un tableau contient un string pass� en param�tre
	 */
	public boolean verifierExiste(String temp) {
		if (tabType.contains(temp)) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String args[]) {
            new FramePlantesPedia().setVisible(true);    
        }
	}
