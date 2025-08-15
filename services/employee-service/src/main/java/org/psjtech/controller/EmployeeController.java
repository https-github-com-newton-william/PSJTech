package org.psjtech.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psjtech.Entity.Employee;
import org.psjtech.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Retrieves a list of all employees.
     *
     * @return list of all {@link Employee} objects present in the system.
     */
    @Operation(summary = "Retrieve all employees", description = "Fetches the complete list of employees from the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of employees",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)))
    })
    @GetMapping("/all")
    public List<Employee> getAllEmployee() {
        log.info("Get All Employee data request received.");
        return employeeService.getAll();
    }

    /**
     * Creates a new employee record in the system.
     *
     * @param employee the {@link Employee} object containing details of the new employee.
     * @return {@code true} if the employee was successfully created.
     */
    @Operation(summary = "Create a new employee", description = "Adds a new employee record into the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee details provided")
    })
    @PostMapping("/add")
    public Boolean createEmployee(@RequestBody @Valid Employee employee) {
        employeeService.add(employee);
        log.info("Employee added successfully: {}", employee.getEmployeeCode());
        return true;
    }

    /**
     * Updates details of an existing employee.
     *
     * @param employee the {@link Employee} object containing updated details.
     * @return {@code true} if the update was successful, {@code false} otherwise.
     */
    @Operation(summary = "Update an employee", description = "Updates the details of an existing employee based on employeeCode.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PatchMapping("/update")
    public Boolean update(@RequestBody @Valid Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    /**
     * Deletes an employee by their unique employee code.
     *
     * @param employeeCode the unique identifier for the employee within the company.
     * @return {@code true} if deletion was successful, {@code false} otherwise.
     */
    @Operation(summary = "Delete an employee", description = "Removes an employee from the system based on their employeeCode.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })

    @DeleteMapping("/remove")
    public Boolean deleteEmployee(
            @RequestParam (required = true) String employeeCode) {
        log.info("Delete request received for employeeCode: {}", employeeCode);
        return employeeService.deleteEmployee(employeeCode);
    }

}
