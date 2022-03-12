package com.example.demo.users;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class UsersConfig {

    @Bean
    CommandLineRunner commandLineRunner(UsersRepository repository){
        return args -> {
            Users chan = new Users(
                    "Chan",
                    "chan@gmail.com",
                    "1998-03-09"
            );
            Users mm = new Users(
                    "mm",
                    "mm@gmail.com",
                   "1779-04-05"
            );
           repository.saveAll(List.of(chan, mm));

        };
    }
}
