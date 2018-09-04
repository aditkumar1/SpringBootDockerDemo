package com.ennate_learning.service;

import com.ennate_learning.entity.Employee;
import com.ennate_learning.exception.BadRequestException;
import com.ennate_learning.exception.ResourceNotFoundException;
import com.ennate_learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return (List<Employee>)employeeRepository.findAll();
    }

    @Override
    public Employee findOne(String empId) {
        Optional<Employee> employee= employeeRepository.findById(empId);
        if(!employee.isPresent()){
            throw new ResourceNotFoundException("Employee with given ID "+empId+" not found");
        }
        else {
            return employee.get();
        }
    }
    @Transactional
    @Override
    public Employee create(Employee employee) {
        Optional<Employee> employee1= employeeRepository.findOptionalByEmail(employee.getEmail());
        if(employee1.isPresent()){
            throw new BadRequestException("Employee with email "+employee.getEmail()+" already exists");
        }
        return employeeRepository.save(employee);
    }
    @Transactional
    @Override
    public Employee update(String empID, Employee employee) {
        Optional<Employee> employee1= employeeRepository.findById(empID);
        if(!employee1.isPresent())throw new ResourceNotFoundException("Employee with given ID "+empID+" not found");
//        employee1.setEmail(employee.getEmail());
//        employee1.setFirstName(employee.getFirstName());
//        employee1.setLastName(employee.getLastName());
        return employeeRepository.save(employee);
    }
    @Transactional
    @Override
    public void delete(String empId) {
        Optional<Employee> employee1= employeeRepository.findById(empId);
        if(!employee1.isPresent())throw new ResourceNotFoundException("Employee with given ID "+empId+" not found");
        employeeRepository.delete(employee1.get());
    }
}
