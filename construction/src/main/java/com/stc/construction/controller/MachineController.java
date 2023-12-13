package com.stc.construction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.construction.model.Machine;
import com.stc.construction.model.Rent;
import com.stc.construction.service.MachineService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("machines")
public class MachineController {

    @Autowired
    MachineService machineService;
    
    // Get all machines
    @GetMapping("allMachines")
    public ResponseEntity<List<Machine>> getAllMachines() {
        return machineService.getAllMachines();
    }
    
    // Get machine by Id
    @GetMapping("get/{id}")
    public ResponseEntity<Machine> getMachineById(@RequestParam Integer id) {
        return machineService.getMachineById(id);
    }

    // Add machine
    @PostMapping("add")
    public ResponseEntity<String> addMachine(@RequestBody Machine machine) {
        return machineService.addMachine(machine);
    }

    // Update machine
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateMachine(@PathVariable Integer id, @RequestBody Machine machine) {
        return machineService.updateMachine(id, machine);
    }

    // Delete machine
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMachine(@PathVariable Integer id) {
        return machineService.deleteMachine(id);
    }

    // Search machine by name
    @GetMapping("search")
    public ResponseEntity<List<Machine>> searchMachine(@RequestParam String name) {
        return machineService.searchMachine(name);
    }

    // Rent machine
    @PutMapping("rent/{id}")
    public ResponseEntity<String> rentMachine(@PathVariable Integer id, @RequestBody Rent rent) {
        return machineService.rentMachine(id, rent);
    }

    // Return machine
    @PutMapping("return/{id}")
    public ResponseEntity<String> returnMachine(@PathVariable Integer id) {
        return machineService.returnMachine(id);
    }

    // Get all rented machines
    @GetMapping("allRents")
    public ResponseEntity<List<Rent>> getAllRentedMachines() {
        return machineService.getAllRentedMachines();
    }

    // Search rented machine by name
    @GetMapping("searchRented")
    public ResponseEntity<List<Rent>> searchRentedMachine(@RequestParam String name) {
        return machineService.searchRentedMachine(name);
    }

    // Get rent by Id
    @GetMapping("getRent/{id}")
    public ResponseEntity<Rent> getRentById(@PathVariable Integer id) {
        return machineService.getRentById(id);
    }

    // Delete rent
    @DeleteMapping("deleteRent/{id}")
    public ResponseEntity<String> deleteRent(@PathVariable Integer id) {
        return machineService.deleteRent(id);
    }
}
