package com.stc.construction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stc.construction.model.Rent;
import com.stc.construction.model.Tool;
import com.stc.construction.service.ToolService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("tools")
public class ToolsController {
    
    @Autowired
    ToolService toolService;

    // Get all tools
    @GetMapping("allTools")
    public ResponseEntity<List<Tool>> getAllTools() {
        return toolService.getAllTools();
    }

    // Get tool by Id
    @GetMapping("get/{id}")
    public ResponseEntity<Tool> getToolById(@PathVariable Integer id) {
        return toolService.getToolById(id);
    }

    // Add tool
    @PostMapping("add")
    public ResponseEntity<String> addTool(@RequestBody Tool tool) {
        return toolService.addTool(tool);
    }

    // Update tool
    @PutMapping("update/{id}")
    public ResponseEntity<String> editTool(@PathVariable Integer id, @RequestBody Tool tool) {
        return toolService.editTool(id, tool);
    }

    // Delete tool
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTool(@PathVariable Integer id) {
        return toolService.deleteTool(id);
    }

    // Search tool by name
    @GetMapping("search")
    public ResponseEntity<List<Tool>> searchTool(@RequestParam String name) {
        return toolService.searchTool(name);
    }

    // Rent tool
    @PutMapping("rent/{id}")
    public ResponseEntity<String> rentTool(@PathVariable Integer id, @RequestBody Rent rent) {
        return toolService.rentTool(id, rent);
    }

    // Return tool
    @PutMapping("return/{id}")
    public ResponseEntity<String> returnTool(@PathVariable Integer id) {
        return toolService.returnTool(id);
    }

    // Get all rents
    @GetMapping("allRents")
    public ResponseEntity<List<Rent>> getAllRents() {
        return toolService.getAllRents();
    }
}
