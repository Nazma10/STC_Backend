package com.stc.construction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.construction.model.Employee;
import com.stc.construction.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // Get all employees
    @GetMapping("allEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Employee>> getEmployeeByRole(@PathVariable String role) {
        return employeeService.getEmployeeByRole(role);
    }

    // Get employee by id

    // Add employee

    // Update employee

    // Delete employee
}