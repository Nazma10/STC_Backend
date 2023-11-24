package com.stc.construction.doa;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.construction.model.Employee;

public interface EmployeeDoa extends JpaRepository<Employee, Integer> {
    
    List<Employee> findByRole(String role);
}