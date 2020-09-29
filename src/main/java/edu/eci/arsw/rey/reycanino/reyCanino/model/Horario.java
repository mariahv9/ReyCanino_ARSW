package edu.eci.arsw.rey.reycanino.reyCanino.model;

import java.sql.Time;

public class Horario {
    Integer day, service, petshop;
    Time timeStart, timeFinal;
    public Horario(){}

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getPetshop() {
        return petshop;
    }

    public void setPetshop(Integer petshop) {
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
