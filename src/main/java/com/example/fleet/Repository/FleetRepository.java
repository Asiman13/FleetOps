package com.example.fleet.Repository;

import com.example.fleet.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FleetRepository extends JpaRepository<Vehicle,Long> {
    boolean existsByPlateNumber(String plateNumber);
    List<Vehicle> findByBrandContainingIgnoreCase(String brand);
}
