package com.camacho.app.cursos.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "temarios")
public class Temario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	     generator="CUST_SEQ4")
	@SequenceGenerator(sequenceName = "customer_seq4",
	     allocationSize = 1, name = "CUST_SEQ4")
	private Long id;
	
	
	private Integer numTemas;
	
	@NotNull(message = "Numero de actividades es requerido" )
	private Integer numActividades;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "temario", cascade = CascadeType.ALL)
	private Set<Tema> temas = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumTemas() {
		return numTemas;
	}

	public void setNumTemas(Integer numTemas) {
		this.numTemas = numTemas;
	}

	public Integer getNumActividades() {
		return numActividades;
	}

	public void setNumActividades(Integer numActividades) {
		this.numActividades = numActividades;
	}

	public Set<Tema> getTemas() {
		return temas;
	}

	public void setTemas(Set<Tema> temas) {
		this.temas = temas;
	}

	public Temario(Long id, @NotNull(message = "Numero de temas es requerido") Integer numTemas,
			@NotNull(message = "Numero de actividades es requerido") Integer numActividades, Set<Tema> temas) {
		super();
		this.id = id;
		this.numTemas = numTemas;
		this.numActividades = numActividades;
		this.temas = temas;
	}

	public Temario() {
		super();
	}

	
}
