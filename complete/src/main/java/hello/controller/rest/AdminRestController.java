package hello.controller.rest;


import hello.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rest/admin")
public class AdminRestController {


    @Autowired
    private AdminService adminService;

    private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);

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
