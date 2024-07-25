package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prixField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField dosageField;
    @FXML
    private TextArea resultArea;
    @FXML
    private TableView<Medicament> tableView;
    @FXML
    private TableColumn<Medicament, String> idColumn;
    @FXML
    private TableColumn<Medicament, String> nomColumn;
    @FXML
    private TableColumn<Medicament, Double> prixColumn;
    @FXML
    private TableColumn<Medicament, String> typeColumn;
    @FXML
    private TableColumn<Medicament, String> dosageColumn;

    private GestionPharmacie gestionPharmacie = new GestionPharmacie();
    private ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));

        tableView.setItems(medicamentList);
    }

    @FXML
    private void ajouterMedicament() {
        String id = idField.getText();
        String nom = nomField.getText();
        double prix = Double.parseDouble(prixField.getText());
        String type = typeField.getText();
        Medicament medicament;
        if (type.equalsIgnoreCase("Ordonnance")) {
            String dosage = dosageField.getText();
            medicament = new MedicamentOrdonnance(id, nom, prix, dosage);
        } else {
            medicament = new MedicamentVenteLibre(id, nom, prix);
        }
        gestionPharmacie.ajouterMedicament(medicament);
        medicamentList.add(medicament);
        resultArea.setText("Médicament ajouté avec succès!");
    }

    @FXML
    private void supprimerMedicament() {
        String id = idField.getText();
        gestionPharmacie.supprimerMedicament(id);
        medicamentList.removeIf(m -> m.getId().equals(id));
        resultArea.setText("Médicament supprimé avec succès!");
    }

    @FXML
    private void rechercherMedicament() {
        String id = idField.getText();
        Medicament medicament = gestionPharmacie.rechercherMedicamentParId(id);
        if (medicament != null) {
            resultArea.setText("Nom: " + medicament.getNom() + ", Prix: " + medicament.getPrix() + ", Type: " + medicament.getType());
        } else {
            resultArea.setText("Médicament non trouvé.");
        }
    }

    @FXML
    private void listerMedicamentsParLettre() {
        char lettre = nomField.getText().charAt(0);
        medicamentList.clear();
        medicamentList.addAll(gestionPharmacie.listerMedicamentsParLettre(lettre));
    }

    @FXML
    private void listerTousLesMedicaments() {
        medicamentList.clear();
        medicamentList.addAll(gestionPharmacie.listerTousLesMedicaments());
    }
}
