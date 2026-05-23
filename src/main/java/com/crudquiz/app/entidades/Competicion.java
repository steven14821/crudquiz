package com.crudquiz.app.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "competiciones")

public class Competicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Long montoPremio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
	public Competicion() {
	}

	public Competicion(Long id, String nombre, Long montoPremio, LocalDate fechaInicio, LocalDate fechaFin) {
		this.id = id;
		this.nombre = nombre;
		this.montoPremio = montoPremio;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getMontoPremio() {
		return montoPremio;
	}

	public void setMontoPremio(Long montoPremio) {
		this.montoPremio = montoPremio;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
    
}
