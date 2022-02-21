package com.fonyou.apifonYouApp.Entity;

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
@Table(name="preguntas")
public class ExamenEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@Column(name="puntaje",nullable=false,columnDefinition="int default 0")
private int puntaje;
@OneToMany(fetch=FetchType.EAGER)
@JoinColumn(name="id_pregunta",nullable=false)
private PreguntasEntity [] preguntas;
public ExamenEntity() {
	
}
/*Constructor*/	
public ExamenEntity(int puntaje, PreguntasEntity [] preguntas) {
	super();
	this.puntaje = puntaje;
	this.preguntas = preguntas;
}
/*getters, setters y toString*/
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public int getPuntaje() {
	return puntaje;
}
public void setPuntaje(int puntaje) {
	this.puntaje = puntaje;
}
public PreguntasEntity [] getPreguntas() {
	return preguntas;
}
public void setPreguntas(PreguntasEntity [] preguntas) {
	this.preguntas = preguntas;
}
/*toString*/
@Override
public String toString() {
	return "Examen [id=" + id + ", puntaje=" + puntaje + ", preguntas=" + preguntas + "]";
}
public void obtenerPuntaje() {
int p=0;
for(PreguntasEntity e: preguntas) {
	if(e.isOpc_a() || e.isOpc_b() || e.isOpc_c() || e.isOpc_d()) {
		p+=e.getValorPregunta();
	}
}

this.setPuntaje(p);
}
}
