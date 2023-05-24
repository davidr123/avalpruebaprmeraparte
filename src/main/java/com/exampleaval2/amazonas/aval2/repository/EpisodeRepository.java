package com.exampleaval2.amazonas.aval2.repository;


import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exampleaval2.amazonas.aval2.models.Episode;

import jakarta.transaction.Transactional;

public interface  EpisodeRepository extends JpaRepository<Episode, Integer>  {
    
   
    // @Query(value = "SELECT * FROM episode WHERE episode_number = :number", nativeQuery = true)
    Episode findById(int id);
    void deleteByCreatedBefore(LocalDateTime dateTime);
    // void deleteByAirDateBefore(LocalDateTime dateTime);

}
