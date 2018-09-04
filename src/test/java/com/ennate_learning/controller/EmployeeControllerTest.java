package com.ennate_learning.controller;

import com.ennate_learning.entity.Employee;
import com.ennate_learning.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class EmployeeControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    EmployeeRepository employeeRepository;

    @Before
    public void setup(){
        Employee employee=new Employee();
        employee.setId("ID1");
        employee.setEmail("asd@gmail.com");
        employee.setLastName("kr");
        employee.setFirstName("ad");
        employeeRepository.save(employee);

        Employee employee1=new Employee();
        employee1.setEmail("adas@gmail.com");
        employee1.setLastName("kp");
        employee1.setFirstName("jp");
        employeeRepository.save(employee1);
    }
    @After
    public void cleanup(){
        employeeRepository.deleteAll();
    }

    @Test
    public void findAll() throws  Exception{
        mvc.perform(MockMvcRequestBuilders.get("/employees")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.is("ID1")));
    }

    @Test
    public void findOne() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/employees/ID1")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is("ID1")));
    }
    @Test
    public void findOneNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/employees/ID2")).
                andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void create() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Employee employeePost=new Employee();
        employeePost.setFirstName("adi");
        employeePost.setLastName("lop");
        employeePost.setEmail("ads@gmail.com");
        mvc.perform(MockMvcRequestBuilders.post("/employees").content(objectMapper.writeValueAsBytes(employeePost)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("ads@gmail.com")));

        mvc.perform(MockMvcRequestBuilders.get("/employees")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }
    @Test
    public void createWithExistingEmail() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        Employee employeePost=new Employee();
        employeePost.setFirstName("adi");
        employeePost.setLastName("lop");
        employeePost.setEmail("adas@gmail.com");
        mvc.perform(MockMvcRequestBuilders.post("/employees").content(objectMapper.writeValueAsBytes(employeePost)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).
                andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}