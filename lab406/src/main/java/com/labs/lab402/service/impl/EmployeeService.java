package com.labs.lab402.service.impl;

import com.labs.lab402.model.Employee;
import com.labs.lab402.repository.EmployeeRepository;
import com.labs.lab402.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee " + id + " not found");
        return employeeOptional.get();
    }

    public List<Employee> getEmployeesByStatus(String status) {
        return employeeRepository.findByStatus(status);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployeeStatus(Long id, String status) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee " + id + " not found");
        employeeOptional.get().setStatus(status);
        employeeRepository.save(employeeOptional.get());
    }

    public void updateEmployeeDepartment(Long id, String department) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee " + id + " not found");
        employeeOptional.get().setDepartment(department);
        employeeRepository.save(employeeOptional.get());
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

