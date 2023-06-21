package com.exampleaval2.amazonas.aval2.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "episodes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    @Id
    private int id;

    private String name;
    @JsonProperty("air_date")
    private String airDate;
    private String episode;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created;
    private String source;
    @JsonIgnore
    private List<String> characters;
    private String url;
    @Column(name = "consultation_count")
    private int consultationCount;

    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EpisodeHistory> episodeHistoryList = new ArrayList<>();
    
    public Episode() {
    }


    public Episode(int id, String name, String airDate, String episode, LocalDateTime created, String source,
            List<String> characters, String url, int consultationCount, List<EpisodeHistory> episodeHistoryList) {
        this.id = id;
        this.name = name;
        this.airDate = airDate;
        this.episode = episode;
        this.created = created;
        this.source = source;
        this.characters = characters;
        this.url = url;
        this.consultationCount = consultationCount;
        this.episodeHistoryList = episodeHistoryList;
    }

    public void incrementConsultationCount() {
        consultationCount++;
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
    public String getAirDate() {
        return airDate;
    }
    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }
    public String getEpisode() {
        return episode;
    }
    public void setEpisode(String episode) {
        this.episode = episode;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public int getConsultationCount() {
        return consultationCount;
    }

    public void setConsultationCount(int consultationCount) {
        this.consultationCount = consultationCount;
    }

    public List<EpisodeHistory> getEpisodeHistoryList() {
        return episodeHistoryList;
    }


    public void setEpisodeHistoryList(List<EpisodeHistory> episodeHistoryList) {
        this.episodeHistoryList = episodeHistoryList;
    }

}
