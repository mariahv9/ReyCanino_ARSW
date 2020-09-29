package edu.eci.arsw.rey.reycanino.reyCanino.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.eci.arsw.rey.reycanino.reyCanino.model.Cliente;
import edu.eci.arsw.rey.reycanino.reyCanino.persistence.DataBaseConnection;

public class ClienteDAO {
	
	private static final String insert ="insert into \"CLIENTE\"(nombre_mascota, raza_mascota, telefono, correo, id)" + 
			"values(?, ?, ?, ?, nextval('client_sequence'));";
	
	public static void insert(Cliente cliente) throws SQLException {
		if (cliente != null) {
			Connection connection = DataBaseConnection.getDataBaseConnection();
			PreparedStatement preparedStatement;
			try {

				int count = 1;
				
				preparedStatement = connection.prepareStatement(insert);
				preparedStatement.setString(count++, cliente.getNombreMascota());
				preparedStatement.setString(count++, cliente.getRazaMascota());
				preparedStatement.setString(count++, cliente.getTelefono());
				preparedStatement.setString(count++, cliente.getCorreo());

				preparedStatement.executeUpdate();

			} catch (SQLException throwables) {
				throwables.printStackTrace();
			} finally {
				connection.close();
			}
		}
	}

}
