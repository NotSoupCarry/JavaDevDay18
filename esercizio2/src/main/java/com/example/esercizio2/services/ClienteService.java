package com.example.esercizio2.services;

import com.example.esercizio2.models.Cliente;
import com.example.esercizio2.repositorys.ClienteRepository;
import com.example.esercizio2.repositorys.OrdineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository, OrdineRepository ordineRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Restituisce tutti i clienti
    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }

    // Restituisce un cliente per ID
    public Optional<Cliente> getClientById(Long id) {
        return clienteRepository.findById(id);
    }

    // Crea un nuovo cliente
    public Cliente createClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Aggiorna un cliente
    public Optional<Cliente> updateClient(Long id, Cliente updatedCliente) {
        Optional<Cliente> existingCliente = clienteRepository.findById(id);
        if (existingCliente.isPresent()) {
            Cliente cliente = existingCliente.get();
            cliente.setNome(updatedCliente.getNome());
            cliente.setEmail(updatedCliente.getEmail());
            cliente.setTelefono(updatedCliente.getTelefono());
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }

    // Elimina un cliente se non ha ordini
    @Transactional
    public boolean deleteClient(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            clienteRepository.delete(cliente);
            return true;
        }
        return false; // Se il cliente ha ordini, non è stato eliminato
    }
}
