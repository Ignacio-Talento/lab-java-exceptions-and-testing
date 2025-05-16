package com.labs.lab402.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.lab402.controller.dto.EmployeeStatusDTO;
import com.labs.lab402.model.Employee;
import com.labs.lab402.repository.EmployeeRepository;
import com.labs.lab402.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControlerTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    Employee employee;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        employee = new Employee(457221L, "cardiology", "Pepe Grillo", "ON_CALL");
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteById(457221L);
    }

    @Test
    void getAllEmployees_validRequest_allEmployees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("356712"));
    }

    @Test
    void getEmployeeById_validId_correctEmployee() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/employees/id/356712"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("356712"));
    }

    @Test
    void getEmployeeById_invalidId_notFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/employees/id/444555").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getEmployeesByStatus() {
    }

    @Test
    void getEmployeesByDepartment() {
    }

    @Test
    void createEmployee_validBody_employeeCreated() throws Exception {
        String body = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post("/api/employees").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        System.out.println(employeeRepository.findAll().toString());

        assertTrue(employeeRepository.findAll().toString().contains("457221"));
    }

    @Test
    void updateEmployeeStatus_validBody_employeeUpdated() throws Exception {
        EmployeeStatusDTO employeeStatusDTO = new EmployeeStatusDTO("OFF");

        String body = objectMapper.writeValueAsString(employeeStatusDTO);

        mockMvc.perform(patch("/api/employees/status/356712").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(employeeRepository.findAll().toString().contains("OFF"));
    }

    @Test
    void updateEmployeeDepartment() {
    }

    @Test
    void deleteEmployee_validRequest_employeeDeleted() throws Exception {
        employeeRepository.save(employee);

        mockMvc.perform(delete("/api/employees/id/457221"))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(employeeRepository.findAll().toString().contains("457221"));
    }
}