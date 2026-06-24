package com.example.registromascotas.strategy;

import com.example.registromascotas.modelo.Mascota;

public class ValidacionMascota implements IValidacion {

    @Override
    public void validar(Mascota mascota) {

        if (mascota.getCodigo() == null ||
                mascota.getCodigo().isEmpty()) {

            throw new IllegalArgumentException(
                    "El código es obligatorio"
            );
        }

        if (mascota.getNombre() == null ||
                mascota.getNombre().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre es obligatorio"
            );
        }

        if (mascota.getPropietario() == null ||
                mascota.getPropietario().isEmpty()) {

            throw new IllegalArgumentException(
                    "El propietario es obligatorio"
            );
        }

        if (mascota.getEdad() <= 0) {

            throw new IllegalArgumentException(
                    "La edad debe ser mayor a 0"
            );
        }
    }
}