package com.labs.lab402.service.interfaces;

import com.labs.lab402.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> getEmployeesByStatus(String status);

    List<Employee> getEmployeesByDepartment(String department);

    void createEmployee(Employee employee);

    void updateEmployeeStatus(Long id, String status);

    void updateEmployeeDepartment(Long id, String department);


    void deleteEmployee(Long id);
}
