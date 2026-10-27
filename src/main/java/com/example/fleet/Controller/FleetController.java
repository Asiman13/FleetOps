package com.example.fleet.Controller;

import com.example.fleet.Service.FleetService;
import com.example.fleet.entity.Vehicle;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Fleet")
public class FleetController {
    private FleetService fleetService;
    public FleetController(FleetService fleetService){
        this.fleetService=fleetService;
    }
    @GetMapping
    public List<Vehicle> getAllVehicle(){
        return fleetService.getAllVehicle();
    }
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id){
        return fleetService.getVehicleById(id);
    }
    @PostMapping("/add")
    public Vehicle addVehicle(@Valid @RequestBody Vehicle v){
        return fleetService.addVehicle(v);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        fleetService.deleteById(id);
    }
    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @Valid @RequestBody Vehicle v){
        return fleetService.updateVehicle(id,v);
    }
    @GetMapping("/page")
    public Page<Vehicle > getVehicle(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="5")int size
    ){
        return fleetService.getVehicle(page,size);
    }
    @GetMapping("/brand")
    public List<Vehicle> searchByBrand(@RequestParam String brand){
        return fleetService.searchByBrand(brand);
    }
    @GetMapping("/sort")
    public List<Vehicle> sortVehicle(){
        return fleetService.sortVehicle();
    }
}
