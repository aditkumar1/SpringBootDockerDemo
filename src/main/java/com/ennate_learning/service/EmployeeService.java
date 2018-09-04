package com.ennate_learning.service;

import com.ennate_learning.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findOne(String empId);
    Employee create(Employee employee);
    Employee update(String empID, Employee employee);
    void delete(String empId);
}
