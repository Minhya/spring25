package org.example.spring25;

import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class FullServerTest {

    //21 Mars

    @LocalServerPort
//    private int port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13.2");

    @Autowired
    PlaygroundRepository playgroundRepository;

    @Autowired //SpringBoot konfigurerar automatiskt rätt port rätt http protokoll
    WebTestClient webClient;

    @Autowired
    WebClient client;

//    @BeforeEach
//    void beforeEach() {
//        webClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
//    }

    //Test using reactive WebnTestClient. Only downloads content from server
    @Test
    void fullServerTest() {

        webClient.get().uri("/playgrounds").exchange()
                .expectStatus()
                .isOk();
//        System.out.println(port);
    }

    //Test html web pages using HTMLUnit. Is a full browser but without GUI.
    @Test
    void htmlEndPointTest() throws IOException {
        HtmlPage htmlPage = client.getPage("/hello");
        assertThat(htmlPage.isHtmlPage()).isTrue();
        assertThat(htmlPage.getTitleText()).isEqualTo("Hello");
        assertThat(htmlPage.getElementById("header2").getTextContent()).isEqualTo("Smaller heading...");


    }

}
