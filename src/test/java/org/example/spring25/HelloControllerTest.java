package org.example.spring25;

import org.example.spring25.domain.PlaygroundService;
import org.example.spring25.domain.entity.Playground;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
@ExtendWith(MockitoExtension.class)
class HelloControllerTest {

    @MockitoBean
    PlaygroundService playgroundService;
    /* Används för att göra HTTP post request,
    går ej in i servern.
    Går direkt in i servern. */
    @Autowired
    MockMvc mockMvc;

    @Test
    void getPlaygrounds() throws Exception {
        when(playgroundService.getAllPlaygrounds()).thenReturn(List.of(new Playground()));
        mockMvc.perform(get("/playgrounds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", Matchers.is(1)));


    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(containsString("Hello, World!")));
    }

}