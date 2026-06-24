package com.example.registromascotas.controller;

import com.example.registromascotas.util.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    // Cuando el usario pulse ingresar ejecutara el btnIngresar
    @FXML
    private void btnIngresar() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();
        if (usuario.equals("admin") && contrasena.equals("1234")) {

            mostrarInformacion(
                    "Login Correcto",
                    "Bienvenido",
                    "Acceso concedido"
            );

        } else {

            mostrarAlerta(
                    "Error",
                    "Credenciales incorrectas",
                    "Usuario o contraseña incorrectos"
            );
        }
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(
                    "Error",
                    "Campos vacíos",
                    "Debe ingresar usuario y contraseña"
            );
            return;

        }
        try {
            Conexion.getInstancia().getConnection();
            mostrarInformacion(
                    "Conexión",
                    "Base de Datos",
                    "Conexión exitosa"
            );
        } catch (Exception e) {
            mostrarAlerta(
                    "Error",
                    "Base de Datos",
                    e.getMessage()
            );
        }}
// Alert de JavaFX. Este metodo permite mostrar mensajes de error cuando el usuario
// deja campos vacíos o cuando ocurre algún problema durante la autenticación.
    private void mostrarAlerta(
            String titulo,
            String encabezado,
            String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void mostrarInformacion(
            String titulo,
            String encabezado,
            String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}

