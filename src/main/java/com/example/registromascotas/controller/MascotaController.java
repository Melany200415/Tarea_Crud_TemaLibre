package com.example.registromascotas.controller;
import com.example.registromascotas.dao.MascotaDAO;
import com.example.registromascotas.factory.MascotaFactory;
import com.example.registromascotas.modelo.Mascota;
import com.example.registromascotas.strategy.ValidacionMascota;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class MascotaController {
        @FXML
        private TextField txtCodigo;
        @FXML
        private TextField txtNombre;
        @FXML
        private TextField txtRaza;
        @FXML
        private TextField txtEdad;
        @FXML
        private TextField txtPropietario;
        @FXML
        private ComboBox<String> cbEspecie;
        @FXML
        private ComboBox<String> cbEstadoSalud;
        @FXML
        private RadioButton rbMacho;
        @FXML
        private RadioButton rbHembra;
        @FXML
        private TextArea txtObservaciones;
        @FXML
        private TableView<Mascota> tablaMascotas;
    @FXML
    private TableColumn<Mascota, String> colCodigo;

    @FXML
    private TableColumn<Mascota, String> colNombre;

    @FXML
    private TableColumn<Mascota, String> colRaza;

    @FXML
    private TableColumn<Mascota, Integer> colEdad;

    @FXML
    private TableColumn<Mascota, String> colPropietario;
    private ToggleGroup grupoSexo;
    private Mascota mascotaSeleccionada;
        @FXML
        public void initialize() {
            cbEspecie.getItems().addAll(
                    "Perro",
                    "Gato",
                    "Ave",
                    "Otro"
            );

            cbEstadoSalud.getItems().addAll(
                    "Sano",
                    "En tratamiento",
                    "Crítico"
            );
            grupoSexo = new ToggleGroup();
            rbMacho.setToggleGroup(grupoSexo);
            rbHembra.setToggleGroup(grupoSexo);
            String sexo = "";
            if (rbMacho.isSelected()) {
                sexo = "Macho";
            } else if (rbHembra.isSelected()) {
                sexo = "Hembra";
            }

                System.out.println("INICIALIZANDO CONTROLADOR");

                colCodigo.setCellValueFactory(
                        new PropertyValueFactory<>("codigo"));

                colNombre.setCellValueFactory(
                        new PropertyValueFactory<>("nombre"));

                colRaza.setCellValueFactory(
                        new PropertyValueFactory<>("raza"));

                colEdad.setCellValueFactory(
                        new PropertyValueFactory<>("edad"));

                colPropietario.setCellValueFactory(
                        new PropertyValueFactory<>("propietario"));

                cargarMascotas();

        }
    private void cargarMascotas() {

        try {

            MascotaDAO dao = new MascotaDAO();

            List<Mascota> lista = dao.listar();

            System.out.println("REGISTROS ENCONTRADOS: "
                    + lista.size());

            for (Mascota m : lista) {
                System.out.println(
                        m.getCodigo() + " - " +
                                m.getNombre()
                );
            }

            tablaMascotas.getItems().setAll(lista);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @FXML
    private void seleccionarMascota() {
        mascotaSeleccionada =
                tablaMascotas.getSelectionModel()
                        .getSelectedItem();
        if (mascotaSeleccionada != null) {
            txtCodigo.setText(
                    mascotaSeleccionada.getCodigo()
            );
            txtNombre.setText(
                    mascotaSeleccionada.getNombre()
            );
            txtRaza.setText(
                    mascotaSeleccionada.getRaza()
            );
            txtEdad.setText(
                    String.valueOf(
                            mascotaSeleccionada.getEdad()
                    )
            );
            txtPropietario.setText(
                    mascotaSeleccionada.getPropietario()
            );
            cbEspecie.setValue(
                    mascotaSeleccionada.getEspecie()
            );
            cbEstadoSalud.setValue(
                    mascotaSeleccionada.getEstadoSalud()
            );
            txtObservaciones.setText(
                    mascotaSeleccionada.getObservaciones()
            );
            if ("Macho".equals(
                    mascotaSeleccionada.getSexo())) {
                rbMacho.setSelected(true);
            } else {
                rbHembra.setSelected(true);
            }
        }
    }
    @FXML
    private void guardarMascota() {

        try {

            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String raza = txtRaza.getText().trim();
            String propietario = txtPropietario.getText().trim();
            String observaciones = txtObservaciones.getText().trim();

            int edad = Integer.parseInt(txtEdad.getText().trim());

            String especie = cbEspecie.getValue();
            String estadoSalud = cbEstadoSalud.getValue();

            String sexo = "";

            if (rbMacho.isSelected()) {
                sexo = "Macho";
            } else if (rbHembra.isSelected()) {
                sexo = "Hembra";
            }
            // FACTORY
            Mascota mascota = MascotaFactory.crearMascota(
                    codigo,
                    nombre,
                    raza,
                    edad,
                    propietario,
                    especie,
                    sexo,
                    estadoSalud,
                    observaciones
            );
            // STRATEGY
            ValidacionMascota validacion = new ValidacionMascota();
            validacion.validar(mascota);
            // DAO
            MascotaDAO dao = new MascotaDAO();
            dao.guardar(mascota);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Registro guardado");
            alert.setContentText("La mascota fue registrada correctamente");
            alert.showAndWait();
            limpiarCampos();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Edad inválida");
            alert.setContentText("La edad debe ser numérica");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo guardar");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        cargarMascotas();
    }
    @FXML
    private void actualizarMascota() {
        try {
            if (mascotaSeleccionada == null) {
                Alert alert =
                        new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setHeaderText("Sin selección");
                alert.setContentText(
                        "Seleccione una mascota"
                );
                alert.showAndWait();
                return;
            }
            mascotaSeleccionada.setCodigo(
                    txtCodigo.getText().trim());
            mascotaSeleccionada.setNombre(
                    txtNombre.getText().trim());
            mascotaSeleccionada.setRaza(
                    txtRaza.getText().trim());
            mascotaSeleccionada.setEdad(
                    Integer.parseInt(
                            txtEdad.getText().trim()));
            mascotaSeleccionada.setPropietario(
                    txtPropietario.getText().trim());
            mascotaSeleccionada.setEspecie(
                    cbEspecie.getValue());
            mascotaSeleccionada.setEstadoSalud(
                    cbEstadoSalud.getValue());
            mascotaSeleccionada.setObservaciones(
                    txtObservaciones.getText().trim());
            if (rbMacho.isSelected()) {
                mascotaSeleccionada.setSexo("Macho");
            } else {
                mascotaSeleccionada.setSexo("Hembra");
            }
            MascotaDAO dao = new MascotaDAO();
            dao.actualizar(mascotaSeleccionada);
            cargarMascotas();
            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Actualización");
            alert.setContentText(
                    "Mascota actualizada correctamente");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert =
                    new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo actualizar");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void eliminarMascota() {
        try {
            if (mascotaSeleccionada == null) {
                Alert alert =
                        new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setHeaderText("Sin selección");
                alert.setContentText(
                        "Seleccione una mascota"
                );
                alert.showAndWait();
                return;
            }
            Alert confirmacion =
                    new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar");
            confirmacion.setHeaderText("Eliminar mascota");
            confirmacion.setContentText(
                    "¿Está seguro de eliminar esta mascota?"
            );
            if (confirmacion.showAndWait().get()
                    == ButtonType.OK) {
                MascotaDAO dao = new MascotaDAO();
                dao.eliminar(
                        mascotaSeleccionada.getId()
                );
                cargarMascotas();
                limpiarCampos();
                mascotaSeleccionada = null;
                Alert alert =
                        new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Eliminación");
                alert.setContentText(
                        "Mascota eliminada correctamente"
                );
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert =
                    new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo eliminar");
            alert.setContentText(
                    e.getMessage()
            );
            alert.showAndWait();
        }
    }
@FXML
private void limpiarCampos() {
    txtCodigo.clear();
    txtNombre.clear();
    txtRaza.clear();
    txtEdad.clear();
    txtPropietario.clear();
    cbEspecie.setValue(null);
    cbEstadoSalud.setValue(null);
    rbMacho.setSelected(false);
    rbHembra.setSelected(false);
    txtObservaciones.clear();
    }
}