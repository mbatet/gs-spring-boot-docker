package hello.controller.rest;


import hello.model.Customer;
import hello.service.CustomerService;
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
@RequestMapping(value = "/rest/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);


    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Customer> index() {

        return customerService.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Customer get(@PathVariable @NotNull Long id) {
        return customerService.get(id);
    }

    @RequestMapping(value = "/findByLastName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public List<Customer> findBy(@PathVariable @NotNull String name) {
        return customerService.findByLastName(name);
    }

    /*
     * En aquest cas, s'injecta el nou costumer com un json al requet body del PUT
     * */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Customer put(@RequestBody @NotNull Customer costumer, BindingResult result) {
        return customerService.save(costumer);
    }
}
