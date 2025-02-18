package com.challenge.api.model;

import java.time.Instant;
import java.util.UUID;

/**
 * Every abstraction of an Employee should, at the bare minimum, implement this interface. Consider this a binding
 * contract for the domain model of an Employee.
 */
public interface Employee {

    UUID getUuid();

    /**
     * Set by either the Service or Data layer.
     * @param uuid required non-null
     */
    void setUuid(UUID uuid);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getFullName();

    void setFullName(String fullName);

    Integer getSalary();

    void setSalary(Integer salary);

    Integer getAge();

    void setAge(Integer age);

    String getJobTitle();

    void setJobTitle(String jobTitle);

    String getEmail();

    void setEmail(String email);

    Instant getContractHireDate();

    void setContractHireDate(Instant contractHireDate);

    /**
     * Nullable.
     * @return null, if Employee has not been terminated.
     */
    Instant getContractTerminationDate();

    void setContractTerminationDate(Instant contractTerminationDate);
}
