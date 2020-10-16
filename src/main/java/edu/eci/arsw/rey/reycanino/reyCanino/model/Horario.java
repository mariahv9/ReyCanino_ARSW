package edu.eci.arsw.rey.reycanino.reyCanino.model;

import java.sql.Time;

public class Horario {
	String day, service, petshop, id;
	Time timeStart, timeFinal;

	public Horario() { }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPetshop() {
		return petshop;
	}

	public void setPetshop(String petshop) {
		this.petshop = petshop;
	}

	public Time getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Time getTimeFinal() {
		return timeFinal;
	}

	public void setTimeFinal(Time timeFinal) {
		this.timeFinal = timeFinal;
	}
}