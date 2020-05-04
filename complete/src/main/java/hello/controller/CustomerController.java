package hello.controller;


import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Iterable<Customer> index() {

        return customerRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Customer get(@PathVariable @NotNull Long id) {
        return customerRepository.findById(id).get();
    }

    @RequestMapping(value = "/findByLastName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public List<Customer> findBy(@PathVariable @NotNull String name) {
        return customerRepository.findByLastName(name);
    }

    /*
     * En aquest cas, s'injecta el nou costumer com un json al requet body del PUT
     * */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Customer put(@RequestBody @NotNull Customer costumer, BindingResult result) {
        return customerRepository.save(costumer);
    }
}
