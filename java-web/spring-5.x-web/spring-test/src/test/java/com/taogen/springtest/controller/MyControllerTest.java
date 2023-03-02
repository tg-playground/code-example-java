package com.taogen.springtest.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// entry-point to start using Spring Test framework
@RunWith(SpringJUnit4ClassRunner.class)
// Loading spring context configuration.
@ContextConfiguration(locations = "classpath:springmvc.xml") // @ContextConfiguration(class={ApplicationConfig.class})
// Loading the web application context. Default path src/main/webapp. You can override by passing value argument.
@WebAppConfiguration()
public class MyControllerTest
{
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void verifyContext() {
        ServletContext servletContext = wac.getServletContext();

        org.junit.Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("myController"));
    }

    @Test
    public void testViewName() throws Exception
    {
        this.mockMvc.perform(get("/toIndex")).andDo(print())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/index.jsp"));
    }

    @Test
    public void testResponseBody() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/returnJson")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.user.name").value("Tom"))
                .andReturn();
        Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
    }

    @Test
    public void testPostRequest() throws Exception {
        this.mockMvc.perform(post("/returnJson").param("name", "Tom1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(content().string(containsString("Tom1")));
    }
}
