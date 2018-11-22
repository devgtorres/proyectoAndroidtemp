package com.renxo.gtorres.proyectofinal.models;

public class Habitaciones {
    private String InmuebleHabitacionNombre;
    private String InmuebleHabitacionCantidad;

    public String getInmuebleHabitacionNombre() {
        return InmuebleHabitacionNombre;
    }

    public void setInmuebleHabitacionNombre(String inmuebleHabitacionNombre) {
        InmuebleHabitacionNombre = inmuebleHabitacionNombre;
    }

    public String getInmuebleHabitacionCantidad() {
        return InmuebleHabitacionCantidad;
    }

    public void setInmuebleHabitacionCantidad(String inmuebleHabitacionCantidad) {
        InmuebleHabitacionCantidad = inmuebleHabitacionCantidad;
    }

    @Override
    public String toString() {
        return "Habitaciones{" +
                "InmuebleHabitacionNombre='" + InmuebleHabitacionNombre + '\'' +
                ", InmuebleHabitacionCantidad='" + InmuebleHabitacionCantidad + '\'' +
                '}';
    }
}
