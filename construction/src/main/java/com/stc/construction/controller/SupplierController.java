package com.stc.construction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stc.construction.model.Supplier;
import com.stc.construction.service.SupplierService;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    
    @Autowired
    SupplierService supplierService;

    // Get all suppliers
    @GetMapping("allSuppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Get supplier by type
    @GetMapping("type/{type}")
    public ResponseEntity<List<Supplier>> getSupplierByType(@PathVariable String type) {
        return supplierService.getSupplierByType(type);
    }

    // Get supplier by id
    @GetMapping("get/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    // Add supplier
    @PostMapping("add")
    public ResponseEntity<String> addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    // Update supplier
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateSupplier(@RequestBody Supplier supplier, @PathVariable Integer id) {
        return supplierService.updateSupplier(supplier, id);
    }

    // Delete supplier
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }

    // Search supplier
    @GetMapping("search")
    public ResponseEntity<List<Supplier>> searchSupplier(@RequestParam String name) {
        return supplierService.searchSupplier(name);
    }
}
