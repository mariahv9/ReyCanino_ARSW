package edu.eci.arsw.rey.reycanino.reyCanino.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;

public class ReservaDAO {

	private static final String insert = "insert into \"RESERVA\"(fecha,comentario, identificador, cliente, horario)"
			+ "values (?::Date,?, ?::integer, ?::bigint, ?::bigint);";

	private static final String SEQUENCE = "select nextval('reserva_sequence')";

	public static void insert(Reserva reserva) throws SQLException {
		if (reserva != null) {
			Connection connection = null;
			PreparedStatement preparedStatement;
			PreparedStatement sequence;
			ResultSet resultSet;
			try {
				String dateAux = "";
				int count = 1;
				String sequenceCont = "";
				sequence = connection.prepareStatement(SEQUENCE);
				resultSet = sequence.executeQuery();
				if (resultSet.next())
					sequenceCont = resultSet.getString(1);
				else
					throw new Exception("Without Secuence");

				reserva.setIdentificador(sequenceCont);

				Calendar calendar = Calendar.getInstance();

				calendar.setTime(reserva.getFecha());
				dateAux += calendar.get(Calendar.YEAR) + "-";
				dateAux += (calendar.get(Calendar.MONTH) + 1 > 9) ? "" : "0";
				dateAux += calendar.get(Calendar.MONTH) + 1 + "-";
				dateAux += (calendar.get(Calendar.DAY_OF_MONTH) > 9) ? "" : "0";
				dateAux += calendar.get(Calendar.DAY_OF_MONTH);

				preparedStatement = connection.prepareStatement(insert);

				preparedStatement.setString(count++, dateAux);
				preparedStatement.setString(count++, reserva.getComentario());
				preparedStatement.setString(count++, reserva.getIdentificador());
				preparedStatement.setString(count++, reserva.getCliente().getId());
				preparedStatement.setString(count++, reserva.getHorario().getId());

				preparedStatement.executeUpdate();

			} catch (Exception throwables) {
				throwables.printStackTrace();
			} finally {
				connection.close();
			}
		}
	}
}