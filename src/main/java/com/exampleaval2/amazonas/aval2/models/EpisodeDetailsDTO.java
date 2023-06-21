package com.exampleaval2.amazonas.aval2.models;

import java.time.LocalDateTime;
import java.util.List;

public class EpisodeDetailsDTO {
    
    private int id;
    private String name;
    private String episode;
    private String airDate;
    private List<String> characters;
    private String url;
    private LocalDateTime created;
    private boolean fromAPI;

    public EpisodeDetailsDTO() {
    }



    public EpisodeDetailsDTO(int id, String name, String episode, String airDate, List<String> characters, String url,
            LocalDateTime created, boolean fromAPI) {
        this.id = id;
        this.name = name;
        this.episode = episode;
        this.airDate = airDate;
        this.characters = characters;
        this.url = url;
        this.created = created;
        this.fromAPI = fromAPI;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }



    public boolean isFromAPI() {
        return fromAPI;
    }



    public void setFromAPI(boolean fromAPI) {
        this.fromAPI = fromAPI;
    }   

    
}
