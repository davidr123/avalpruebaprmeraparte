package com.exampleaval2.amazonas.aval2.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int episodeId;
    private LocalDateTime consultaDateTime;
    private int consultCount;
    private boolean isFromAPI;
    
    public Consulta() {
    }



    public Consulta(Long id, int episodeId, LocalDateTime consultaDateTime, int consultCount, boolean isFromAPI) {
        this.id = id;
        this.episodeId = episodeId;
        this.consultaDateTime = consultaDateTime;
        this.consultCount = consultCount;
        this.isFromAPI = isFromAPI;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public LocalDateTime getConsultaDateTime() {
        return consultaDateTime;
    }

    public void setConsultaDateTime(LocalDateTime consultaDateTime) {
        this.consultaDateTime = consultaDateTime;
    }



    public int getConsultCount() {
        return consultCount;
    }



    public void setConsultCount(int consultCount) {
        this.consultCount = consultCount;
    }



    public boolean isFromAPI() {
        return isFromAPI;
    }



    public void setFromAPI(boolean isFromAPI) {
        this.isFromAPI = isFromAPI;
    }

    
}
