package com.exampleaval2.amazonas.aval2.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{episodeNumber}")
    public Episode getEpisodeByNumber(@PathVariable Integer episodeNumber) {
        return episodeService.getEpisodeByNumber(episodeNumber);
    }
}
