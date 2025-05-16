package com.labs.lab402.controller.impl;

import com.labs.lab402.controller.dto.EmployeeDepartmentDTO;
import com.labs.lab402.controller.dto.EmployeeStatusDTO;
import com.labs.lab402.model.Employee;
import com.labs.lab402.model.Patient;
import com.labs.lab402.service.interfaces.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeControler {
    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/id/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees/status/{status}")
    public List<Employee> getEmployeesByStatus(@PathVariable String status) {
        return employeeService.getEmployeesByStatus(status);
    }

    @GetMapping("/employees/department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody @Valid Employee employee) {
        employeeService.createEmployee(employee);
    }

    @PatchMapping("/employees/status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeStatus(@RequestBody EmployeeStatusDTO employeeStatusDTO, @PathVariable Long id) {
        employeeService.updateEmployeeStatus(id, employeeStatusDTO.getStatus());
    }

    @PatchMapping("/employees/department/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeDepartment(@RequestBody EmployeeDepartmentDTO employeeDepartmentDTO, @PathVariable Long id) {
        employeeService.updateEmployeeDepartment(id, employeeDepartmentDTO.getDepartment());
    }

    @DeleteMapping("/employees/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }









}
