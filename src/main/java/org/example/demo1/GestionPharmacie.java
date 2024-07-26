package org.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionPharmacie {
    private Map<String, Medicament> medicaments;
    private Connection connection;

    public GestionPharmacie() {
        medicaments = new HashMap<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacie", "root", "");
            loadAllMedicaments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllMedicaments() {
        try {
            String query = "SELECT * FROM medicaments";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                String type = rs.getString("type");
                Medicament medicament;
                if (type.equals("Ordonnance")) {
                    String dosage = rs.getString("dosage");
                    medicament = new MedicamentOrdonnance(id, nom, prix, dosage);
                } else {
                    medicament = new MedicamentVenteLibre(id, nom, prix);
                }
                medicaments.put(id, medicament);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterMedicament(Medicament medicament) {
        medicaments.put(medicament.getId(), medicament);
        try {
            String query = "INSERT INTO medicaments (id, nom, prix, type, dosage) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, medicament.getId());
            stmt.setString(2, medicament.getNom());
            stmt.setDouble(3, medicament.getPrix());
            stmt.setString(4, medicament.getType());
            if (medicament instanceof MedicamentOrdonnance) {
                stmt.setString(5, ((MedicamentOrdonnance) medicament).getDosage());
            } else {
                stmt.setString(5, null);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerMedicament(String id) {
        medicaments.remove(id);
        try {
            String query = "DELETE FROM medicaments WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Medicament rechercherMedicamentParId(String id) {
        Medicament medicament = medicaments.get(id);
        if (medicament == null) {
            try {
                String query = "SELECT * FROM medicaments WHERE id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    double prix = rs.getDouble("prix");
                    String type = rs.getString("type");
                    if (type.equals("Ordonnance")) {
                        String dosage = rs.getString("dosage");
                        medicament = new MedicamentOrdonnance(id, nom, prix, dosage);
                    } else {
                        medicament = new MedicamentVenteLibre(id, nom, prix);
                    }
                    medicaments.put(id, medicament);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return medicament;
    }

    public List<Medicament> listerMedicamentsParLettre(char lettre) {
        List<Medicament> result = new ArrayList<>();
        lettre = Character.toLowerCase(lettre);
        for (Medicament medicament : medicaments.values()) {
            if (Character.toLowerCase(medicament.getNom().charAt(0)) == lettre) {
                result.add(medicament);
            }
        }
        return result;
    }

    public List<Medicament> listerTousLesMedicaments() {
        return new ArrayList<>(medicaments.values());
    }
}
