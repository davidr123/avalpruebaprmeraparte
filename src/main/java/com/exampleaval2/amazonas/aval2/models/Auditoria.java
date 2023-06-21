package com.exampleaval2.amazonas.aval2.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int episodeId;
    private LocalDateTime consultaDateTime;
    private LocalDateTime expirationDateTime;
    private int consultCount;
    @Column(name = "FromAPI")
    private boolean fromAPI;
    
    public Auditoria() {
    }

    


    public Auditoria(Long id, int episodeId, LocalDateTime consultaDateTime, LocalDateTime expirationDateTime,
            int consultCount, boolean fromAPI) {
        this.id = id;
        this.episodeId = episodeId;
        this.consultaDateTime = consultaDateTime;
        this.expirationDateTime = expirationDateTime;
        this.consultCount = consultCount;
        this.fromAPI = fromAPI;
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

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public int getConsultCount() {
        return consultCount;
    }

    public void setConsultCount(int consultCount) {
        this.consultCount = consultCount;
    }




    public boolean isFromAPI() {
        return fromAPI;
    }




    public void setFromAPI(boolean fromAPI) {
        this.fromAPI = fromAPI;
    }

    
    
    
}
