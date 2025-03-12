package com.example.esercizio2.controllers.ThymeLeafControllers;

import com.example.esercizio2.models.Cliente;
import com.example.esercizio2.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clienti")
public class ClienteControllerThymeLeaf {

    private final ClienteService clienteService;

    public ClienteControllerThymeLeaf(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // GET - Mostra tutti i clienti
    @GetMapping()
    public String getAllClients(Model model) {
        model.addAttribute("clienti", clienteService.getAllClients());
        return "clienti"; // Carica la pagina dei clienti
    }

    // GET - Mostra la pagina per modificare un cliente
    @GetMapping("/modifica/{id}")
    public String editClient(@PathVariable("id") Long id, Model model) {
        clienteService.getClientById(id).ifPresent(cliente -> model.addAttribute("cliente", cliente));
        return "modifica_cliente"; // Pagina per modificare un cliente
    }

    // POST - Modifica un cliente
    @PostMapping("/modifica/{id}")
    public String updateClient(@PathVariable("id") Long id, @ModelAttribute Cliente cliente) {
        clienteService.updateClient(id, cliente);
        return "redirect:/clienti"; // Torna alla lista dei clienti
    }

    // GET - Elimina un cliente
    @GetMapping("/elimina/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clienteService.deleteClient(id);
        return "redirect:/clienti"; // Torna alla lista dei clienti
    }

    // POST - Crea un nuovo ordine
    @PostMapping("/nuovo")
    public String createOrder(@ModelAttribute Cliente cliente) {
        clienteService.createClient(cliente);
        return "redirect:/clienti"; // Torna alla lista degli ordini
    }
}
