package com.challenge.api.dto;

import com.challenge.api.model.Employee;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CreateEmployeeRequest {
    private String firstName;
    private String lastName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;

    // Constructor with validation
    public CreateEmployeeRequest(
            String firstName,
            String lastName,
            Integer salary,
            Integer age,
            String jobTitle,
            String email,
            Instant contractHireDate) {
        List<String> errors = new ArrayList<>();

        // validation can be implemented with @NotNull, @Size, @NotBlank but I have encountered issues with dependencies
        // Therefore, I have implemented custom validation

        // Validate firstName
        if (firstName == null || firstName.trim().isEmpty()) {
            errors.add("First name is required");
        } else if (firstName.length() < 2 || firstName.length() > 50) {
            errors.add("First name must be between 2 and 50 characters");
        }

        // Validate lastName
        if (lastName == null || lastName.trim().isEmpty()) {
            errors.add("Last name is required");
        } else if (lastName.length() < 2 || lastName.length() > 50) {
            errors.add("Last name must be between 2 and 50 characters");
        }

        // Validate salary
        if (salary == null) {
            errors.add("Salary is required");
        } else if (salary <= 0) {
            errors.add("Salary must be positive");
        }

        // Validate age
        if (age == null) {
            errors.add("Age is required");
        } else if (age < 18) {
            errors.add("Employee must be at least 18 years old");
        }

        // Validate jobTitle
        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            errors.add("Job title is required");
        }

        // @email also caused issues with dependencies. Implemnted custom email validation
        // Validate email
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email is required");
        } else if (!isValidEmail(email)) {
            errors.add("Invalid email format");
        }

        // Validate contractHireDate
        if (contractHireDate == null) {
            errors.add("Contract hire date is required");
        }

        // If there are any validation errors, throw exception
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        // If validation passes, set the fields
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = email;
        this.contractHireDate = contractHireDate;
    }

    // Email validation helper method
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Custom ValidationException class
    public static class ValidationException extends RuntimeException {
        private final List<String> errors;

        public ValidationException(List<String> errors) {
            super("Validation failed: " + String.join(", ", errors));
            this.errors = errors;
        }

        public List<String> getErrors() {
            return errors;
        }
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public Instant getContractHireDate() {
        return contractHireDate;
    }

    public void populateEmployee(Employee newEmployee) {
        if (newEmployee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        newEmployee.setFirstName(this.firstName);
        newEmployee.setLastName(this.lastName);
        newEmployee.setSalary(this.salary);
        newEmployee.setAge(this.age);
        newEmployee.setJobTitle(this.jobTitle);
        newEmployee.setEmail(this.email);
        newEmployee.setContractHireDate(this.contractHireDate);
    }
}
