package edu.eci.arsw.rey.reycanino.reyCanino.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rethinkdb.gen.ast.Json;
import com.rethinkdb.net.Util;

public class TiendaCaninaPrueba {
	String direccion, telefono, nombre, id;
	Long identificacion;
	List<HorarioPrueba> horarios;


	public TiendaCaninaPrueba(String direccion, String telefono, String nombre, Long identificacion, String id,
			List<HorarioPrueba> horarios) {
		this.direccion = direccion;
		this.telefono = telefono;
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.id = id;
		this.horarios = horarios;
	}
	public TiendaCaninaPrueba() {
		
	}

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

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public List<HorarioPrueba> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioPrueba> horarios) {
		List<HorarioPrueba> nueva = new ArrayList<HorarioPrueba>();
		for (int i = 0; i < horarios.size(); i++) {
			HorarioPrueba horario = (HorarioPrueba) Util.convertToPojo(horarios.get(i), Optional.of(HorarioPrueba.class));
			nueva.add(horario);
		}
		this.horarios = nueva;
	}

	@Override
	public String toString() {
		return "TiendaCaninaPrueba [direccion=" + direccion + ", telefono=" + telefono + ", nombre=" + nombre
				+ ", identificacion=" + identificacion + ", horarios=" + horarios + "]";
	}

}
