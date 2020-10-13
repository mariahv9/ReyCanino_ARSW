package edu.eci.arsw.rey.reycanino.reyCanino.model;

import java.util.Date;

public class Reserva {

	Date fecha;
	String comentario, identificador;
	Horario horario;
	Cliente cliente;

	public Reserva() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
}