# ReliaQuest Entry-Level Java Challenge Solution

## Overview
I implemented a REST API interface to bridge the existing employee management system with the Employees-R-US SaaS platform. The solution focuses on creating a robust, maintainable API with proper validation and error handling, while following clean coding practices.

## Implementation Details

### Domain Model Design
- Used `Employee` interface as a contract for the domain model, ensuring consistent implementation across the system
- Implemented `EmployeeImpl` class with proper encapsulation and data management


### Data Validation
Implemented comprehensive validation in `CreateEmployeeRequest`:
- Custom validation for all required fields
- Age verification (minimum 18 years)
- Email format validation using regex pattern
- Salary validation (must be positive)
- Name length validation (2-50 characters)


### Service Layer Implementation
The `EmployeeService` class provides three main operations:

1. **getAllEmployees()**
   - Returns a new ArrayList of all employees to prevent external modification
   - Simple and efficient implementation using collection values

2. **getEmployeeByUuid(UUID uuid)**
   - Null check for input UUID
   - Uses Optional for clean null handling
   - Throws appropriate HTTP status exceptions

3. **createEmployee(CreateEmployeeRequest request)**
   - Validates request object
   - Checks for duplicate email addresses
   - Generates new UUID for each employee
   - Proper exception handling with meaningful messages

### Error Handling
- Custom `ValidationException` class for handling validation errors
- Used Spring's `ResponseStatusException` for HTTP-specific errors
- Meaningful error messages for better debugging
- Proper null checking throughout the application



### Clean Code Practices
1. **Separation of Concerns**
   - DTO layer for request handling
   - Service layer for business logic
   - Model layer for data representation

2. **Input Validation**
   - Centralized validation in DTO
   - Comprehensive error messages
   - Proper exception handling

3. **Code Organization**
   - Logical package structure
   - Clear class responsibilities
   - Consistent naming conventions


## Statement
I appreciate the opportunity to work on this challenge and share my solution with the ReliaQuest team. This exercise was engaging and allowed me to demonstrate my approach to software development. I look forward to any feedback and the possibility of discussing my solution in more detail. Thank you for your time and consideration.
