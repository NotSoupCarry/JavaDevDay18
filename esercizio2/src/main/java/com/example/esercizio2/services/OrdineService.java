package com.example.esercizio2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.esercizio2.enums.StatoOrdine;
import com.example.esercizio2.interfaces.PrezzoStrategy;
import com.example.esercizio2.models.Ordine;
import com.example.esercizio2.repositorys.OrdineRepository;

import jakarta.transaction.Transactional;

@Service
@Qualifier("ordineServicePriority")
public class OrdineService {

    private final OrdineRepository ordineRepository;
    private final PrezzoStrategy prezzoStrategyStandard;
    private final PrezzoStrategy prezzoStrategyScontato;

    public OrdineService(OrdineRepository ordineRepository,
            @Qualifier("prezzoStandardStrategy") PrezzoStrategy prezzoStrategyStandard,
            @Qualifier("prezzoScontatoStrategy") PrezzoStrategy prezzoStrategyScontato) {
        this.ordineRepository = ordineRepository;
        this.prezzoStrategyStandard = prezzoStrategyStandard;
        this.prezzoStrategyScontato = prezzoStrategyScontato;
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

    // Metodo per calcolare il prezzo in base alla strategia
    public double prezzoStrategy(Long ordineId) {
        // Recupera l'ordine per id
        Optional<Ordine> ordineOptional = ordineRepository.findById(ordineId);

        // Se l'ordine esiste
        if (ordineOptional.isPresent()) {
            Ordine ordine = ordineOptional.get();
            double prezzoBase = ordine.getImporto(); // Ottieni l'importo dell'ordine

            // Recupera tutti gli ordini del cliente
            long numeroOrdini = ordineRepository.countByClienteId(ordine.getCliente().getId());

            // Se il cliente ha almeno 3 ordini, applica lo sconto
            if (numeroOrdini >= 3) {
                return prezzoStrategyScontato.calcolaPrezzo(prezzoBase); // Calcola con sconto
            } else {
                return prezzoStrategyStandard.calcolaPrezzo(prezzoBase); // Calcola con prezzo standard
            }
        }
        throw new RuntimeException("Ordine non trovato con ID: " + ordineId);
    }

}
