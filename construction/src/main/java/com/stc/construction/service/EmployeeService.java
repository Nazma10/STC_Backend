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
    public ResponseEntity<Employee> getEmployeeById(Integer id) {
        try {
            return new ResponseEntity<>(employeeDoa.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Employee(), HttpStatus.BAD_REQUEST);
    }

    // Add employee
    public ResponseEntity<String> addEmployee(Employee employee) {
        employeeDoa.save(employee);
        return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
    }

    // Update employee
    public ResponseEntity<String> updateEmployee(Employee employee, Integer id) {
        try {
            Employee existingEmployee = employeeDoa.findById(id).orElseThrow();
        
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setContact_no(employee.getContact_no());
            existingEmployee.setRole(employee.getRole());
        
            employeeDoa.save(existingEmployee);
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.BAD_REQUEST);
    }
     
    // Delete employee
    public ResponseEntity<String> deleteEmployee(Integer id) {
        try {
            Employee existingEmployee = employeeDoa.findById(id).orElseThrow();
            employeeDoa.delete(existingEmployee);

            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.BAD_REQUEST);
    }
}