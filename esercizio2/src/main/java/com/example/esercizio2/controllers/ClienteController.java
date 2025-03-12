package com.example.esercizio2.controllers;

import com.example.esercizio2.models.Cliente;
import com.example.esercizio2.services.ClienteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET /api/clienti → Restituisce tutti i clienti
    @GetMapping
    public List<Cliente> getAllClients() {
        return clienteService.getAllClients();
    }

    // GET /api/clienti/{id} → Restituisce un cliente per ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClientById(id);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/clienti → Crea un cliente
    @PostMapping
    public ResponseEntity<Cliente> createClient(@Valid @RequestBody Cliente cliente) {
        Cliente createdCliente = clienteService.createClient(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    // PUT /api/clienti/{id} → Aggiorna un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @Valid @RequestBody Cliente updatedCliente) {
        Optional<Cliente> cliente = clienteService.updateClient(id, updatedCliente);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/clienti/{id} → Elimina un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        boolean isDeleted = clienteService.deleteClient(id);
        if (isDeleted) {
            return ResponseEntity.ok("Cliente eliminato con successo con i rispettivi ordini");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Impossibile eliminare il cliente");
        }
    }
}
