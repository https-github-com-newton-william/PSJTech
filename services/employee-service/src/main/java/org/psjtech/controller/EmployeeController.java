package org.psjtech.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psjtech.Entity.Employee;
import org.psjtech.response.ApiResponse;
import org.psjtech.service.EmployeeService;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/all")
    public ApiResponse<List<Employee>> getAllEmployees() {
        log.info("Get All Employee data request received.");
        return ApiResponse.success(200, "Employees fetched successfully", employeeService.getAll());
    }

    /**
     * Creates a new employee record in the system.
     *
     * @param employee the {@link Employee} object containing details of the new employee.
     * @return {@code true} if the employee was successfully created.
     */
    @Operation(summary = "Create a new employee", description = "Adds a new employee record into the system.")
    @PostMapping("/add")
    public ApiResponse<Boolean> createEmployee(@RequestBody @Valid Employee employee) {
        log.info("Employee create request received for employee : {}", employee.getEmployeeCode());
        return ApiResponse.success(200, "Employee Added Successfully", employeeService.add(employee));
    }

    /**
     * Updates details of an existing employee.
     *
     * @param employee the {@link Employee} object containing updated details.
     * @return {@code true} if the update was successful, {@code false} otherwise.
     */
    @Operation(summary = "Update an employee", description = "Updates the details of an existing employee based on employeeCode.")
    @PatchMapping("/update")
    public ApiResponse<Boolean> update(@RequestBody @Valid Employee employee) {
        log.info("Update request received for employeeCode: {}", employee.getEmployeeCode());
        return ApiResponse.success(200, "Employee Updated successfully.", employeeService.updateEmployee(employee));
    }

    /**
     * Deletes an employee by their unique employee code.
     *
     * @param employeeCode the unique identifier for the employee within the company.
     * @return {@code true} if deletion was successful, {@code false} otherwise.
     */
    @Operation(summary = "Delete an employee", description = "Removes an employee from the system based on their employeeCode.")
    @DeleteMapping("/remove")
    public ApiResponse<Boolean> deleteEmployee(
            @RequestParam (required = true) String employeeCode) {
        log.info("Delete request received for employeeCode: {}", employeeCode);
        return ApiResponse.success(200, "Employee Deleted successfully.", employeeService.deleteEmployee(employeeCode));
    }

}
