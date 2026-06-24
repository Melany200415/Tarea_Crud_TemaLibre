module com.example.registromascotas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.registromascotas.modelo to javafx.base;
    opens com.example.registromascotas to javafx.fxml;
    exports com.example.registromascotas;
    exports com.example.registromascotas.controller;
    opens com.example.registromascotas.controller to javafx.fxml;
}