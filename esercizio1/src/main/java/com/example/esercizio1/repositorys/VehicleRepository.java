package com.example.esercizio1.repositorys;

import com.example.esercizio1.models.Vehicle;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByTarga(String targa);

    void deleteByTarga(String targa);

    @Query("SELECT stato, COUNT(id) FROM Vehicle v GROUP BY v.stato")
    List<Object[]> countVehiclesByStatus();

}
