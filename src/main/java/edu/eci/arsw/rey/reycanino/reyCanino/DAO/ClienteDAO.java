package edu.eci.arsw.rey.reycanino.reyCanino.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Cliente;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;

public class ClienteDAO {
	
	private static final String INSERT ="insert into \"CLIENTE\"(nombre_mascota, raza_mascota, telefono, correo, id)" + 
			"values(?, ?, ?, ?, ?::integer);";
	
	private static final String SEQUENCE = "select nextval('client_sequence')";
	
	public static void insert(Cliente cliente) throws SQLException {
		if (cliente != null) {
			Connection connection = DataBaseConnection.getDataBaseConnection();
			PreparedStatement preparedStatement;
			PreparedStatement sequence;
			ResultSet resultSet;
			try {

				int count = 1;
				String sequenceCont = "";
				
				sequence     = connection.prepareStatement(SEQUENCE);
				resultSet =  sequence.executeQuery();
				if(resultSet.next())
					sequenceCont = resultSet.getString(1);
				else
					throw new Exception("Without Secuence");
				
				cliente.setId(sequenceCont);
				
				preparedStatement = connection.prepareStatement(INSERT);
				preparedStatement.setString(count++, cliente.getNombreMascota());
				preparedStatement.setString(count++, cliente.getRazaMascota());
				preparedStatement.setString(count++, cliente.getTelefono());
				preparedStatement.setString(count++, cliente.getCorreo());
				preparedStatement.setString(count++, sequenceCont);

				preparedStatement.executeUpdate();

			} catch (Exception throwables) {
				throwables.printStackTrace();
			} finally {
				connection.close();
			}
		}
	}

}
