package org.example.spring25.config;

import org.example.spring25.domain.entity.Playground;
import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test") // modifiera för att denna klassen inte ska laddas i test klasser
public class Config {
    @Bean
    CommandLineRunner runner(PlaygroundRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Playground playground = new Playground();
                playground.setName("Test playground");
                repository.save(playground);
            }

        };
    }
}
