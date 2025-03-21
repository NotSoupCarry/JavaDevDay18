package com.example.esercizio2.repositorys;

import com.example.esercizio2.models.Ordine;


import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    long countByClienteId(Long clienteId); // Metodo per contare gli ordini di un cliente
}