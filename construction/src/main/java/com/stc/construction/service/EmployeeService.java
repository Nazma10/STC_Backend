package com.stc.construction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stc.construction.doa.EmployeeDoa;
import com.stc.construction.model.Employee;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDoa employeeDoa;

    // Get all employees
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            return new ResponseEntity<>(employeeDoa.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Get employee by role
    public ResponseEntity<List<Employee>> getEmployeeByRole(String role) {
        try {
            return new ResponseEntity<>(employeeDoa.findByRole(role), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    // Get employee by id

    // Add employee

    // Update employee

    // Delete employee
}