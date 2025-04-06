package org.example.spring25.domain;

import jakarta.transaction.Transactional;
import org.example.spring25.domain.entity.Playground;
import org.example.spring25.infrastructure.persistence.PlaygroundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Transactional
@Service
public class PlaygroundService {

    Logger log = LoggerFactory.getLogger(PlaygroundService.class);

    PlaygroundRepository playgroundRepository;


    public PlaygroundService(PlaygroundRepository playgroundRepository) {
        this.playgroundRepository = playgroundRepository;
    }


    @Cacheable("allPlaygrounds")
    public List<Playground> getAllPlaygrounds() {
        log.info("Fetching all playgrounds");
        return playgroundRepository.findAll();
    }

    @CacheEvict(value = "allPlaygrounds", allEntries = true)
    public Playground addPlayground(Playground playground) {
        return playgroundRepository.save(playground);
    }


    public void addRandomPlayground(int count) {
        for (int i = 0; i < count; i++) {
            Playground playground = new Playground();
            playground.setName("Playground" + i);
            playgroundRepository.save(playground);
        }
    }
}
