package org.example.spring25.domain;

import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaygroundServiceTest {

    @InjectMocks
    PlaygroundService playgroundService;

    @Mock
    PlaygroundRepository playgroundRepository;

   @Test
    void getAllPlaygrounds() {
       playgroundRepository = mock(PlaygroundRepository.class);
       when(playgroundRepository.findAll())
               .thenReturn(List.of());
       playgroundService = new PlaygroundService(playgroundRepository);

       assertThat(playgroundService.getAllPlaygrounds().isEmpty()).isTrue();
   }

}