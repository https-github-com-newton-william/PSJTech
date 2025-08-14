package org.psjtech.service;

import lombok.AllArgsConstructor;
import org.psjtech.Entity.Employee;
import org.psjtech.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public boolean add(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    public boolean deleteEmployee(String employeeCode) {
        return employeeRepository.findByEmployeeCode(employeeCode)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return true;
                })
                .orElse(false);
    }
}
