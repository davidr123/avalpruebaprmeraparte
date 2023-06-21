package com.exampleaval2.amazonas.aval2.models;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Informacion {
    @Id
    private int episodeId;
    private String episode;
    private String AirDate;
    @ElementCollection
    private List<String> characters;
    
    public Informacion() {
    }



    public Informacion(int episodeId, String episode, String airDate, List<String> characters) {
        this.episodeId = episodeId;
        this.episode = episode;
        AirDate = airDate;
        this.characters = characters;
    }



    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }



    public String getAirDate() {
        return AirDate;
    }



    public void setAirDate(String airDate) {
        AirDate = airDate;
    }



    public void setId(int id) {
    }

    
}
