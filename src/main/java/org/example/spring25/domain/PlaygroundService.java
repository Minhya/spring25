package org.example.spring25.domain;

import jakarta.transaction.Transactional;
import org.example.spring25.domain.entity.Playground;
import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Transactional
@Service
public class PlaygroundService {
    PlaygroundRepository playgroundRepository;

    public PlaygroundService(PlaygroundRepository playgroundRepository) {
        this.playgroundRepository = playgroundRepository;
    }
    public List<Playground> getAllPlaygrounds() {
        return playgroundRepository.findAll();
    }


    public void addRandomPlayground(int count) {
        for (int i = 0; i < count; i++) {
            Playground playground = new Playground();
            playground.setName("Playground" + i);
            playgroundRepository.save(playground);
        }
    }
}
