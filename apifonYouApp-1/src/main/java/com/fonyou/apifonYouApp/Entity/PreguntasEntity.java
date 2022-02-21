package com.fonyou.apifonYouApp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preguntas")
public class PreguntasEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@Column(name="enunciado",nullable=false,columnDefinition="varchar(40) default 'enunciado'")
private String enunciado;
@Column(name="opc_a",nullable=false,columnDefinition="boolean default false")
private boolean opc_a;
@Column(name="opc_b",nullable=false,columnDefinition="boolean default false")
private boolean opc_b;
@Column(name="opc_c",nullable=false,columnDefinition="boolean default false")
private boolean opc_c;
@Column(name="opc_d",nullable=false,columnDefinition="boolean default false")
private boolean opc_d;
@Column(name="valor_pregunta",nullable=false,columnDefinition="int default 0")
private int valor_pregunta;
public PreguntasEntity() {
	
}
/*Constructor*/
public PreguntasEntity(String enunciado, boolean opc_a, boolean opc_b, boolean opc_c, boolean opc_d,int valor_pregunta) {
	super();
	this.enunciado = enunciado;
	this.opc_a = opc_a;
	this.opc_b = opc_b;
	this.opc_c = opc_c;
	this.opc_d = opc_d;
	this.valor_pregunta = valor_pregunta;
}
/*getters y setters*/
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getEnunciado() {
	return enunciado;
}
public void setEnunciado(String enunciado) {
	this.enunciado = enunciado;
}
public boolean isOpc_a() {
	return opc_a;
}
public void setOpc_a(boolean opc_a) {
	this.opc_a = opc_a;
}
public boolean isOpc_b() {
	return opc_b;
}
public void setOpc_b(boolean opc_b) {
	this.opc_b = opc_b;
}
public boolean isOpc_c() {
	return opc_c;
}
public void setOpc_c(boolean opc_c) {
	this.opc_c = opc_c;
}
public boolean isOpc_d() {
	return opc_d;
}
public void setOpc_d(boolean opc_d) {
	this.opc_d = opc_d;
}
public int getValorPregunta() {
	return valor_pregunta;
}
public void setValorPregunta(int valor_pregunta) {
	this.valor_pregunta = valor_pregunta;
}
/*toString*/
@Override
public String toString() {
	return "Preguntas [id=" + id + ", enunciado=" + enunciado + ", opc_a=" + opc_a + ", opc_b=" + opc_b + ", opc_c="
			+ opc_c + ", opc_d=" + opc_d +", valor pregunta="+valor_pregunta+"]";
}

}
