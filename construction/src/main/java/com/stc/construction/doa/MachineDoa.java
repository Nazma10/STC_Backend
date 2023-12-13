package com.stc.construction.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.construction.model.Machine;

public interface MachineDoa extends JpaRepository<Machine, Integer> {

    List<Machine> findByNameContainingIgnoreCase(String name);
    
} 