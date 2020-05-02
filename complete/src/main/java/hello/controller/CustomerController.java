package hello.controller;


import hello.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    //read "Field injection is not recommended – Spring IOC"
    //https://blog.marcnuri.com/field-injection-is-not-recommended/
    @Autowired
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


    //http://localhost:8080/customer/insertData
    @RequestMapping("/insertData")
    public String insertData()
    {
        log.info("[m:insertData] =============> El log funciona!!!! <=============");

        String result = customerService.insertData();
        return result;

    }



    //http://localhost:8080/customer/retrieveData
    @RequestMapping("/retrieveData")
    public String retrieveData()
    {

        log.info("[m:retrieveData] =============> El log funciona!!!! <=============");

        String result = customerService.retrieveData();
        return result;

    }
}
