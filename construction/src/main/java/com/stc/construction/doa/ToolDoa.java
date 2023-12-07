package com.stc.construction.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.construction.model.Tool;

public interface ToolDoa extends JpaRepository<Tool, Integer>{

        List<Tool> findByNameContainingIgnoreCase(String name);

}
