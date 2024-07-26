package org.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class RechercherController extends BaseController {
    private GestionPharmacie gestionPharmacie;

    @FXML
    private TextField idField;
    @FXML
    private TextArea resultArea;

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

    @Override
    public void setGestionPharmacie(GestionPharmacie gestionPharmacie) {
        this.gestionPharmacie = gestionPharmacie;
    }

    public void goBack(ActionEvent actionEvent) {

    }
}
