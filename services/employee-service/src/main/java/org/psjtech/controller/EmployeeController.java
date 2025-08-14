package org.psjtech.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psjtech.Entity.Employee;
import org.psjtech.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAllEmployee() {
        log.info("Get All Employee data request received..");
        return employeeService.getAll();
    }

    @PostMapping("/add")
    public Boolean createEmployee(@RequestBody Employee employee) {
        employeeService.add(employee);
        log.info("Employee Added successfully");
        return true;
    }

    @DeleteMapping("/remove")
    public Boolean deleteEmployee(@RequestParam(required = true) String employeeCode) {
        log.info("Employee deleted successfully.");
        return employeeService.deleteEmployee(employeeCode);
    }

}
