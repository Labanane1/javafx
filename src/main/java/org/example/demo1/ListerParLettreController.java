package org.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class ListerParLettreController extends BaseController {
    private GestionPharmacie gestionPharmacie;

    @FXML
    private TextField lettreField;
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
    private void listerParLettre() {
        char lettre = lettreField.getText().toLowerCase().charAt(0);
        medicamentList.clear();
        medicamentList.addAll(gestionPharmacie.listerMedicamentsParLettre(lettre));
    }

    @Override
    public void setGestionPharmacie(GestionPharmacie gestionPharmacie) {
        this.gestionPharmacie = gestionPharmacie;
    }

    public void goBack(ActionEvent actionEvent) {

    }
}
