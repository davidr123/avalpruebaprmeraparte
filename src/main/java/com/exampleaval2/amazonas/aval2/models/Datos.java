package com.exampleaval2.amazonas.aval2.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Datos {
    @Id
    private int id;
    private String name;
    private String episode;
    private LocalDateTime expirationDate; // Nuevo campo

    public Datos() {
    }




    public Datos(int id, String name, String episode, LocalDateTime expirationDate) {
        this.id = id;
        this.name = name;
        this.episode = episode;
        this.expirationDate = expirationDate;
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




    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }




    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }   

    
}
