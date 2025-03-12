package com.example.esercizio1.models;

import com.example.esercizio1.enums.VehicleStatuses;

public class ResultQueryCountVehicles {
    private VehicleStatuses stato;
    private Long count;

    public ResultQueryCountVehicles(VehicleStatuses stato, Long count){
        this.stato = stato;
        this.count = count;
    }

    // Getter per stato
    public VehicleStatuses getStato() {
        return stato;
    }

    // Setter per stato
    public void setStato(VehicleStatuses stato) {
        this.stato = stato;
    }

    // Getter per count
    public Long getCount() {
        return count;
    }

    // Setter per count
    public void setCount(Long count) {
        this.count = count;
    }
}
