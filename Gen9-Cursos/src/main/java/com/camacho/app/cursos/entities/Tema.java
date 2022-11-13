package com.camacho.app.cursos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "temas")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	     generator="CUST_SEQ3")
	@SequenceGenerator(sequenceName = "customer_seq3",
	     allocationSize = 1, name = "CUST_SEQ3")
	private Long id;
	
	@NotEmpty(message = "Nombre es requerido")
	private String nombre;
	
	@NotNull(message = "Duracion es requerido")
	private Double duracion;
	
	
	@ManyToOne
	@JoinColumn(name="temario_id")
	private Temario temario;


	
	
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


	public Double getDuracion() {
		return duracion;
	}


	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}


	public Temario getTemario() {
		return temario;
	}


	public void setTemario(Temario temario) {
		this.temario = temario;
	}


	public Tema(Long id, @NotEmpty(message = "Nombre es requerido") String nombre,
			@NotNull(message = "Duracion es requerido") Double duracion, Temario temario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.temario = temario;
	}


	public Tema() {
		super();
	}
	
	

}
