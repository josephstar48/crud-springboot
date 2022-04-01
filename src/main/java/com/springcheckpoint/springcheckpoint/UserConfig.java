package com.springcheckpoint.springcheckpoint;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {
        return args -> {
            User user1 = new User("josh@joshmatos.com", "password");
            User user2 = new User( "jose@jose.com", "Password");
            User user3 = new User( "alic@a.com", "Password");
            User user4 = new User( "m@m.com", "Password");
            User user5 = new User( "a@a.com", "Password");
            userRepository.saveAll(List.of(user1,user2,user3,user4,user5));
        };
    }
}
