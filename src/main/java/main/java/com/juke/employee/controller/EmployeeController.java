/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.juke.employee.controller;

import main.java.com.juke.employee.model.Employee;
import main.java.com.juke.employee.model.EmployeeRequest;
import main.java.com.juke.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                return ResponseEntity.ok(employee.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        try {
            Employee employee = employeeRequest.toEmployee();
            Employee savedEmployee = employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        try {
            // Convert untuk partial update
            Employee employeeDetails = new Employee();
            
            if (employeeRequest.getName() != null) {
                employeeDetails.setName(employeeRequest.getName());
            }
            if (employeeRequest.getEmail() != null) {
                employeeDetails.setEmail(employeeRequest.getEmail());
            }
            if (employeeRequest.getPosition() != null) {
                employeeDetails.setPosition(employeeRequest.getPosition());
            }
            if (employeeRequest.getSalary() != null) {
                try {
                    Double salaryValue = Double.parseDouble(employeeRequest.getSalary());
                    employeeDetails.setSalary(salaryValue);
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest()
                        .body("Invalid salary format. Salary must be a valid number. Received: " + employeeRequest.getSalary());
                }
            }
            
            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            return  ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().body("Employee deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    // Health check endpoint 
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Employee Management API is running!");
    }
}