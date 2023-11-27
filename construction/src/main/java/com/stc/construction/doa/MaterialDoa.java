package com.stc.construction.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.construction.model.Material;

public interface MaterialDoa extends JpaRepository<Material, Integer>{

    List<Material> findByNameContainingIgnoreCase(String name);

}
