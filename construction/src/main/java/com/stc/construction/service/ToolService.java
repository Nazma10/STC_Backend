package com.stc.construction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stc.construction.doa.ToolDoa;
import com.stc.construction.model.Tool;

@Service
public class ToolService {
    
    @Autowired
    ToolDoa toolDoa;

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
        try {
            toolDoa.save(tool);
            return new ResponseEntity<>("Tool added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Tool not added!", HttpStatus.BAD_REQUEST);
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
}
