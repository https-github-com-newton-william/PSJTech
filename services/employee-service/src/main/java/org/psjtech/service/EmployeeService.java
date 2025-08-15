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
    /**
     * Updates the details of an existing employee in the system.
     * <p>
     * This method searches for an employee by their unique {@code employeeCode}.
     * If the employee exists, their details are updated with the provided {@link Employee} object.
     * If no matching employee is found, the update operation is not performed and {@code false} is returned.
     * </p>
     *
     * @param employee the {@link Employee} object containing updated employee details.
     *                 The {@code employeeCode} must correspond to an existing employee in the database.
     * @return {@code true} if the employee exists and the update was successful,
     *         {@code false} if no matching employee was found.
     */
    public boolean updateEmployee(Employee employee) {
        return employeeRepository.findByEmployeeCode(employee.getEmployeeCode())
                .map(emp -> {
                    employeeRepository.save(employee);
                    return true;
                })
                .orElse(false);
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
