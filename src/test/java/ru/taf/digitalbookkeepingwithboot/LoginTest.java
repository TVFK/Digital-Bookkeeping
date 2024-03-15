package ru.taf.digitalbookkeepingwithboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import ru.taf.digitalbookkeepingwithboot.controllers.AuthController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthController controller;

    @Test
    void contextLoads() throws Exception {
        this.mockMvc
                .perform(get("/auth/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, user!")));
    }

    @Test
    public void loginTest() throws Exception{
        this.mockMvc
                .perform(get("/auth/admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/auth/login"));
    }

    @Test
    public void correctLoginTest() throws Exception{
        this.mockMvc
                .perform(formLogin().user("user").password("user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/auth/login"));
    }

//    @Test
//    public void badCredential() throws Exception{
//        this.mockMvc
//                .perform()
//    }
}
