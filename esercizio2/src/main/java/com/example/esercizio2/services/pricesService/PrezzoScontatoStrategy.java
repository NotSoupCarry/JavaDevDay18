package com.example.esercizio2.services.pricesService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.esercizio2.interfaces.PrezzoStrategy;

@Service
@Qualifier("prezzoScontatoStrategy")
public class PrezzoScontatoStrategy implements PrezzoStrategy{
    @Override
    public double calcolaPrezzo(double prezzoBase) {
        return prezzoBase * 0.9; // Sconto 10%
    }
}
