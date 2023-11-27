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

import com.stc.construction.model.Material;
import com.stc.construction.service.MaterialService;

@RestController
@RequestMapping("material")
public class MaterialController {
    
    @Autowired
    MaterialService materialService;

    // Get all materials
    @GetMapping("allMaterials")
    public ResponseEntity<List<Material>> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    // Add material
    @PostMapping("add")
    public ResponseEntity<String> addMaterial(@RequestBody Material material) {
        return materialService.addMaterial(material);
    }

    // Update material (quantity)
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateMaterial(@RequestBody Material material, @PathVariable Integer id) {
        return materialService.updateMaterial(material, id);
    }

    // Delete material
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable Integer id) {
        return materialService.deleteMaterial(id);
    }

    // Search material by name
    @GetMapping("search")
    public ResponseEntity<List<Material>> searchMaterial(@RequestParam String name) {
        return materialService.searchMaterial(name);
    }
}
