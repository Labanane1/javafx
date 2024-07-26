package org.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class SupprimerController extends BaseController {
    private GestionPharmacie gestionPharmacie;

    @FXML
    private TextField idField;
    @FXML
    private TextArea resultArea;

    @FXML
    private void supprimerMedicament() {
        String id = idField.getText();
        gestionPharmacie.supprimerMedicament(id);
        resultArea.setText("Médicament supprimé avec succès!");
    }

    @Override
    public void setGestionPharmacie(GestionPharmacie gestionPharmacie) {
        this.gestionPharmacie = gestionPharmacie;
    }

    public void goBack(ActionEvent actionEvent) {

    }
}
