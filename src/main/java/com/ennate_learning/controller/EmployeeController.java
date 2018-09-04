package com.ennate_learning.controller;

import com.ennate_learning.entity.Employee;
import com.ennate_learning.service.EmployeeService;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.net.Authenticator;
import java.util.List;

@RestController
@RequestMapping(value="employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @RequestMapping(method = RequestMethod.GET,value="{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Employee findOne(@PathVariable("id") String empId){
        return employeeService.findOne(empId);
    }
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }
    @RequestMapping(method = RequestMethod.PUT,value="{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Employee update(@PathVariable("id") String empId,@RequestBody Employee employee){
        return employeeService.update(empId,employee);
    }
    @RequestMapping(method = RequestMethod.DELETE, value="{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable("id") String id){
         employeeService.delete(id);
    }
}