package com.stc.construction.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stc.construction.doa.SupplierDoa;
import com.stc.construction.model.Supplier;

@Service
public class SupplierService {

    @Autowired
    SupplierDoa supplierDoa;

    // Get all suppliers
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        try {
            return new ResponseEntity<>(supplierDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Get supplier by type
    public ResponseEntity<List<Supplier>> getSupplierByType(String type) {
        try {
            return new ResponseEntity<>(supplierDoa.findByType(type), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Get supplier by id
    public ResponseEntity<Supplier> getSupplierById(Integer id) {
        try {
            return new ResponseEntity<>(supplierDoa.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Add supplier
    public ResponseEntity<String> addSupplier(Supplier supplier) {
        supplierDoa.save(supplier);
        return new ResponseEntity<>("Supplier added successfully", HttpStatus.CREATED);
    }

    // Update supplier
    public ResponseEntity<String> updateSupplier(Supplier supplier, Integer id) {
        try {
            Supplier existingSupplier = supplierDoa.findById(id).orElseThrow();

            existingSupplier.setAddress(supplier.getAddress());
            existingSupplier.setContact_no(supplier.getContact_no());
            existingSupplier.setEmail(supplier.getEmail());

            supplierDoa.save(existingSupplier);
            return new ResponseEntity<>("Supplier updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Supplier not found", HttpStatus.BAD_REQUEST);
    }

    // Delete supplier
    public ResponseEntity<String> deleteSupplier(Integer id) {
        try {
            Supplier existingSupplier = supplierDoa.findById(id).orElseThrow();
            supplierDoa.delete(existingSupplier);

            return new ResponseEntity<>("Supplier deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Supplier not found", HttpStatus.BAD_REQUEST);
    }

    // Search supplier
    public ResponseEntity<List<Supplier>> searchSupplier(String name) {
        try {
            return new ResponseEntity<>(supplierDoa.findByNameContainingIgnoreCase(name),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
