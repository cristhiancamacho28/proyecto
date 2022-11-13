package com.camacho.app.cursos.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "instructores")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	     generator="CUST_SEQ2")
	@SequenceGenerator(sequenceName = "customer_seq2",
	     allocationSize = 1, name = "CUST_SEQ2")
	private Long id;
	
	@NotEmpty(message = "Nombre es requerido")
	private String nombre;
	
	@NotEmpty(message = "Ap Paterno es requerido")
	@Column(name = "ap_paterno")
	private String apPaterno;
	
	@NotEmpty(message = "Ap Materno es requerido")
	@Column(name = "ap_Materno")
	private String apMaterno;
	
	@NotEmpty(message = "Correo es requerido")
	@Email(message = "Favor de ingresar un correo valido")
	private String email;
	
	@NotEmpty(message = "Telefono es requerido")
	private String telefono;
	
	@Column(name = "horas")
	private Double horas;
	
	@NotEmpty
	@Pattern(regexp="^[a-zA-Z]{3}-[0-9]{4}",
	      message="El codigo debe cumplir con el formato XXX-0000")
	@Column(name = "codigo_instructor")
	private String codigoInstructor;
	
	@JsonBackReference
	@OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
	private Set<Curso> cursos = new HashSet<>();
	
	
	

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

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Double getHoras() {
		return horas;
	}

	public void setHoras(Double horas) {
		this.horas = horas;
	}

	public String getCodigoInstructor() {
		return codigoInstructor;
	}

	public void setCodigoInstructor(String codigoInstructor) {
		this.codigoInstructor = codigoInstructor;
	}

	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	public Instructor(Long id, @NotEmpty(message = "Nombre es requerido") String nombre,
			@NotEmpty(message = "Ap Paterno es requerido") String apPaterno,
			@NotEmpty(message = "Ap Materno es requerido") String apMaterno,
			@NotEmpty(message = "Correo es requerido") @Email(message = "Favor de ingresar un correo valido") String email,
			@NotEmpty(message = "Telefono es requerido") String telefono, Double horas,
			@NotEmpty @Pattern(regexp = "^[a-zA-Z]{3}-[0-9]{4}", message = "El codigo debe cumplir con el formato XXX-0000") String codigoInstructor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.email = email;
		this.telefono = telefono;
		this.horas = horas;
		this.codigoInstructor = codigoInstructor;
	}

	public Instructor() {
		super();
	}
	
			

}
