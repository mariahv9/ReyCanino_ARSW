package edu.eci.arsw.rey.reycanino.reyCanino.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class makes the connection with the data base and obtain datas
 * @author Maria Fernanda Hernandez Vargas
 */
public class DataBaseConnection {
    private static String url = "jdbc:postgresql://ec2-54-235-192-146.compute-1.amazonaws.com:5432/d98eeu5e11qd9q";
    private static String user = "vqutduqigizrpu";
    private static String passwd = "4b13f1b319aac26027d8839941271d1bf8585eaad56a9ed7c974752699b1cfcf";
    private static Connection connect = null;

    /**
     * Method that does the connection with data base
     */
    public DataBaseConnection (){
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
