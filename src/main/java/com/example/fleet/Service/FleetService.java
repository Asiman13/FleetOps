package com.example.fleet.Service;

import com.example.fleet.Repository.FleetRepository;
import com.example.fleet.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class FleetService {
    private FleetRepository fleetRepository;
    public FleetService(FleetRepository fleetRepository){
        this.fleetRepository=fleetRepository;
    }
    public List<Vehicle> getAllVehicle(){
        return fleetRepository.findAll();
    }
    public Vehicle getVehicleById(Long id){
       return fleetRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with " +id));
    }
    public Vehicle addVehicle(Vehicle v){
        if(fleetRepository.existsByPlateNumber(v.getPlateNumber())){
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Platenumber cannot be repeated");
        }
        return fleetRepository.save(v);
    }
    public void deleteById(Long id){
        if(!fleetRepository.existsById(id)){
            throw new RuntimeException("Vehicle not found with " + id);
        }
        fleetRepository.deleteById(id);
    }
    public Vehicle updateVehicle(Long id,Vehicle v){
        Vehicle v1=getVehicleById(id);
        if(!v1.getPlateNumber().equals(v.getPlateNumber()) && fleetRepository.existsByPlateNumber(v.getPlateNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT," Vehicle with plate number already exits");
        }
        v1.setBrand(v.getBrand());
        v1.setId(v.getId());
        v1.setMileage(v.getMileage());
        v1.setModel(v.getModel());
        v1.setPlateNumber(v.getPlateNumber());
        v1.setStatus(v.getStatus());
        v1.setYear(v.getYear());
        fleetRepository.save(v1);
        return v1;
    }
    public Page<Vehicle> getVehicle(int page,int size){
        Pageable p= PageRequest.of(page,size);
        return fleetRepository.findAll(p);
    }
    public List<Vehicle > searchByBrand(String brand){
        return fleetRepository.findByBrandContainingIgnoreCase(brand);
    }
    public List<Vehicle> sortVehicle(){
        Sort s=Sort.by("year").ascending();
        return fleetRepository.findAll(s);
    }

}
