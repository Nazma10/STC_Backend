package com.stc.construction.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stc.construction.doa.MachineDoa;
import com.stc.construction.doa.RentDoa;
import com.stc.construction.model.Machine;
import com.stc.construction.model.Rent;

@Service
public class MachineService {

    @Autowired
    MachineDoa machineDoa;

    @Autowired
    RentDoa rentDoa;

    // Get all machines
    public ResponseEntity<List<Machine>> getAllMachines() {
        try {
            return new ResponseEntity<>(machineDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Get machine by Id
    public ResponseEntity<Machine> getMachineById(Integer id) {
        try {
            return new ResponseEntity<>(machineDoa.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Add machine
    public ResponseEntity<String> addMachine(Machine machine) {
        machineDoa.save(machine);
        return new ResponseEntity<>("Machine Added Successfully", HttpStatus.CREATED);
    }

    // Update machine
    public ResponseEntity<String> updateMachine(Integer id, Machine machine) {
        try {
            Machine existingMachine = machineDoa.findById(id).orElseThrow();

            existingMachine.setName(machine.getName());
            existingMachine.setQuantity(machine.getQuantity());

            machineDoa.save(existingMachine);
            return new ResponseEntity<>("Machine Updated Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Delete machine
    public ResponseEntity<String> deleteMachine(Integer id) {
        try {
            Machine existingMachine = machineDoa.findById(id).orElseThrow();  

            machineDoa.delete(existingMachine);
            return new ResponseEntity<>("Machine Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Search machine by name
    public ResponseEntity<List<Machine>> searchMachine(String name) {
        try {
            return new ResponseEntity<>(machineDoa.findByNameContainingIgnoreCase(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.BAD_REQUEST);
    }

    // Rent machine
    public ResponseEntity<String> rentMachine(Integer id, Rent rent) {
        try {
            Machine existingMachine = machineDoa.findById(id).orElseThrow();

            // check if quantity is available

            // if not available, return error message
            if (existingMachine.getQuantity() < rent.getQuantity()) {
                return new ResponseEntity<>("Not enough quantity available", HttpStatus.BAD_REQUEST);
            }

            // if available, 
            // 1. update quantity in tool table
            Integer newQuantity = existingMachine.getQuantity() - rent.getQuantity();
            existingMachine.setQuantity(newQuantity);

            machineDoa.save(existingMachine);

            // 2. add entry to rent table
            Rent newRent = new Rent();
            newRent.setToolId(id);
            newRent.setCustomerName(rent.getCustomerName());
            newRent.setCustomerPhone(rent.getCustomerPhone());
            newRent.setDateRented(new Date());
            newRent.setQuantity(rent.getQuantity());
            newRent.setStatus("Rented");

            rentDoa.save(newRent);

            // return success message  
            return new ResponseEntity<>("Machine Rented Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Rent Machine failed!" ,HttpStatus.BAD_REQUEST);
    }
   
    // Return machine
    public ResponseEntity<String> returnMachine(Integer id) {
        try {
            // 1. get the machine id from rent table
            Rent rentedMachine = rentDoa.findById(id).orElseThrow();
            Integer machineId = rentedMachine.getToolId();

             // 2. get the machine from machine table
            Machine existingMachine = machineDoa.findById(machineId).orElseThrow();

            // 3. update quantity in machine table
            Integer quantityOfMachine = existingMachine.getQuantity();
            Integer quantityOfRent = rentedMachine.getQuantity();

            Integer newQuantity = quantityOfMachine + quantityOfRent;
            existingMachine.setQuantity(newQuantity);

            machineDoa.save(existingMachine);

            // 4. update entry in rent table
            rentedMachine.setStatus("Returned");
            rentDoa.save(rentedMachine);

            // return success message
            return new ResponseEntity<>("Machine Returned Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Return Machine failed!" ,HttpStatus.BAD_REQUEST);
    }
 
    // Get all rented machines
    public ResponseEntity<List<Rent>> getAllRentedMachines() {
        try {
            return new ResponseEntity<>(rentDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.BAD_REQUEST);
    }

    // Search rented machine by name
    public ResponseEntity<List<Rent>> searchRentedMachine(String name) {
        try {
            return new ResponseEntity<>(rentDoa.findByToolNameContainingIgnoreCase(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.BAD_REQUEST);
    }

    // Get rent by Id
    public ResponseEntity<Rent> getRentById(Integer id) {
        try {
            return new ResponseEntity<>(rentDoa.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Delete rent
    public ResponseEntity<String> deleteRent(Integer id) {
        try {
            Rent existingRent = rentDoa.findById(id).orElseThrow();  

            rentDoa.delete(existingRent);
            return new ResponseEntity<>("Rent Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
