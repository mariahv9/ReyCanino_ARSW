package edu.eci.arsw.rey.reycanino.reyCanino.model;

import java.time.OffsetDateTime;
import java.util.Date;

public class Horario {
	String servicio, id, tiendaCanina;
	OffsetDateTime ff, fi;
	Date fechaConsulta;
	Reserva reserva;

	public Horario(String servicio, String id, OffsetDateTime ff, OffsetDateTime fi, Reserva reserva, String tiendaCanina) {
		this.servicio = servicio;
		this.id = id;
		this.ff = ff;
		this.fi = fi;
		this.reserva = reserva;
		this.tiendaCanina = tiendaCanina;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTiendaCanina() {
		return tiendaCanina;
	}

	public void setTiendaCanina(String tiendaCanina) {
		this.tiendaCanina = tiendaCanina;
	}

	public Horario() {}

	public OffsetDateTime getFf() {
		return ff;
	}

	public void setFf(OffsetDateTime ff) {
		this.ff = ff;
	}

	public OffsetDateTime getFi() {
		return fi;
	}

	public void setFi(OffsetDateTime fi) {
		this.fi = fi;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "Horario [servicio=" + servicio + ", id=" + id + ", ff=" + ff + ", fi=" + fi + ", fechaConsulta="
				+ fechaConsulta + ", reserva=" + reserva + ", tiendaCanina=" + tiendaCanina + "]";
	}
}