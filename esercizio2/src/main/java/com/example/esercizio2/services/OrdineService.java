package com.example.esercizio2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.esercizio2.enums.StatoOrdine;
import com.example.esercizio2.models.Ordine;
import com.example.esercizio2.repositorys.OrdineRepository;

import jakarta.transaction.Transactional;

@Service
@Qualifier("ordineServicePriority")
public class OrdineService {

    private final OrdineRepository ordineRepository;

    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    // Restituisce tutti gli ordini GET
    public List<Ordine> getAllOrders() {
        return ordineRepository.findAll();
    }

    // Restituisce un ordine per ID GET
    public Optional<Ordine> getOrderById(Long id) {
        return ordineRepository.findById(id);
    }

    // Crea un nuovo ordine POST
    @Transactional
    public Ordine createOrder(Ordine ordine) {
        return ordineRepository.save(ordine);
    }

    // Modifica lo stato di un ordine PUT
    @Transactional
    public Optional<Ordine> updateOrderStatus(Long id, StatoOrdine newStatus) {
        Optional<Ordine> ordineOptional = ordineRepository.findById(id);
        if (ordineOptional.isPresent()) {
            Ordine ordine = ordineOptional.get();
            ordine.setStato(newStatus);
            return Optional.of(ordineRepository.save(ordine));
        }
        return Optional.empty();
    }

    // Elimina un ordine
    @Transactional
    public boolean deleteOrder(Long id) {
        if (ordineRepository.existsById(id)) {
            ordineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
