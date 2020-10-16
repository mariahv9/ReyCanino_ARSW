package edu.eci.arsw.rey.reycanino.reyCanino.model;

import java.time.OffsetDateTime;

public class HorarioPrueba {
	String servicio;
	OffsetDateTime ff, fi;
	ReservaPrueba reserva;

	public HorarioPrueba(String servicio, OffsetDateTime ff, OffsetDateTime fi, ReservaPrueba reserva) {
		this.servicio = servicio;
		this.ff = ff;
		this.fi = fi;
		this.reserva = reserva;
	}

	public HorarioPrueba() {
	}

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

	public ReservaPrueba getReserva() {
		return reserva;
	}

	public void setReserva(ReservaPrueba reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "HorarioPrueba [servicio=" + servicio + ", ff=" + ff + ", fi=" + fi + ", reserva=" + reserva + "]";
	}

}
