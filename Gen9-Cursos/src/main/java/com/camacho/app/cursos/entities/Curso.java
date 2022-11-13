package com.camacho.app.cursos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	     generator="CUST_SEQ5")
	@SequenceGenerator(sequenceName = "customer_seq5",
	     allocationSize = 1, name = "CUST_SEQ5")
	private Long id;
	
	@NotEmpty(message = "Nombre de curso es requerido")
	private String nombre;

	@NotEmpty(message = "Descripcion de curso es requerido")
	private String descripcion;
	
	@NotNull(message = "Duracion es requerida")
	@Column(name = "horas_duracion")
	private Double horasDuracion;
	

	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	
	@OneToOne
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


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Double getHorasDuracion() {
		return horasDuracion;
	}


	public void setHorasDuracion(Double horasDuracion) {
		this.horasDuracion = horasDuracion;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}


	public Temario getTemario() {
		return temario;
	}


	public void setTemario(Temario temario) {
		this.temario = temario;
	}


	public Curso(Long id, @NotEmpty(message = "Nombre de curso es requerido") String nombre,
			@NotEmpty(message = "Descripcion de curso es requerido") String descripcion,
			@NotNull(message = "Duracion es requerida") Double horasDuracion, Categoria categoria,
			Instructor instructor, Temario temario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horasDuracion = horasDuracion;
		this.categoria = categoria;
		this.instructor = instructor;
		this.temario = temario;
	}


	public Curso() {
		super();
	}
	
	
}
