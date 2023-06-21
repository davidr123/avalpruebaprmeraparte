package com.exampleaval2.amazonas.aval2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleaval2.amazonas.aval2.models.EpisodeHistory;

public interface EpisodeHistoryRepository extends JpaRepository<EpisodeHistory, Long> {
    List<EpisodeHistory> findAll();
}
