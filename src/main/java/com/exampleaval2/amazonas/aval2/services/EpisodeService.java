package com.exampleaval2.amazonas.aval2.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.exampleaval2.amazonas.aval2.models.Auditoria;
import com.exampleaval2.amazonas.aval2.models.Consulta;
import com.exampleaval2.amazonas.aval2.models.Datos;
import com.exampleaval2.amazonas.aval2.models.Detalle;
import com.exampleaval2.amazonas.aval2.models.Episode;
import com.exampleaval2.amazonas.aval2.models.EpisodeDetailsDTO;
import com.exampleaval2.amazonas.aval2.models.Informacion;
import com.exampleaval2.amazonas.aval2.repository.AuditoriaRepository;
import com.exampleaval2.amazonas.aval2.repository.ConfiguracionRepository;
import com.exampleaval2.amazonas.aval2.repository.ConsultaRepository;
import com.exampleaval2.amazonas.aval2.repository.DatosRepository;
import com.exampleaval2.amazonas.aval2.repository.DetalleRepository;
import com.exampleaval2.amazonas.aval2.repository.EpisodeRepository;
import com.exampleaval2.amazonas.aval2.repository.InformacionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class EpisodeService {
    private final DatosRepository datosRepository;
    private final InformacionRepository informacionRepository;
    private final DetalleRepository detalleRepository;
    private final ConsultaRepository consultaRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;
    private final ConfiguracionRepository configuracionRepository;
    private final WebClient webClient;

    @Autowired
    public EpisodeService(DatosRepository datosRepository, InformacionRepository informacionRepository,
            DetalleRepository detalleRepository, ConsultaRepository consultaRepository,
            ConfiguracionRepository configuracionRepository, WebClient.Builder webClientBuilder) {
        this.datosRepository = datosRepository;
        this.informacionRepository = informacionRepository;
        this.detalleRepository = detalleRepository;
        this.consultaRepository = consultaRepository;
        this.configuracionRepository = configuracionRepository;
        this.webClient = webClientBuilder.build();
    }

    public EpisodeDetailsDTO getEpisodeDetails(int id) {
        Datos datos = datosRepository.findById(id).orElse(null);
        Informacion informacion = informacionRepository.findById(id).orElse(null);
        Detalle detalle = detalleRepository.findById(id).orElse(null);

        if (datos != null && informacion != null && detalle != null) {
            LocalDateTime expirationDate = datos.getExpirationDate();
            if (expirationDate != null && expirationDate.isBefore(LocalDateTime.now())) {
                // El episodio ha expirado, eliminar registros
                datosRepository.delete(datos);
                informacionRepository.delete(informacion);
                detalleRepository.delete(detalle);
                datos = null; // Establecer a null para indicar que no se encontraron registros válidos en la
                              // base de datos
            } else {
                // El episodio aún es válido, incrementar contador de consultas
                Consulta consulta = consultaRepository.findByEpisodeId(id).orElse(null);
                if (consulta == null) {
                    consulta = new Consulta();
                    consulta.setEpisodeId(id);
                    consulta.setConsultCount(1);
                    consulta.setFromAPI(true);
                    consulta.setConsultaDateTime(LocalDateTime.now());
                } else {
                    consulta.setConsultCount(consulta.getConsultCount() + 1);
                    consulta.setFromAPI(false); // La consulta se realiza desde la base de datos
                    // consulta.setFromAPI(!consulta.isFromAPI()); // Invertir el valor de "fromAPI"
                    consulta.setConsultaDateTime(LocalDateTime.now());
                }
                consultaRepository.save(consulta);

                EpisodeDetailsDTO episodeDetails = new EpisodeDetailsDTO();
                episodeDetails.setId(datos.getId());
                episodeDetails.setName(datos.getName());
                episodeDetails.setEpisode(datos.getEpisode());
                episodeDetails.setAirDate(informacion.getAirDate());
                episodeDetails.setCharacters(informacion.getCharacters());
                episodeDetails.setUrl(detalle.getUrl());
                episodeDetails.setCreated(detalle.getCreated());
                episodeDetails.setFromAPI(consulta.isFromAPI());

                // Guardar la información de consulta en la tabla "Auditoria"
                Auditoria auditoria = new Auditoria();
                auditoria.setEpisodeId(id);
                auditoria.setConsultaDateTime(LocalDateTime.now());
                auditoria.setExpirationDateTime(expirationDate);
                auditoria.setConsultCount(consulta.getConsultCount());
                auditoria.setFromAPI(consulta.isFromAPI()); // Establecer el valor de "fromAPI" según la fuente de la consulta
                auditoriaRepository.save(auditoria);

                return episodeDetails;
            }
        }

        // Realiza la llamada a la API externa y guarda los datos en las tablas
        EpisodeDetailsDTO episode = fetchEpisodeFromExternalAPI(id);
        if (episode != null) {
            Datos newDatos = new Datos();
            newDatos.setId(episode.getId());
            newDatos.setName(episode.getName());
            newDatos.setEpisode(episode.getEpisode());
            // Establecer la fecha de expiración en 5 minutos después de la fecha actual
            int tiempoExpiracion = configuracionRepository.findFirstByOrderByIdDesc().getExpirationTimeMinutes();
            LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(tiempoExpiracion);
            newDatos.setExpirationDate(expirationDate);
            datosRepository.save(newDatos);

            Informacion newInformacion = new Informacion();
            newInformacion.setEpisodeId(episode.getId());
            newInformacion.setAirDate(episode.getAirDate());
            newInformacion.setCharacters(episode.getCharacters());
            informacionRepository.save(newInformacion);

            Detalle newDetalle = new Detalle();
            newDetalle.setEpisodeId(episode.getId());
            newDetalle.setUrl(episode.getUrl());
            newDetalle.setCreated(episode.getCreated());
            detalleRepository.save(newDetalle);

            Consulta consulta = consultaRepository.findByEpisodeId(episode.getId()).orElse(null);
            if (consulta != null) {
                // El episodio ya existe en la tabla "Consulta", actualizar el registro
                consulta.setConsultCount(consulta.getConsultCount() + 1);
                consulta.setFromAPI(true);
                consulta.setConsultaDateTime(LocalDateTime.now());
                consultaRepository.save(consulta);
            } else {
                // El episodio no existe en la tabla "Consulta", crear un nuevo registro
                consulta = new Consulta();
                consulta.setEpisodeId(episode.getId());
                consulta.setConsultCount(1);
                consulta.setFromAPI(true);
                consulta.setConsultaDateTime(LocalDateTime.now());
                consultaRepository.save(consulta);
            }

            episode.setFromAPI(consulta.isFromAPI());

            // Guardar la información de consulta en la tabla "Auditoria"
            Auditoria auditoria = new Auditoria();
            auditoria.setEpisodeId(episode.getId());
            auditoria.setConsultCount(consulta.getConsultCount());
            auditoria.setConsultaDateTime(LocalDateTime.now());
            auditoria.setExpirationDateTime(expirationDate);
            auditoria.setFromAPI(consulta.isFromAPI());
            auditoriaRepository.save(auditoria);

            return episode;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Episodio no encontrado");
        }
    }

    private static final DateTimeFormatter API_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    private EpisodeDetailsDTO fetchEpisodeFromExternalAPI(int id) {
        String apiUrl = "https://rickandmortyapi.com/api/episode/" + id;

        try {
            ResponseEntity<String> responseEntity = webClient.get()
                    .uri(apiUrl)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .toEntity(String.class)
                    .block();

            if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();

                // Procesar el cuerpo de respuesta y construir el objeto EpisodeDetailsDTO
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseJson = objectMapper.readTree(responseBody);

                EpisodeDetailsDTO episodeDetails = new EpisodeDetailsDTO();
                episodeDetails.setId(id);
                episodeDetails.setName(responseJson.get("name").asText());
                episodeDetails.setEpisode(responseJson.get("episode").asText());
                episodeDetails.setAirDate(responseJson.get("air_date").asText());
                episodeDetails.setCharacters(extractCharacterUrls(responseJson.get("characters")));
                episodeDetails.setUrl(responseJson.get("url").asText());

                String createdDateString = responseJson.get("created").asText();
                LocalDateTime createdDateTime = LocalDateTime.parse(createdDateString, API_DATE_TIME_FORMATTER);
                episodeDetails.setCreated(createdDateTime);

                return episodeDetails;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<String> extractCharacterUrls(JsonNode charactersNode) {
        List<String> characterUrls = new ArrayList<>();

        if (charactersNode != null && charactersNode.isArray()) {
            for (JsonNode characterNode : charactersNode) {
                characterUrls.add(characterNode.asText());
            }
        }

        return characterUrls;
    }
}
