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
    @GetMapping("role/{role}")
    public ResponseEntity<List<Employee>> getEmployeeByRole(@PathVariable String role) {
        return employeeService.getEmployeeByRole(role);
    }

    // Get employee by id
    @GetMapping("get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    // Add employee
    @PostMapping("add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Update employee
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {
        return employeeService.updateEmployee(employee, id);
    }

    // Delete employee
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }

    // Search employee
    @GetMapping("search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam String firstname) {
        return employeeService.searchEmployee(firstname);
    }
}