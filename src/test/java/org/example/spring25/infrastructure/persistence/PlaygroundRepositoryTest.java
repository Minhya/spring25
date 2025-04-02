package org.example.spring25.infrastructure.persistence;

import org.example.spring25.domain.entity.Playground;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
class PlaygroundRepositoryTest {
    @Autowired
    PlaygroundRepository playgroundRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11-alpine");



    @BeforeEach
    void beforeEach() {
        var playground = new Playground();
        playground.setName("Playground");
        playgroundRepository.save(playground);
    }

    @Test
    void findAll() {
        var result = playgroundRepository.findAll();
        assertThat(result).hasSize(1);

    }

    @Test
    void findAll2() {
        var result = playgroundRepository.findAll();
        assertThat(result).hasSize(1);

    }

    @Test
    void findAllByNameReturnsOnlyPlaygroundsWithMatchingName() {
        var playground = new Playground();
        playground.setName("Test");
        playgroundRepository.save(playground);
        var result = playgroundRepository.findAllByName("Test");
        assertThat(result)
                .hasSize(1)
                .extracting(Playground::getName)
                .containsExactly("Test");
    }

}