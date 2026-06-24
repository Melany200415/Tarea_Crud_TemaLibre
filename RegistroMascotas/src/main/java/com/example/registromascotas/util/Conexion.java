package com.example.registromascotas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
    private static Conexion instancia;
    private Connection connection;
    private Conexion() {
    }
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/veterinaria_db",
                    "root",
                    "1234"
            );

        }
        return connection;
    }
    public void probarConexion() {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}