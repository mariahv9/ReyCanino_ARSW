package edu.eci.arsw.rey.reycanino.reyCanino;

public class ReyCaninoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	public static final String CON_RESERVA = "El horario ya tiene reserva";
	public static final String TIEMPO_EXPIRADO = "El tiempo de confirmación ha expirado.";

	public ReyCaninoException(String message) {
		super(message);
	}

}
