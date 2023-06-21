package com.exampleaval2.amazonas.aval2.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exampleaval2.amazonas.aval2.models.Episode;

import jakarta.transaction.Transactional;

public interface  EpisodeRepository extends JpaRepository<Episode, Integer>  {
    
   
    // @Query(value = "SELECT * FROM episode WHERE episode_number = :number", nativeQuery = true)
    Episode findById(int id);
    void deleteByCreatedBefore(LocalDateTime dateTime);
    @Query("SELECT e FROM Episode e WHERE e.created < :threshold")
    List<Episode> findEpisodesToDelete(@Param("threshold") LocalDateTime threshold);
    // void deleteByAirDateBefore(LocalDateTime dateTime);

}
