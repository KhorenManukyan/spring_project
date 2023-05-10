package spring_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_demo.service.AccountService;
import spring_demo.service.impl.AccountServiceImpl;

@Configuration
public class ServiceConfig {
    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }
}
