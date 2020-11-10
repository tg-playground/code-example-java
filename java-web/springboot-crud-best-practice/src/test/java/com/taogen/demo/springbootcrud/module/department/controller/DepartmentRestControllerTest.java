package com.taogen.demo.springbootcrud.module.department.controller;

import com.google.gson.Gson;
import com.taogen.demo.springbootcrud.module.department.entity.Department;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class DepartmentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRestController departmentRestController;

    private static final String SERVLET_PATH = "/departments";

    @Test
    public void findList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SERVLET_PATH)
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void get() throws Exception {
        Integer id = 1;
        StringBuilder path = new StringBuilder().append(SERVLET_PATH).append("/").append(id);
        mockMvc.perform(MockMvcRequestBuilders.get(path.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void delete() throws Exception {
        Integer id = 1;
        StringBuilder path = new StringBuilder().append(SERVLET_PATH).append("/").append(id);
        mockMvc.perform(MockMvcRequestBuilders.delete(path.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(SERVLET_PATH)
                .content("{\"ids\": [1, 2]}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void save() throws Exception {
        Department rightDepartment = new Department();
        rightDepartment.setName("test");
        mockMvc.perform(MockMvcRequestBuilders.post(SERVLET_PATH)
                .content(new Gson().toJson(rightDepartment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
//        .andExpect(MockMvcResultMatchers.jsonPath("$.responseBody.id", ));
    }

    @Test
    public void save_emptyName_400() throws Exception {
        Department department = new Department();
        mockMvc.perform(MockMvcRequestBuilders.post(SERVLET_PATH)
                .content(new Gson().toJson(department))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Name cannot be null")));
    }
    @Test
    public void update() throws Exception {
        Integer id = 1;
        StringBuilder path = new StringBuilder().append(SERVLET_PATH).append("/").append(id);
        Department rightDepartment = new Department();
        rightDepartment.setId(id);
        rightDepartment.setName("test");
        mockMvc.perform(MockMvcRequestBuilders.put(path.toString())
                .content(new Gson().toJson(rightDepartment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}