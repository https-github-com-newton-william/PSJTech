package org.psjtech.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;  // Unique ID for internal system use

    @Column(name = "employee_code", nullable = false, unique = true, length = 50)
    private String employeeCode; // Company-specific employee ID

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate dateOfJoining;

    @Column(length = 100)
    private String department;

    @Column(length = 100)
    private String designation;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 200)
    private String address;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String state;

    @Column(length = 20)
    private String postalCode;

    @Column(length = 100)
    private String country;

    @Column(length = 50)
    private String employmentType; // e.g., Full-Time, Part-Time, Contract

    @Column
    private Double salary;

    @Column(length = 20)
    private String status; // Active, Inactive, Terminated, On Leave

    @PrePersist
    protected void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
