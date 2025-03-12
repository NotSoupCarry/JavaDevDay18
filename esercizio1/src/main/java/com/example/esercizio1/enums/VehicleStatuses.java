package com.example.esercizio1.enums;

public enum VehicleStatuses {   
    COMPLETATO("Compleatato"),
    IN_ATTESA("In attesa"),
    IN_LAVORAZIONE("In lavorazione");

    private final String descrizione;

    VehicleStatuses(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
