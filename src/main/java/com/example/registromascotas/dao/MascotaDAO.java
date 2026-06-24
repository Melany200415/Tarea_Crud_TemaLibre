package com.example.registromascotas.dao;

import com.example.registromascotas.modelo.Mascota;
import com.example.registromascotas.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    public void guardar(Mascota mascota) throws SQLException {
        String sql = """
        INSERT INTO mascotas
        (codigo,nombre,raza,edad,propietario,
         especie,sexo,estado_salud,observaciones)
        VALUES (?,?,?,?,?,?,?,?,?)
        """;
        Connection conn =
                Conexion.getInstancia().getConnection();
        PreparedStatement stmt =
                conn.prepareStatement(sql);
        stmt.setString(1, mascota.getCodigo());
        stmt.setString(2, mascota.getNombre());
        stmt.setString(3, mascota.getRaza());
        stmt.setInt(4, mascota.getEdad());
        stmt.setString(5, mascota.getPropietario());
        stmt.setString(6, mascota.getEspecie());
        stmt.setString(7, mascota.getSexo());
        stmt.setString(8, mascota.getEstadoSalud());
        stmt.setString(9, mascota.getObservaciones());
        stmt.executeUpdate();
    }
    public List<Mascota> listar() throws SQLException {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        Connection conn =
                Conexion.getInstancia().getConnection();
        PreparedStatement stmt =
                conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Mascota mascota = new Mascota();
            mascota.setId(rs.getInt("id"));
            mascota.setCodigo(rs.getString("codigo"));
            mascota.setNombre(rs.getString("nombre"));
            mascota.setRaza(rs.getString("raza"));
            mascota.setEdad(rs.getInt("edad"));
            mascota.setPropietario(rs.getString("propietario"));
            mascota.setEspecie(rs.getString("especie"));
            mascota.setSexo(rs.getString("sexo"));
            mascota.setEstadoSalud(rs.getString("estado_salud"));
            mascota.setObservaciones(rs.getString("observaciones"));
            mascotas.add(mascota);
        }
        return mascotas;
    }
    public void actualizar(Mascota mascota)
            throws SQLException {
        String sql = """
        UPDATE mascotas
        SET codigo=?,
        nombre=?,
         raza=?,
        edad=?,
        propietario=?,
         especie=?,
         sexo=?,
        estado_salud=?,
          observaciones=?
        WHERE id=?
        """;
        Connection conn =
                Conexion.getInstancia().getConnection();
        PreparedStatement stmt =
                conn.prepareStatement(sql);
        stmt.setString(1, mascota.getCodigo());
        stmt.setString(2, mascota.getNombre());
        stmt.setString(3, mascota.getRaza());
        stmt.setInt(4, mascota.getEdad());
        stmt.setString(5, mascota.getPropietario());
        stmt.setString(6, mascota.getEspecie());
        stmt.setString(7, mascota.getSexo());
        stmt.setString(8, mascota.getEstadoSalud());
        stmt.setString(9, mascota.getObservaciones());
        stmt.setInt(10, mascota.getId());
        stmt.executeUpdate();
    }
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM mascotas WHERE id=?";
        Connection conn =
                Conexion.getInstancia().getConnection();
        PreparedStatement stmt =
                conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}