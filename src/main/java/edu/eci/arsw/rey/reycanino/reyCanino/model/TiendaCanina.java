package edu.eci.arsw.rey.reycanino.reyCanino.model;

public class TiendaCanina {
	String direccion, telefono, nombre, id;
	String identificacion;

	public TiendaCanina(String direccion, String telefono, String nombre, Long identificacion, String id) {
		this.direccion = direccion;
		this.telefono = telefono;
		this.nombre = nombre;
		this.identificacion = String.valueOf(identificacion);
		this.id = id;
	}

	public TiendaCanina() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = String.valueOf(identificacion);
	}

	@Override
	public String toString() {
		return "TiendaCanina [direccion=" + direccion + ", telefono=" + telefono + ", nombre=" + nombre + ", id=" + id
				+ ", identificacion=" + identificacion + "]";
	}
}