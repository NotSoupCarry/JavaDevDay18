package com.example.esercizio2.controllers.ThymeLeafControllers;

import com.example.esercizio2.models.Ordine;
import com.example.esercizio2.services.OrdineService;
import com.example.esercizio2.enums.StatoOrdine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ordini")
public class OrdineControllerThymeLeaf {

    private final OrdineService ordineService;

    public OrdineControllerThymeLeaf(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    // GET - Mostra tutti gli ordini con un filtro per stato
    @GetMapping()
    public String getAllOrders(@RequestParam(value = "stato", required = false) StatoOrdine stato, Model model) {
        if (stato != null) {
            model.addAttribute("ordini", ordineService.getAllOrders().stream()
                    .filter(ordine -> ordine.getStato().equals(stato))
                    .toList());
        } else {
            model.addAttribute("ordini", ordineService.getAllOrders());
        }
        model.addAttribute("stati", StatoOrdine.values());
        return "ordini"; // Carica la pagina degli ordini
    }

    // POST - Crea un nuovo ordine
    @PostMapping("/nuovo")
    public String createOrder(@ModelAttribute Ordine ordine) {
        ordineService.createOrder(ordine);
        return "redirect:/ordini"; // Torna alla lista degli ordini
    }

    // GET - Filtro ordini SPEDITI
    @GetMapping("/spedit")
    public String getOrdersSpediti(Model model) {
        model.addAttribute("ordini", ordineService.getAllOrders().stream()
                .filter(ordine -> ordine.getStato() == StatoOrdine.SPEDITO)
                .toList());
        return "ordini"; // Mostra solo ordini spediti
    }

    // GET - Calcola il prezzo dell'ordine
    // GET - Calcola il prezzo dell'ordine
    @GetMapping("/calcolaPrezzo")
    public String calcolaPrezzo(@RequestParam("ordineId") Long ordineId, Model model) {
        try {
            double prezzo = ordineService.prezzoStrategy(ordineId); // Calcola il prezzo in base alla strategia
            model.addAttribute("prezzo", prezzo); // Aggiungi il prezzo al modello
        } catch (RuntimeException e) {
            model.addAttribute("error", "Errore: " + e.getMessage()); // Aggiungi il messaggio di errore al modello
        }

        // Aggiungi gli ordini e gli stati per mostrare tutto sulla stessa pagina
        model.addAttribute("ordini", ordineService.getAllOrders());
        model.addAttribute("stati", StatoOrdine.values());

        return "ordini"; // Ritorna alla pagina degli ordini
    }
}
