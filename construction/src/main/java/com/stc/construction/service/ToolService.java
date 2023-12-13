package com.stc.construction.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stc.construction.doa.RentDoa;
import com.stc.construction.doa.ToolDoa;
import com.stc.construction.model.Rent;
import com.stc.construction.model.Tool;

@Service
public class ToolService {
    
    @Autowired
    ToolDoa toolDoa;

    @Autowired
    RentDoa rentDoa;

    // Get all tools
    public ResponseEntity<List<Tool>> getAllTools() {
        try {
            return new ResponseEntity<>(toolDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Get tool by Id
    public ResponseEntity<Tool> getToolById(Integer id) {
        try {
            return new ResponseEntity<>(toolDoa.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Add tool
    public ResponseEntity<String> addTool(Tool tool) {
            toolDoa.save(tool);
            return new ResponseEntity<>("Tool added successfully", HttpStatus.CREATED); 
    }

    // Update tool
    public ResponseEntity<String> editTool(Integer id, Tool tool) {
        try {
            Tool existingTool = toolDoa.findById(id).orElseThrow();

            existingTool.setName(tool.getName());
            existingTool.setQuantity(tool.getQuantity());

            toolDoa.save(existingTool);
            return new ResponseEntity<>("Tool updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Tool update failed!", HttpStatus.BAD_REQUEST);
    }

    // Delete tool
    public ResponseEntity<String> deleteTool(Integer id) {
        try {
            Tool existingTool = toolDoa.findById(id).orElseThrow();

            toolDoa.delete(existingTool);
            return new ResponseEntity<>("Tool deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Tool delete failed!", HttpStatus.BAD_REQUEST);
    }

    // Search tool by name
    public ResponseEntity<List<Tool>> searchTool(String name) {
        try {
            return new ResponseEntity<>(toolDoa.findByNameContainingIgnoreCase(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Rent tool
    public ResponseEntity<String> rentTool(Integer id, Rent rent) {
        try {
            Tool existingTool = toolDoa.findById(id).orElseThrow();

            // check if quantity is available

            // if not available, return error message
            if (existingTool.getQuantity() < rent.getQuantity()) {
                return new ResponseEntity<>("Tool not available", HttpStatus.BAD_REQUEST);
            }

            // if available, 
            // 1. update quantity in tool table
            Integer quantity = existingTool.getQuantity() - rent.getQuantity();
            existingTool.setQuantity(quantity);

            toolDoa.save(existingTool);

            // 2. add entry to rent table
            Rent rentTool = new Rent();
            rentTool.setToolId(id);
            rentTool.setCustomerName(rent.getCustomerName());
            rentTool.setCustomerPhone(rent.getCustomerPhone());
            rentTool.setDateRented(new Date());
            rentTool.setQuantity(rent.getQuantity());
            rentTool.setStatus("Rented");

            rentDoa.save(rentTool);

            // return success message    
            return new ResponseEntity<>("Tool rented successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Tool rent failed!", HttpStatus.BAD_REQUEST);
    }

    // Return tool
    public ResponseEntity<String> returnTool(Integer id) {
        try {
            // 1. get the tool id from rent table
            Rent rentedTool = rentDoa.findById(id).orElseThrow();
            Integer toolId = rentedTool.getToolId();

            // 2. get the tool from tool table
            Tool existingTool = toolDoa.findById(toolId).orElseThrow();

            // 3. update quantity in tool table
            Integer quantityOfTool = existingTool.getQuantity();
            Integer quantityOfRent = rentedTool.getQuantity();

            Integer quantity = quantityOfTool + quantityOfRent;
            existingTool.setQuantity(quantity);

            toolDoa.save(existingTool);

            // 4. update entry in rent table
            rentedTool.setStatus("Returned");
            rentDoa.save(rentedTool);

            // return success message    
            return new ResponseEntity<>("Tool returned successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Tool return update failed!", HttpStatus.BAD_REQUEST);
    }

    // Get all rents
    public ResponseEntity<List<Rent>> getAllRents() {
        try {
            return new ResponseEntity<>(rentDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Search rent by tool name
    public ResponseEntity<List<Rent>> searchRent(String name) {
        try {
            return new ResponseEntity<>(rentDoa.findByToolNameContainingIgnoreCase(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>("Rent deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Rent delete failed!", HttpStatus.BAD_REQUEST);
    }
}
