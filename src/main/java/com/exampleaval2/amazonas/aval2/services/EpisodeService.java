package com.exampleaval2.amazonas.aval2.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.exampleaval2.amazonas.aval2.models.Episode;

import com.exampleaval2.amazonas.aval2.repository.EpisodeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final WebClient webClient;

    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository, WebClient.Builder webClientBuilder) {
        this.episodeRepository = episodeRepository;
        this.webClient = webClientBuilder.build();
    }

    public Episode getEpisode(int id) {
        Episode episode = episodeRepository.findById(id); if (episode == null) { episode = fetchEpisodeFromExternalAPI(id); if (episode != null) { episode.setSource("API externo"); episodeRepository.save(episode); } } 
        else { 
            episode.setSource("Base de datos"); 
            episodeRepository.save(episode);
    } return episode;
    }

    private Episode fetchEpisodeFromExternalAPI(int id) {
        String apiUrl = "https://rickandmortyapi.com/api/episode/" + id;

        try {
            ResponseEntity<String> responseEntity = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .toEntity(String.class)
                    .block();

            if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                Episode episode = objectMapper.readValue(responseBody, Episode.class);
                episode.setSource("API externo");

                return episode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
