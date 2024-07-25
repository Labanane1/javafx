package org.example.demo1;

class MedicamentOrdonnance extends Medicament {
    private String dosage;

    public MedicamentOrdonnance(String id, String nom, double prix, String dosage) {
        super(id, nom, prix);
        this.dosage = dosage;
    }

    public String getDosage() {
        return dosage;
    }

    @Override
    public String getType() {
        return "Ordonnance";
    }
}