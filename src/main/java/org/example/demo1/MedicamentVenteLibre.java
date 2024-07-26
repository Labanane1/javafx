package org.example.demo1;

public class MedicamentVenteLibre extends Medicament {

    public MedicamentVenteLibre(String id, String nom, double prix) {
        super(id, nom, prix);
    }

    @Override
    public String getType() {
        return "Vente Libre";
    }

    @Override
    public String getDosage() {
        return null;  // Vente Libre does not have dosage
    }
}
