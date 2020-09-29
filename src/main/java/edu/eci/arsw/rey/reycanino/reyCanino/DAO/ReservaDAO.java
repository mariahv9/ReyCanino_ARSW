package edu.eci.arsw.rey.reycanino.reyCanino.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;

public class ReservaDAO {

	private static final String insert = "insert into \"RESERVA\"(fecha,comentario, identificador, cliente, horario)"
			+ "values (?::Date,?, nextval('reserva_sequence'), currval('client_sequence'), ?::bigint);";

	public static void insert(Reserva reserva) throws SQLException {
		if (reserva != null) {
			Connection connection = DataBaseConnection.getDataBaseConnection();
			PreparedStatement preparedStatement;
			try {
				String dateAux = "";
				int count = 1;
				Calendar calendar = Calendar.getInstance();

				calendar.setTime(reserva.getFecha());
				dateAux += calendar.get(Calendar.YEAR) + "-";
				dateAux += (calendar.get(Calendar.MONTH) > 9) ? "" : "0";
				dateAux += calendar.get(Calendar.MONTH) + "-";
				dateAux += (calendar.get(Calendar.DAY_OF_MONTH) > 9) ? "" : "0";
				dateAux += calendar.get(Calendar.DAY_OF_MONTH);
				preparedStatement = connection.prepareStatement(insert);
				preparedStatement.setString(count++, dateAux);
				preparedStatement.setString(count++, reserva.getComentario());
				//preparedStatement.setString(count++, reserva.getHorario());

				preparedStatement.executeUpdate();

			} catch (SQLException throwables) {
				throwables.printStackTrace();
			} finally {
				connection.close();
			}
		}
	}

}
