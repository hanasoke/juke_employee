/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.juke.employee.service;

import main.java.com.juke.employee.model.Employee;
import main.java.com.juke.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found in the database");
        }
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        return employee;
    }

    public Employee createEmployee(Employee employee) {        
        // Check if email already exists
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        // Partial update - only update provided fields
        if (employeeDetails.getName() != null && !employeeDetails.getName().trim().isEmpty()) {
            employee.setName(employeeDetails.getName());
        }
        
        if (employeeDetails.getEmail() != null && !employeeDetails.getEmail().trim().isEmpty()) {
            // Check if email is being changed and if it already exists
            if (!employee.getEmail().equals(employeeDetails.getEmail()) && 
                employeeRepository.existsByEmail(employeeDetails.getEmail())) {
                throw new RuntimeException("Email already exists: " + employeeDetails.getEmail());
            }
            employee.setEmail(employeeDetails.getEmail());
        }
        
        if (employeeDetails.getPosition() != null && !employeeDetails.getPosition().trim().isEmpty()) {
            employee.setPosition(employeeDetails.getPosition());
        }
        
        if (employeeDetails.getSalary() != null) {
            // Validate salary format
            if (employeeDetails.getSalary() <= 0) {
                throw new RuntimeException("Salary must be greater than 0");
            }
            employee.setSalary(employeeDetails.getSalary());
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}