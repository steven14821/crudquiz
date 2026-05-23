package com.crudquiz.app.entidades;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "clubes")

public class Club {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String nombre;
	 
	 @OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "entrenador_id", nullable = false)
	 private Entrenador entrenador;
	 
	 @OneToMany(fetch = FetchType.EAGER)
	 @JoinColumn(name= "id_club")
	 private List<Jugador> jugadores;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "asociacion_id")
	 private Asociacion asociacion;
	 
	 @ManyToMany(fetch = FetchType.LAZY)
	 private List<Competicion> competiciones;

	 public Club() {
	 }

	 public Club(String nombre) {
	        this.nombre = nombre;
	    }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public Entrenador getEntrenador() {
		 return entrenador;
	 }

	 public void setEntrenador(Entrenador entrenador) {
		 this.entrenador = entrenador;
	 }

	 public List<Jugador> getJugadores() {
		 return jugadores;
	 }

	 public void setJugadores(List<Jugador> jugadores) {
		 this.jugadores = jugadores;
	 }

	 public Asociacion getAsociacion() {
		 return asociacion;
	 }

	 public void setAsociacion(Asociacion asociacion) {
		 this.asociacion = asociacion;
	 }

	 public List<Competicion> getCompeticiones() {
		 return competiciones;
	 }

	 public void setCompeticiones(List<Competicion> competiciones) {
		 this.competiciones = competiciones;
	 }

	 public String getNombre() {
		 return nombre;
	 }

	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }
	 
}
