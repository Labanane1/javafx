package org.example.demo1;

class MedicamentVenteLibre extends Medicament {
    public MedicamentVenteLibre(String id, String nom, double prix) {
        super(id, nom, prix);
    }

    @Override
    public String getType() {
        return "Vente Libre";
    }
}

