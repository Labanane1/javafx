package org.example.demo1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private GestionPharmacie gestionPharmacie = new GestionPharmacie();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion de Pharmacie");

        // Création des composants de l'interface
        TextField idField = new TextField();
        idField.setPromptText("ID");
        idField.getStyleClass().add("text-field");

        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        nomField.getStyleClass().add("text-field");

        TextField prixField = new TextField();
        prixField.setPromptText("Prix");
        prixField.getStyleClass().add("text-field");

        TextField typeField = new TextField();
        typeField.setPromptText("Type (Vente Libre/Ordonnance)");
        typeField.getStyleClass().add("text-field");

        TextField dosageField = new TextField();
        dosageField.setPromptText("Dosage (si applicable)");
        dosageField.getStyleClass().add("text-field");

        Button ajouterButton = new Button("Ajouter Médicament");
        ajouterButton.getStyleClass().add("button");

        Button supprimerButton = new Button("Supprimer Médicament");
        supprimerButton.getStyleClass().add("button");

        Button rechercherButton = new Button("Rechercher Médicament");
        rechercherButton.getStyleClass().add("button");

        Button listerButton = new Button("Lister Médicaments par Lettre");
        listerButton.getStyleClass().add("button");

        Button listerTousButton = new Button("Lister Tous les Médicaments");
        listerTousButton.getStyleClass().add("button");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.getStyleClass().add("text-area");

        TableView<Medicament> tableView = new TableView<>();
        tableView.getStyleClass().add("table-view");

        TableColumn<Medicament, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Medicament, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Medicament, Double> prixColumn = new TableColumn<>("Prix");
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

        TableColumn<Medicament, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Medicament, String> dosageColumn = new TableColumn<>("Dosage");
        dosageColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));

        tableView.getColumns().addAll(idColumn, nomColumn, prixColumn, typeColumn, dosageColumn);

        ObservableList<Medicament> medicamentList = FXCollections.observableArrayList();
        tableView.setItems(medicamentList);

        // Ajout des actions aux boutons
        ajouterButton.setOnAction(e -> {
            String id = idField.getText();
            String nom = nomField.getText();
            double prix = Double.parseDouble(prixField.getText());
            String type = typeField.getText();
            if (type.equalsIgnoreCase("Ordonnance")) {
                String dosage = dosageField.getText();
                gestionPharmacie.ajouterMedicament(new MedicamentOrdonnance(id, nom, prix, dosage));
            } else {
                gestionPharmacie.ajouterMedicament(new MedicamentVenteLibre(id, nom, prix));
            }
            resultArea.setText("Médicament ajouté avec succès!");
        });

        supprimerButton.setOnAction(e -> {
            String id = idField.getText();
            gestionPharmacie.supprimerMedicament(id);
            medicamentList.removeIf(m -> m.getId().equals(id));
            resultArea.setText("Médicament supprimé avec succès!");
        });

        rechercherButton.setOnAction(e -> {
            String id = idField.getText();
            Medicament medicament = gestionPharmacie.rechercherMedicamentParId(id);
            if (medicament != null) {
                resultArea.setText("Nom: " + medicament.getNom() + ", Prix: " + medicament.getPrix() + ", Type: " + medicament.getType());
            } else {
                resultArea.setText("Médicament non trouvé.");
            }
        });

        listerButton.setOnAction(e -> {
            char lettre = nomField.getText().charAt(0);
            medicamentList.clear();
            medicamentList.addAll(gestionPharmacie.listerMedicamentsParLettre(lettre));
        });

        listerTousButton.setOnAction(e -> {
            medicamentList.clear();
            medicamentList.addAll(gestionPharmacie.listerTousLesMedicaments());
        });

        // Mise en page des composants
        VBox vbox = new VBox(10, idField, nomField, prixField, typeField, dosageField, ajouterButton, supprimerButton, rechercherButton, listerButton, listerTousButton, resultArea, tableView);
        vbox.setStyle("-fx-alignment: center; -fx-padding: 20px; -fx-spacing: 10px;");
        Scene scene = new Scene(vbox, 600, 600);

        // Intégration du fichier CSS
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
