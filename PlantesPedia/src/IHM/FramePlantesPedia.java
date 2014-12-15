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


public class FramePlantesPedia extends JFrame {
	
	List<String> tabType = new ArrayList<String>();
	JTextArea textArea_1 = new JTextArea();
	boolean trouve = false;
	boolean suppr = false;
	JComboBox comboBox = new JComboBox<String>();
	JComboBox comboBox_1 = new JComboBox<String>();
	

	
	public FramePlantesPedia() {
		this.setSize(500, 300);
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
		txtrTapezVotreRecherche.setForeground(Color.LIGHT_GRAY);
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
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JLabel label = new JLabel("New label");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);
		
		
		textArea_1.setEditable(false);
		scrollPane.setViewportView(textArea_1);
		
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 2;
		comboBox.addItem("Nom");
		comboBox.addItem("Region");
		comboBox.addItem("Nombre d'habitants");
		comboBox.addItem("Code Postal");
		comboBox.addItem("Superficie");
		panel.add(comboBox, gbc_comboBox);
		
		
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 2;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String temp = comboBox.getSelectedItem().toString();
				//tabType.add(temp);
				afficherTableau(tabType);
				verifierDoublon(tabType, temp);
				if(trouve == false) {
					textArea_1.setText("");
					tabType.add(temp);
					//mettreTypeDansTextArea(temp);
					afficherTableauDansTextArea(tabType);
					mettreTypeDansComboBox(temp);
					comboBox_1.setSelectedItem(null);
				} else {
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Impossible d'ajouter un type déjà existant", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				//textArea_1.append(temp+"\r\n");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String temp = comboBox_1.getSelectedItem().toString();
				suppr = verifierExiste(temp);
				if (suppr == true) {
					supprimerTypeDuTableau(temp);
					comboBox_1.removeItem(temp);
					comboBox_1.setSelectedItem(null);
				} else {
					JOptionPane jop3 = new JOptionPane();
					jop3.showMessageDialog(null, "Impossible de supprimer un type non existant", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 3;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		

	}
	
	/*public void mettreTypeDansTextArea(String temp) {
		for (int i=0; i<tabType.size();i++) {
			System.out.println("indice : "+i);
			//String tempType = comboBox.getSelectedItem().toString();
			textArea_1.append(temp+"\r\n");
		}
	}*/
	
	public void verifierDoublon(List<String> tab, String valeur) {
		//trouve = false;
		if  (tab.isEmpty() == false) {
		for (int i=0; i<tab.size();i++) {
			//System.out.println("Valeur ajout : "+tabType.get(i));
			if (tabType.get(i).equals(valeur)) {
				//System.out.println(tabType.get(i).toString());
				System.out.println("Doublon");
				trouve = true;
				break;
			}
			trouve = false;
		}
		}
		
	}
	
	public void afficherTableauDansTextArea(List<String> tab) {
		for (int i=0; i<tab.size();i++) {
			textArea_1.append(tab.get(i)+"\r\n");
		}
	}
	
	public void afficherTableau(List<String> tab) {
		System.out.println("Tableau :");
		for (int i=0; i<tab.size();i++) {
			System.out.println(tab.get(i).toString());
		}
	}
	
	public void supprimerTypeDuTableau(String temp) {
		for (int i=0; i<tabType.size();i++) {
			if (tabType.get(i).equals(temp)) {
				tabType.remove(i);
				textArea_1.setText("");
				afficherTableauDansTextArea(tabType);
				suppr = true;
				break;
			}
			suppr = false;
			}
		}
	
	public void mettreTypeDansComboBox(String temp) {
		comboBox_1.addItem(temp);
	}
	
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
