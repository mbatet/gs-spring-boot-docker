package hello;

import hello.controller.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ComponentScan(basePackages = {"hello"})
@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser
    public void homePage() throws Exception {
        // N.B. jsoup can be useful for asserting HTML content
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Welcome.")));
    }

    @Test
    @WithMockUser
    public void greeting() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(content().string(containsString("Hello, World!")));
    }

    @Test
    @WithMockUser
    public void greetingWithUser() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "Greg"))
                .andExpect(content().string(containsString("Hello, Greg!")));
    }

}