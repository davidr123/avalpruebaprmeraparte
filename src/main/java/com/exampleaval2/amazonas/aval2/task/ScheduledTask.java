package com.exampleaval2.amazonas.aval2.task;

// import java.time.LocalDateTime;
// import java.util.List;
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;

// import com.exampleaval2.amazonas.aval2.models.Consulta;
// import com.exampleaval2.amazonas.aval2.repository.ConsultaRepository;
// import com.exampleaval2.amazonas.aval2.services.EpisodeService;
// import com.exampleaval2.amazonas.aval2.models.EpisodeDetailsDTO;
// import jakarta.transaction.Transactional;

// @Component
// @EnableScheduling
// public class ScheduledTask {
//     private final ConsultaRepository consultaRepository;
//     private final EpisodeService episodeService;

//     public ScheduledTask(ConsultaRepository consultaRepository, EpisodeService episodeService) {
//         this.consultaRepository = consultaRepository;
//         this.episodeService = episodeService;
//     }

//     @Transactional
//     @Scheduled(fixedRate = 300000) // 5 minutes = 5 * 60 * 1000 milliseconds
//     public void deleteExpiredEpisodesAndFetchAgain() {
//         LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
//         List<Consulta> expiredConsultas = consultaRepository.findExpiredConsultas(threshold);

//         for (Consulta consulta : expiredConsultas) {
//             // Eliminar consulta
//             consultaRepository.delete(consulta);

//             // Volver a consultar y guardar los detalles del episodio en la base de datos
//             EpisodeDetailsDTO episodeDetails = episodeService.getEpisodeDetails(consulta.getEpisodeId());
//         }
//     }
// }
