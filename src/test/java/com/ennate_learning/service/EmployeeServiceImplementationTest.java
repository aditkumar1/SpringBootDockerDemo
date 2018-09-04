package com.ennate_learning.service;

import com.ennate_learning.entity.Employee;
import com.ennate_learning.exception.ResourceNotFoundException;
import com.ennate_learning.repository.EmployeeRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplementationTest {

    @TestConfiguration
    static class EmployeeServiceImplementationtestConfiguration{
        @Bean
        public EmployeeService getService(){
            return new EmployeeServiceImplementation();
        }

    }

    //because employee service is a component and it has to be tested, only this will be autowired
    @Autowired
    EmployeeService employeeService;
    //any other dependency will be mocked
    @MockBean
    private EmployeeRepository employeeRepository;
    List<Employee> employeeListMocked;
    @Before
    public void setup(){
        Employee employee=new Employee();
        employee.setEmail("asd@gmail.com");
        employee.setLastName("kr");
        employee.setFirstName("ad");
        employeeListMocked =Collections.singletonList(employee);

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeListMocked);
        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
    }

    @After
    public void cleanup(){

    }
    @Test
    public void findAll() {
        List<Employee> employeeList= employeeService.findAll();
        Assert.assertEquals("Employee list should match",employeeListMocked,employeeList);
    }


    @Test
    public void findOne() {
        Employee employeeOne=employeeService.findOne(employeeListMocked.get(0).getId());
        Assert.assertEquals("ID should match",employeeListMocked.get(0),employeeOne);
    }
    @Test(expected=ResourceNotFoundException.class)
    public void findOneNotFound() throws Exception{
        Employee employeeOne=employeeService.findOne("0123");
        Assert.assertEquals("ID should not match",employeeListMocked.get(0),employeeOne);
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}