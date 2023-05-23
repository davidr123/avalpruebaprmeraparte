package com.exampleaval2.amazonas.aval2.task;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.exampleaval2.amazonas.aval2.repository.EpisodeRepository;

import jakarta.transaction.Transactional;

@Component
@EnableScheduling
public class ScheduledTask {
    private final EpisodeRepository episodeRepository;

    public ScheduledTask(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }
    @Transactional
    @Scheduled(fixedRate = 300000) // 5 minutes = 5 * 60 * 1000 milliseconds
    public void deleteOldEpisodes() {
        // Delete episodes older than 5 minutes
        // Adjust the condition as per your requirements
        episodeRepository.deleteByCreatedBefore(LocalDateTime.now().minusMinutes(5));
        System.out.println("Tarea programada completada");
       
    }
}
