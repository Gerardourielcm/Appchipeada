package com.example.proyectilulu.Json;

import android.widget.EditText;

import com.example.proyectilulu.R;

import java.io.Serializable;

public class MyInfo implements Serializable {
    private String Nombre;
    private String Usuario;
    private String Contraseña;
    private String Correo;
    private String Telefono;
    private String Fecha;
    private  Boolean genero;
    private String edad;
    private Boolean notifi;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Boolean getGenero() {
        return genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Boolean getNotifi() {
        return notifi;
    }

    public void setNotifi(Boolean notifi) {
        this.notifi = notifi;
    }

    public MyInfo(){

    }
}
