package hello.controller;


import hello.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    //read "Field injection is not recommended – Spring IOC"
    //https://blog.marcnuri.com/field-injection-is-not-recommended/
    @Autowired
    private AdminService adminService;

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);


    //http://localhost:8080/admin/insertData
    @RequestMapping("/insertData")
    public String insertData()
    {
        log.info("[m:insertData] =============> El log funciona!!!! <=============");

        String result = adminService.insertData();
        return result;

    }



    //http://localhost:8080/admin/retrieveData
    @RequestMapping("/retrieveData")
    public String retrieveData()
    {

        log.info("[m:retrieveData] =============> El log funciona!!!! <=============");

        String result = adminService.retrieveData();
        return result;

    }


    //http://localhost:8080/admin/deleteData
    @RequestMapping("/deleteData")
    public String deleteData()
    {

        log.info("[m:deleteData] =============> El log funciona!!!! <=============");

        adminService.deleteData();

        return "Deleted OK";

    }
}
