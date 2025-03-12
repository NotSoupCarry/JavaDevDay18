package com.example.esercizio2.controllers.ApiControllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.esercizio2.enums.StatoOrdine;
import com.example.esercizio2.models.Ordine;
import com.example.esercizio2.services.OrdineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {

    private final OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    // GET - Restituisce tutti gli ordini
    @GetMapping()
    public List<Ordine> getAllOrders() {
        return ordineService.getAllOrders();
    }

    // GET - Restituisce un ordine per ID
    @GetMapping("/{id}")
    public ResponseEntity<Ordine> getOrderById(@PathVariable Long id) {
        return ordineService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Crea un nuovo ordine
    @PostMapping
    public ResponseEntity<Ordine> createOrder(@Valid @RequestBody Ordine ordine) {
        Ordine nuovoOrdine = ordineService.createOrder(ordine);
        return ResponseEntity.ok(nuovoOrdine);
    }

    // PUT - Modifica lo stato di un ordine
    @PutMapping("/{id}/stato")
    public ResponseEntity<Ordine> updateOrderStatus(@PathVariable Long id, @RequestBody StatoOrdine nuovoStato) {
        return ordineService.updateOrderStatus(id, nuovoStato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Elimina un ordine
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (ordineService.deleteOrder(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/ordini/{id}/prezzo -> Calcola il prezzo di un ordine in base alla
    // strategia
    @GetMapping("/prezzo/{id}")
    public ResponseEntity<Double> calcolaPrezzo(@PathVariable Long id) {
        try {
            // Chiama il servizio per calcolare il prezzo
            double prezzo = ordineService.prezzoStrategy(id);
            return ResponseEntity.ok(prezzo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Se l'ordine non è trovato
        }
    }
}
