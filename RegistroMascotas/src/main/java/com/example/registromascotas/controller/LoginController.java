package com.example.registromascotas.controller;

import com.example.registromascotas.util.Conexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private void iniciarSesion() {

        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        // 1. Validar campos vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Campos vacíos", "Debe ingresar usuario y contraseña");
            return;
        }

        // 2. Validación simple
        if (usuario.equals("admin") && contrasena.equals("1234")) {

            try {
                // 3. Abrir CRUD
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/registromascotas/CRUD.fxml")
                );

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Sistema de Mascotas");
                stage.setScene(new Scene(root));
                stage.show();

                // cerrar login
                Stage loginStage = (Stage) txtUsuario.getScene().getWindow();
                loginStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            mostrarAlerta("Error", "Login incorrecto", "Usuario o contraseña inválidos");
        }

        // 4. Probar conexión BD (opcional, no obligatorio en login)
        try {
            Conexion.getInstancia().getConnection();
        } catch (Exception e) {
            mostrarAlerta("Error BD", "Conexión fallida", e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String encabezado, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}