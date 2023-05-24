package com.exampleaval2.amazonas.aval2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exampleaval2.amazonas.aval2.models.Episode;
import com.exampleaval2.amazonas.aval2.services.EpisodeService;

@RestController
@RequestMapping("/episodes")

public class EpisodeController {
    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Episode> getEpisode(@PathVariable int id) {
        Episode episode = episodeService.getEpisode(id);

        if (episode != null) {
            return ResponseEntity.ok(episode);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
