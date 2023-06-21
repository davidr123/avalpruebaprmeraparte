package com.exampleaval2.amazonas.aval2.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Detalle {
    
    @Id
    private int episodeId;
    private String url;
    private LocalDateTime created;
    
    public Detalle() {
    }
    public Detalle(int episodeId, String url, LocalDateTime created) {
        this.episodeId = episodeId;
        this.url = url;
        this.created = created;
    }
    public int getEpisodeId() {
        return episodeId;
    }
    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
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

    
}
