package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {



    //El mes restrictiu ha de ser el primer
    @Configuration
    @Order(1)
    public static class RestSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/rest/**")
                    .authorizeRequests().anyRequest().hasRole("USER")
                    .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint())
                    .and().csrf().disable(); //si no petaran els posts / put... hauriem de habilitar de nou i posar el  al thymeleaf: https://www.baeldung.com/csrf-thymeleaf-with-spring-security
        }

        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint(){
            BasicAuthenticationEntryPoint entryPoint =
                    new BasicAuthenticationEntryPoint();
            entryPoint.setRealmName("rest realm");
            return entryPoint;
        }
    }


    @Configuration
    @Order(2)
    public static class WebLoginFormSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    //.and()
                        .antMatchers("/", "/home", "/css/**", "/js/**","/login","/login-error", "/logout" ).permitAll()
                    .and().authorizeRequests()
                        .antMatchers("/books/**", "/genres/**", "/costumers/**", "/about").hasRole("USER")
                    .and()
                        .formLogin()
                        .loginPage("/login").defaultSuccessUrl("/about")
                        //.successForwardUrl("http://localhost:8080/demoapp/")
                        .failureUrl("/login-error")
                        .permitAll()
                    .and()
                        .logout()
                        .logoutSuccessUrl("/login")
                        .permitAll();
        }

        @Bean
        @Override
        public UserDetailsService userDetailsService() {
            UserDetails user =
                    User.withDefaultPasswordEncoder()
                            .username("user")
                            .password("password")
                            .roles("USER")
                            .build();

            return new InMemoryUserDetailsManager(user);
        }
    }

/*
    @Configuration
    @Order(40)
    public static class AnonymousConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests().antMatchers("/", "/home", "/css/**", "/js/**","/login","/login-error", "/logout" ).anonymous();
        }
    }*/


}