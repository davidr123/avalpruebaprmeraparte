package com.exampleaval2.amazonas.aval2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episode_history")
public class EpisodeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;

    @Column(name = "consultation_count")
    private int consultationCount;

    public EpisodeHistory() {
    }

    public EpisodeHistory(Long id, Episode episode, int consultationCount) {
        this.id = id;
        this.episode = episode;
        this.consultationCount = consultationCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public int getConsultationCount() {
        return consultationCount;
    }

    public void setConsultationCount(int consultationCount) {
        this.consultationCount = consultationCount;
    }

}
