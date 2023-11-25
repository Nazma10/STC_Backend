package com.stc.construction.doa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.construction.model.Supplier;

public interface SupplierDoa extends JpaRepository<Supplier, Integer>{
    
    List<Supplier> findByType(String type);

    void save(Optional<Supplier> existingSupplier);

}
