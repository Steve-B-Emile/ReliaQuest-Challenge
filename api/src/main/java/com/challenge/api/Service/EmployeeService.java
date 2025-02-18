package com.challenge.api.Service;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    private final Map<UUID, Employee> employeeStore = new HashMap<>();

    // get all employees
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeStore.values());
    }

    // get employee by UUID
    public Employee getEmployeeByUuid(UUID uuid) {
        if (uuid == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID cannot be null");
        }
        // if found return employee, else throw exception
        return Optional.ofNullable(employeeStore.get(uuid))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Employee not found for UUID: %s", uuid)));
    }

    // create employee
    public Employee createEmployee(CreateEmployeeRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request cannot be null");
        }

        // Check for duplicate email
        if (employeeStore.values().stream()
                .anyMatch(employee -> employee.getEmail().equals(request.getEmail()))) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, String.format("Employee with email %s already exists", request.getEmail()));
        }
        // store data with exception handling
        try {
            Employee newEmployee = new EmployeeImpl();
            UUID newUuid = UUID.randomUUID();
            newEmployee.setUuid(newUuid);
            request.populateEmployee(newEmployee);

            employeeStore.put(newUuid, newEmployee);
            return newEmployee;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save employee", e);
        }
    }
}
