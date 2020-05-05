package hello;

import hello.controller.BookController;
import hello.controller.MainController;
import hello.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;






@ComponentScan(basePackages = {"hello"})
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AdminService adminService;



    //A VEURE, PROVAR A TREURE EL AUTOWIRED: //https://blog.marcnuri.com/field-injection-is-not-recommended/

    @Test
    public void listBooks() throws Exception {

        adminService.deleteData(); //Per si de cas, per no tenir problemes insertant
        adminService.insertData(); //Per posar unes cuantes dades a recuperar

        ResultActions result = mockMvc.perform(get("/books/"));

        result.andExpect(content().string(containsString("Book list")))
                .andExpect(content().string(containsString("Brave New World")));
    }


    @Test
    public void getBook() throws Exception {


        ResultActions result = mockMvc.perform(get("/books/11"));

        result.andExpect(content().string(containsString("Book:")))
                .andExpect(content().string(containsString("Foundation")));
    }


}