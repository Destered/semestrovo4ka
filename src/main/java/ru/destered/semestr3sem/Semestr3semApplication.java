package ru.destered.semestr3sem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Semestr3semApplication {

    public static void main(String[] args) {
        SpringApplication.run(Semestr3semApplication.class, args);
    }

}
