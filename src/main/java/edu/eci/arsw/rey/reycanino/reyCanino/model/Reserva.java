package edu.eci.arsw.rey.reycanino.reyCanino.model;

import java.time.OffsetDateTime;

public class Reserva {
	String cliente, correo, mascota, comentario, telefono, raza, id, idHorario;
	OffsetDateTime fechaLimite;

	public Reserva(String cliente, String correo, String mascota, String comentario, String telefono, String raza,
			String id, String idHorario, OffsetDateTime fechaLimite) {
		this.cliente = cliente;
		this.correo = correo;
		this.mascota = mascota;
		this.comentario = comentario;
		this.telefono = telefono;
		this.raza = raza;
		this.id = id;
		this.idHorario = idHorario;
		this.fechaLimite = fechaLimite;
	}

	public Reserva() {

	}

	public String getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(String idHorario) {
		this.idHorario = idHorario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCorreo() {
		return correo;
	}

	public OffsetDateTime getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(OffsetDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getMascota() {
		return mascota;
	}

	public void setMascota(String mascota) {
		this.mascota = mascota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Reserva [cliente=" + cliente + ", correo=" + correo + ", mascota=" + mascota + ", comentario="
				+ comentario + ", telefono=" + telefono + ", raza=" + raza + ", id=" + id + ", idHorario=" + idHorario
				+ ", fechaLimite=" + fechaLimite + "]";
	}

}