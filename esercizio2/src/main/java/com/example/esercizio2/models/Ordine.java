package com.example.esercizio2.models;

import java.time.LocalDate;

import com.example.esercizio2.enums.StatoOrdine;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "ordini")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataOrdine = LocalDate.now();

    @Column(nullable = false, precision = 10)
    private Double importo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatoOrdine stato;

    public Ordine() {}

    public Ordine(Long id, Cliente cliente, LocalDate dataOrdine, Double importo, StatoOrdine stato) {
        this.id = id;
        this.cliente = cliente;
        this.dataOrdine = dataOrdine;
        this.importo = importo;
        this.stato = stato;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDate getDataOrdine() { return dataOrdine; }
    public void setDataOrdine(LocalDate dataOrdine) { this.dataOrdine = dataOrdine; }

    public Double getImporto() { return importo; }
    public void setImporto(Double importo) { this.importo = importo; }

    public StatoOrdine getStato() { return stato; }
    public void setStato(StatoOrdine stato) { this.stato = stato; }
}

