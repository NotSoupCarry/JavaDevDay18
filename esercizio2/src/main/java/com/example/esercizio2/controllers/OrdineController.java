package com.example.esercizio2.controllers;

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
    @GetMapping
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
}
