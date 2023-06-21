package com.exampleaval2.amazonas.aval2.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exampleaval2.amazonas.aval2.models.Consulta;

public interface ConsultaRepository  extends JpaRepository<Consulta, Long> {
    @Query("SELECT c FROM Consulta c WHERE c.consultaDateTime <= :threshold")
    List<Consulta> findExpiredConsultas(@Param("threshold") LocalDateTime threshold);

    @Query("SELECT c FROM Consulta c WHERE c.episodeId = :episodeId")
Optional<Consulta> findConsultaByEpisodeId(@Param("episodeId") int episodeId);

Optional<Consulta> findByEpisodeId(int episodeId);

}
