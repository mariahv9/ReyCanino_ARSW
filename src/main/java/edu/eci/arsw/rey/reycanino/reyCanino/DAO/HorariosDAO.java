package edu.eci.arsw.rey.reycanino.reyCanino.DAO;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Horario;
import edu.eci.arsw.rey.reycanino.reyCanino.model.Reserva;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class HorariosDAO {

    private static final String available = "select * from \"HORARIO\" h2 where h2.identificacion not in (" +
            "select r.horario from \"RESERVA\" r, \"HORARIO\" h, \"TIENDA_CANINA\" tc, \"SERVICIO\" s " +
            "where r.horario = h.identificacion and h.tienda_canina = tc.identificacion and s.identificacion = h.servicio and r.fecha = ?::Date and tc.identificacion = ?::bigint and s.identificacion = ?::bigint)" +
            "and h2.servicio = ?::bigint and h2.tienda_canina = ?::bigint and h2.dia = ?::bigint;";

    public static Collection<Horario> consultAvailable (Reserva reserva) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Collection <Horario> collection = new ArrayList<>();
        String dateAux = "";
        try {
            int count = 1;
            preparedStatement = connection.prepareStatement(available);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reserva.getFecha());
            dateAux += calendar.get(Calendar.YEAR) + "-";
            dateAux += (calendar.get(Calendar.MONTH)+1 > 9)?"":"0";
            dateAux += calendar.get(Calendar.MONTH)+1 + "-";
            dateAux += (calendar.get(Calendar.DAY_OF_MONTH) > 9)?"":"0";
            dateAux += calendar.get(Calendar.DAY_OF_MONTH);
            preparedStatement.setString(count++, dateAux);
            preparedStatement.setString(count++, reserva.getHorario().getPetshop());
            preparedStatement.setString(count++, reserva.getHorario().getService());
            preparedStatement.setString(count++, reserva.getHorario().getService());
            preparedStatement.setString(count++, reserva.getHorario().getPetshop());
            preparedStatement.setString(count++, String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                collection.add(getHorario(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
          connection.close();
        }
        return collection;
    }

    public static Horario getHorario(ResultSet resultSet) throws SQLException {
        Horario horario = new Horario();
        horario.setDay(resultSet.getString("dia"));
        horario.setTimeStart(resultSet.getTime("horaInicio"));
        horario.setTimeFinal(resultSet.getTime("horaFin"));
        horario.setPetshop(resultSet.getString("tienda_canina"));
        horario.setId(resultSet.getString("identificacion"));
        horario.setService(resultSet.getString("servicio"));
        return horario;
    }
}