package org.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class AjouterController extends BaseController {
    private GestionPharmacie gestionPharmacie;

    @FXML
    private TextField idField, nomField, prixField, typeField, dosageField;
    @FXML
    private TextArea resultArea;
    @FXML
    private TableView<Medicament> tableView;
    @FXML
    private TableColumn<Medicament, String> idColumn, nomColumn, typeColumn, dosageColumn;
    @FXML
    private TableColumn<Medicament, Double> prixColumn;

    private ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();

    public AjouterController(TableColumn<Medicament, Double> prixColumn) {
        this.prixColumn = prixColumn;
    }

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

    @Override
    public void setGestionPharmacie(GestionPharmacie gestionPharmacie) {
        this.gestionPharmacie = gestionPharmacie;
    }

    public void goBack(ActionEvent actionEvent) {

    }
}
