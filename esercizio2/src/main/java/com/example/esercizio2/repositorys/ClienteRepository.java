package com.example.esercizio2.repositorys;

import com.example.esercizio2.models.Cliente;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
