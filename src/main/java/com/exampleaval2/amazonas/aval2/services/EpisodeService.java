package com.exampleaval2.amazonas.aval2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exampleaval2.amazonas.aval2.models.Episode;

import com.exampleaval2.amazonas.aval2.repository.EpisodeRepository;

import jakarta.transaction.Transactional;

@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final RestTemplate restTemplate;

    public EpisodeService(EpisodeRepository episodeRepository, RestTemplate restTemplate) {
        this.episodeRepository = episodeRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public Episode getEpisodeByNumber(Integer episodeNumber) {
        Episode episode = episodeRepository.findById(episodeNumber.intValue());

        if (episode == null) {
            String apiUrl = "https://rickandmortyapi.com/api/episode/" + episodeNumber;
            ResponseEntity<Episode> response = restTemplate.getForEntity(apiUrl, Episode.class);
            episode = response.getBody();

            if (episode != null) {
                episode.setSource(Episode.EpisodeSource.API);
                episodeRepository.save(episode);
            }
        } else {
            episode.setSource(Episode.EpisodeSource.DATABASE);
        }

        return episode;
    }
}
