package org.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainController {
    private GestionPharmacie gestionPharmacie;

    public MainController() {
        this.gestionPharmacie = new GestionPharmacie();
    }

    // Méthode pour gérer l'ajout de médicament
    @FXML
    private void handleAddMedicament() {
        loadPage("/org/example/demo1/ajouter.fxml");
    }

    // Méthode pour gérer la suppression de médicament
    @FXML
    private void handleDeleteMedicament() {
        loadPage("/org/example/demo1/supprimer.fxml");
    }

    // Méthode pour gérer la recherche de médicament
    @FXML
    private void handleSearchMedicament() {
        loadPage("/org/example/demo1/rechercher.fxml");
    }

    // Méthode pour lister tous les médicaments
    @FXML
    private void handleListAllMedicaments() {
        loadPage("/org/example/demo1/lister.fxml");
    }

    // Méthode pour lister les médicaments par lettre
    @FXML
    private void handleListMedicamentsByLetter() {
        loadPage("/org/example/demo1/lister_par_lettre.fxml");
    }

    // Méthode privée pour charger une page donnée
    private void loadPage(String fxmlFile) {
        try {
            // Charger le fichier FXML correspondant
            URL fxmlLocation = getClass().getResource(fxmlFile);
            if (fxmlLocation == null) {
                throw new IOException(STR."FXML file not found: \{fxmlFile}");
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            // Passer l'instance de GestionPharmacie aux contrôleurs des nouvelles pages
            BaseController controller = loader.getController();
            if (controller != null) {
                controller.setGestionPharmacie(gestionPharmacie);
            }

            // Créer une nouvelle scène et stage pour la nouvelle page
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // Ajouter la feuille de style CSS
            URL cssLocation = getClass().getResource("/org/example/demo1/styles.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            } else {
                System.err.println("CSS file not found: /org/example/demo1/styles.css");
            }

            // Configurer et afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Setter pour injecter l'instance de GestionPharmacie
    public void setGestionPharmacie(GestionPharmacie gestionPharmacie) {
        this.gestionPharmacie = gestionPharmacie;
    }

    // Méthodes d'action
    @FXML
    private void supprimerMedicament(ActionEvent actionEvent) {
        handleDeleteMedicament();
    }

    @FXML
    private void ajouterMedicament(ActionEvent actionEvent) {
        handleAddMedicament();
    }

    @FXML
    private void rechercherMedicament(ActionEvent actionEvent) {
        handleSearchMedicament();
    }

    @FXML
    private void listerMedicamentsParLettre(ActionEvent actionEvent) {
        handleListMedicamentsByLetter();
    }

    @FXML
    private void listerTousLesMedicaments(ActionEvent actionEvent) {
        handleListAllMedicaments();
    }
}
