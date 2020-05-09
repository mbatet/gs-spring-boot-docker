package hello;

import hello.controller.BookController;
import hello.controller.MainController;
import hello.repository.BookRepository;
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
import org.springframework.security.test.context.support.WithMockUser;
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
    @Autowired
    private BookRepository bookRepository;



    //A VEURE, PROVAR A TREURE EL AUTOWIRED: //https://blog.marcnuri.com/field-injection-is-not-recommended/

    @Test
    @WithMockUser
    public void listBooks() throws Exception {


        adminService.cleanAndInsertData(); //Per posar unes cuantes dades a recuperar
        long count = bookRepository.count();

        ResultActions result = mockMvc.perform(get("/books/"));

        result.andExpect(content().string(containsString("We have " + count + " books")))
                .andExpect(content().string(containsString("Brave New World")));
    }


    @Test
    @WithMockUser
    public void getBook() throws Exception {


        ResultActions result = mockMvc.perform(get("/books/11"));

        result.andExpect(content().string(containsString("Book:")))
                .andExpect(content().string(containsString("Foundation")));
    }


}