package hello.service;

import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//valors possibles de scoped epenent del que volguem: singleton | prototype | request | session | application | websocket
@Scope(value = "singleton")
@Component(value = "customerService")
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);


    public List<Customer> findAll() {
        Iterable<Customer> iterator = customerRepository.findAll();
        List<Customer> customers = new ArrayList<Customer>();
        iterator.forEach(customers::add);
        return customers;
    }



    public Customer get(Long id) {
        Customer customer = customerRepository.findById(id).get();
        log.info("[m:get] Hem trobat " + customer);
        return customer;
    }



    public Customer save(Customer customer) {
        log.info("[m:put] ===================> PERSISTIM NOU CUSTOMER: " + customer);
        Customer customerPersisted = customerRepository.save(customer);
        return customerPersisted;
    }


    public List<Customer> findByLastName(String name) {
        return customerRepository.findByLastName(name);
    }

}
