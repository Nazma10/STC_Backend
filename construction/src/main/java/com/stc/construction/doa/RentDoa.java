package com.stc.construction.doa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.construction.model.Rent;
import com.stc.construction.model.Tool;

public interface RentDoa extends JpaRepository<Rent, Integer> {

    Optional<Tool> findByToolIdAndStatus(Integer id, String string);

    
} 
