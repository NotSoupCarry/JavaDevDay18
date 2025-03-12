package com.example.esercizio1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.esercizio1.enums.VehicleStatuses;
import com.example.esercizio1.models.Vehicle;
import com.example.esercizio1.repositorys.VehicleRepository;

import jakarta.transaction.Transactional;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // GET per avere tutti i veicoli
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // POST per aggiungere un veicolo
    @Transactional
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // DELETE per eleminare un veicolo
    @Transactional
    public boolean deleteVehicle(String targa) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByTarga(targa);

        if (optionalVehicle.isPresent()) {
            vehicleRepository.deleteByTarga(targa);
            return true;
        } else {
            return false;
        }
    }

    // GET metodo per contare i veicoli per stato
    public List<Object[]> getVehicleCountByStatus() {
        return vehicleRepository.countVehiclesByStatus();
    }

    // GET per cercare un veicolo con targa
    public Optional<Vehicle> findByTarga(String targa) {
        return vehicleRepository.findByTarga(targa);
    }

    // PATCH per modificare lo stato di un veicolo
    @Transactional
    public Optional<Vehicle> patchVehicle(Long id, VehicleStatuses newState) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setStato(newState);
            vehicleRepository.save(vehicle);
            return Optional.of(vehicle);
        } else {
            return Optional.empty();
        }
    }

}
