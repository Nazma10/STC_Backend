package com.stc.construction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stc.construction.doa.MaterialDoa;
import com.stc.construction.model.Material;

@Service
public class MaterialService {

    @Autowired
    MaterialDoa materialDoa;

    // Get all materials
    public ResponseEntity<List<Material>> getAllMaterials() {
        try {
            return new ResponseEntity<>(materialDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Add material
    public ResponseEntity<String> addMaterial(Material material) {
        materialDoa.save(material);
        return new ResponseEntity<>("Material added successfully", HttpStatus.CREATED);
    }

    // Update material (quantity)
    public ResponseEntity<String> updateMaterial(Material material, Integer id) {
        try {
            Material existingMaterial = materialDoa.findById(id).orElseThrow();

            existingMaterial.setQuantity(material.getQuantity());

            materialDoa.save(existingMaterial);

            return new ResponseEntity<>("Material updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Material not found", HttpStatus.BAD_REQUEST);
    }

    // Delete material
    public ResponseEntity<String> deleteMaterial(Integer id) {
        try {
            Material existingMaterial = materialDoa.findById(id).orElseThrow();
            materialDoa.delete(existingMaterial);

            return new ResponseEntity<>("Material deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Material not found", HttpStatus.BAD_REQUEST);
    }

    // Search material by name
    public ResponseEntity<List<Material>> searchMaterial(String name) {
        try {
            return new ResponseEntity<>(materialDoa.findByNameContainingIgnoreCase(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}
