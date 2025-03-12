package com.example.esercizio2.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "clienti")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(length = 20)
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ordine> ordini;

    public Cliente() {}

    public Cliente(Long id, String nome, String email, String telefono) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}