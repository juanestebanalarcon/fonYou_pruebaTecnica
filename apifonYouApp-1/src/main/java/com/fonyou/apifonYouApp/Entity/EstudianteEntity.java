package com.fonyou.apifonYouApp.Entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="estudiante")
public class EstudianteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="nombre",nullable=false,columnDefinition="varchar(30) default ' '")
	private String nombre;
	@Column(name="edad",nullable=false,columnDefinition="int default 0")
	private int edad;
	@Column(name="ciudad",nullable=false,columnDefinition="varchar(30) default ' '")
	private String ciudad;
	@Column(name="zona_horaria",nullable=true,columnDefinition="timestamp")
	private Timestamp zona_horaria;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_examen", nullable = false)
	private ExamenEntity examen;
	public EstudianteEntity() {
	}
	public EstudianteEntity(String nombre, int edad, String ciudad, Timestamp zona_horaria,
			ExamenEntity examen) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.ciudad = ciudad;
		this.zona_horaria = zona_horaria;
		this.examen = examen;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Timestamp getZona_horaria() {
		return zona_horaria;
	}
	public void setZona_horaria(Timestamp zona_horaria) {
		this.zona_horaria = zona_horaria;
	}
	public ExamenEntity getExamen() {
		return examen;
	}
	public void setExamen(ExamenEntity examen) {
		this.examen = examen;
	}
	@Override
	public String toString() {
		return "EstudianteEntity [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", ciudad=" + ciudad
				+ ", zona_horaria=" + zona_horaria + ", examen=" + examen + "]";
	}
	
}
	