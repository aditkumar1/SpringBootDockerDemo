package com.ennate_learning.repository;

import com.ennate_learning.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee,String> {
    Optional<Employee> findOptionalByEmail(String email);
}
