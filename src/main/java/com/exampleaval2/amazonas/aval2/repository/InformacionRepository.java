package com.exampleaval2.amazonas.aval2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleaval2.amazonas.aval2.models.Informacion;

public interface  InformacionRepository extends JpaRepository<Informacion, Integer> {
    void deleteByEpisodeId(int episodeId);
}
