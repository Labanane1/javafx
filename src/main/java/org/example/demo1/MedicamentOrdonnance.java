package org.example.demo1;

public class MedicamentOrdonnance extends Medicament {
    private String dosage;

    public MedicamentOrdonnance(String id, String nom, double prix, String dosage) {
        super(id, nom, prix);
        this.dosage = dosage;
    }

    @Override
    public String getType() {
        return "Ordonnance";
    }

    @Override
    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
