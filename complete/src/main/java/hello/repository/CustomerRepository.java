package hello.repository;

import java.util.List;
import hello.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
@Component
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);


    @Query("SELECT c.firstName FROM Customer c WHERE c.firstName LIKE CONCAT('%',:username,'%')")
    List<String> findUsersWithPartOfName(@Param("username") String username);

}