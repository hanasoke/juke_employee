/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.juke.employee.model;

import jakarta.validation.constraints.*;

/**
 *
 * @author ASUS
 */
public class EmployeeRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Position is mandatory")
    @Size(min = 2, max = 100, message = "Position must be between 2 and 100 characters")
    private String position;

    @NotBlank(message = "Salary is mandatory")
    private String salary; // Terima sebagai String dulu

    // Default constructor
    public EmployeeRequest() {}

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    // Convert to Employee entity dengan validasi
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setName(this.name);
        employee.setEmail(this.email);
        employee.setPosition(this.position);
        
        // Validasi dan konversi salary
        validateAndSetSalary(employee);
        
        return employee;
    }

    private void validateAndSetSalary(Employee employee) {
        if (salary == null || salary.trim().isEmpty()) {
            throw new RuntimeException("Salary is mandatory");
        }
        
        try {
            Double salaryValue = Double.parseDouble(salary);
            if (salaryValue <= 0) {
                throw new RuntimeException("Salary must be greater than 0");
            }
            employee.setSalary(salaryValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid salary format. Salary must be a valid number. Received: " + salary);
        }
    }
}
