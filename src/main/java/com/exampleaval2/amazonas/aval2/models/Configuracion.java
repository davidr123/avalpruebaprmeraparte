package com.exampleaval2.amazonas.aval2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "configuracion")
public class Configuracion {

    @Id
    private int id;

    @Column(name = "expiration_time_minutes")
    private int expirationTimeMinutes;

    public Configuracion() {
    }

    public Configuracion(int id, int expirationTimeMinutes) {
        this.id = id;
        this.expirationTimeMinutes = expirationTimeMinutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpirationTimeMinutes() {
        return expirationTimeMinutes;
    }

    public void setExpirationTimeMinutes(int expirationTimeMinutes) {
        this.expirationTimeMinutes = expirationTimeMinutes;
    }


}
