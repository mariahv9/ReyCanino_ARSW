package edu.eci.arsw.rey.reycanino.reyCanino.model;

public class Reserva {
	String cliente, correo, mascota, comentario, telefono, raza;

	public Reserva(String cliente, String correo, String mascota, String comentario, String telefono, String raza) {
		super();
		this.cliente = cliente;
		this.correo = correo;
		this.mascota = mascota;
		this.comentario = comentario;
		this.telefono = telefono;
		this.raza = raza;
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
				+ comentario + ", telefono=" + telefono + "]";
	}
}