package com.example.registromascotas.factory;

import com.example.registromascotas.modelo.Mascota;

public class MascotaFactory {

    public static Mascota crearMascota(
            String codigo,
            String nombre,
            String raza,
            int edad,
            String propietario,
            String especie,
            String sexo,
            String estadoSalud,
            String observaciones) {
        Mascota mascota = new Mascota();
        mascota.setCodigo(codigo);
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setEdad(edad);
        mascota.setPropietario(propietario);
        mascota.setEspecie(especie);
        mascota.setSexo(sexo);
        mascota.setEstadoSalud(estadoSalud);
        mascota.setObservaciones(observaciones);
        return mascota;
    }
}