package com.camacho.app.cursos.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	     generator="CUST_SEQ")
	@SequenceGenerator(sequenceName = "customer_seq",
	     allocationSize = 1, name = "CUST_SEQ")
	private Long id;
	
	@NotEmpty()
	private String nombre;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private Set<Curso> cursos = new HashSet<>();
	
	@Column(name = "numero_cursos")
	private Integer numeroCursos;
	
	
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

	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	public Integer getNumeroCursos() {
		return numeroCursos;
	}

	public void setNumeroCursos(Integer numeroCursos) {
		this.numeroCursos = numeroCursos;
	}

	public Categoria() {
	}
	
	public Categoria(Long id, String nombre, Integer numeroCursos) {
		this.id = id;
		this.nombre = nombre;
		this.numeroCursos = numeroCursos;
	}
		
}
