package com.renxo.gtorres.proyectofinal.models;

import java.util.ArrayList;

public class Inmuebles {
    private Integer InmuebleId;
    private String InmuebleTitulo;
    private String InmuebleBarrio;
    private String InmueblePrecio;
    private String InmuebleMetrosCuadrados;
    private Boolean Favorito;
    private Boolean InmuebleTieneBalcon;
    private Boolean InmuebleTieneGarage;
    private Boolean InmuebleTieneParrillero;
    private Boolean InmuebleTienePatio;
    private ArrayList<String> Fotos;
    private ArrayList<String> Habitaciones;

    public Integer getInmuebleId() {
        return InmuebleId;
    }

    public void setInmuebleId(Integer inmuebleId) {
        InmuebleId = inmuebleId;
    }

    public String getInmuebleTitulo() {
        return InmuebleTitulo;
    }

    public void setInmuebleTitulo(String inmuebleTitulo) {
        InmuebleTitulo = inmuebleTitulo;
    }

    public String getInmuebleBarrio() {
        return InmuebleBarrio;
    }

    public void setInmuebleBarrio(String inmuebleBarrio) {
        InmuebleBarrio = inmuebleBarrio;
    }

    public String getInmueblePrecio() {
        return InmueblePrecio;
    }

    public void setInmueblePrecio(String inmueblePrecio) {
        InmueblePrecio = inmueblePrecio;
    }

    public String getInmuebleMetrosCuadrados() {
        return InmuebleMetrosCuadrados;
    }

    public void setInmuebleMetrosCuadrados(String inmuebleMetrosCuadrados) {
        InmuebleMetrosCuadrados = inmuebleMetrosCuadrados;
    }

    public Boolean getFavorito() {
        return Favorito;
    }

    public void setFavorito(Boolean favorito) {
        Favorito = favorito;
    }

    public Boolean getInmuebleTieneBalcon() {
        return InmuebleTieneBalcon;
    }

    public void setInmuebleTieneBalcon(Boolean inmuebleTieneBalcon) {
        InmuebleTieneBalcon = inmuebleTieneBalcon;
    }

    public Boolean getInmuebleTieneGarage() {
        return InmuebleTieneGarage;
    }

    public void setInmuebleTieneGarage(Boolean inmuebleTieneGarage) {
        InmuebleTieneGarage = inmuebleTieneGarage;
    }

    public Boolean getInmuebleTieneParrillero() {
        return InmuebleTieneParrillero;
    }

    public void setInmuebleTieneParrillero(Boolean inmuebleTieneParrillero) {
        InmuebleTieneParrillero = inmuebleTieneParrillero;
    }

    public Boolean getInmuebleTienePatio() {
        return InmuebleTienePatio;
    }

    public void setInmuebleTienePatio(Boolean inmuebleTienePatio) {
        InmuebleTienePatio = inmuebleTienePatio;
    }

    public ArrayList<String> getFotos() {
        return Fotos;
    }

    public void setFotos(ArrayList<String> fotos) {
        Fotos = fotos;
    }

    public ArrayList<String> getHabitaciones() {
        return Habitaciones;
    }

    public void setHabitaciones(ArrayList<String> habitaciones) {
        Habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        return "Inmuebles{" +
                "InmuebleId=" + InmuebleId +
                ", InmuebleTitulo='" + InmuebleTitulo + '\'' +
                ", InmuebleBarrio='" + InmuebleBarrio + '\'' +
                ", InmueblePrecio='" + InmueblePrecio + '\'' +
                ", InmuebleMetrosCuadrados='" + InmuebleMetrosCuadrados + '\'' +
                ", Favorito=" + Favorito +
                ", InmuebleTieneBalcon=" + InmuebleTieneBalcon +
                ", InmuebleTieneGarage=" + InmuebleTieneGarage +
                ", InmuebleTieneParrillero=" + InmuebleTieneParrillero +
                ", InmuebleTienePatio=" + InmuebleTienePatio +
                ", Fotos=" + Fotos +
                ", Habitaciones=" + Habitaciones +
                '}';
    }
}
