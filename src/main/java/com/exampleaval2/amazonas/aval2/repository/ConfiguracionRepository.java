package com.exampleaval2.amazonas.aval2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleaval2.amazonas.aval2.models.Configuracion;

public interface ConfiguracionRepository extends JpaRepository<Configuracion, Integer> {
    Configuracion findFirstByOrderByIdDesc();
    
}
