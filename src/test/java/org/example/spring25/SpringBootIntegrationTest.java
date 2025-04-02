package org.example.spring25;

import org.example.spring25.config.Config;
import org.example.spring25.domain.entity.Playground;
import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SpringBootIntegrationTest.TestConfig.class)
@Testcontainers
public class SpringBootIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11-alpine");

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Config config;

    @Autowired
    PlaygroundRepository playgroundRepository;

    @BeforeEach
    void beforeEach() {
        playgroundRepository.deleteAll();
        var playground = new Playground();
        playground.setName("Playground 1");
        playgroundRepository.save(playground);
        playground = new Playground();
        playground.setName("Playground 2");
        playgroundRepository.save(playground);
    }
    @Test
    void getAllPlaygrounds() throws Exception {
        mockMvc.perform(get("/playgrounds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Playground 1"))
                .andExpect(jsonPath("$[1].name").value("Playground 2"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", Matchers.is(2)));
    }

    @TestConfiguration
    static
    class TestConfig {
        @Bean
        CommandLineRunner commandLineRunner(PlaygroundRepository playgroundRepository) {
            return args -> {

            };
        }
    }


}
