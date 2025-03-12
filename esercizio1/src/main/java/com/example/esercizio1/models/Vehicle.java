package com.example.esercizio1.models;

import java.util.Date;

import com.example.esercizio1.enums.VehicleStatuses;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modello;

    //@Pattern(regexp = "^[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}$", message = "La targa deve avere il formato XX999XX.")
    @Column(nullable = false, length = 10, unique = true)
    private String targa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, length = 50)
    private Date dataIngresso;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private VehicleStatuses stato;

    public Vehicle() {
    }

    public Vehicle(Long id, String marca, String modello, String targa, Date dataIngresso, VehicleStatuses stato) {
        this.id = id;
        this.marca = marca;
        this.modello = modello;
        this.targa = targa;
        this.dataIngresso = dataIngresso;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public String getStato() {
        return stato.getDescrizione();
    }

    public void setStato(VehicleStatuses stato) {
        this.stato = stato;
    }

}
