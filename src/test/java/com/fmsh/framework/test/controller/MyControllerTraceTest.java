package com.fmsh.framework.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fmsh.framework.test.BaseTest;

public class MyControllerTraceTest extends BaseTest {

    private MockMvc mockMvc;

    @Autowired
    private MyController myController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    }

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get("/api/get")).andExpect(status().isOk());
    }

}
