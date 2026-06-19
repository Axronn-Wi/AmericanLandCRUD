package com.americanland.modelo;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Usuario {
    // Atributos basados estrictamente en las columnas de la base de datos
    private int id;
    private int idRol;
    private String nombreCompleto;
    private String correo;
    private String contrasenaHash; // Usamos contrasena para evitar la 'ñ' en código externo
    private LocalDate fechaNacimiento;
    private String pais;
    private String ciudad;
    private boolean activo;
    private Timestamp creadoEn;
    private Timestamp actualizadoEn;

    // Constructor vacío (necesario para cuando listemos datos)
    public Usuario() {}

    // Constructor lleno para cuando vayamos a insertar un nuevo usuario
    public Usuario(int idRol, String nombreCompleto, String correo, String contrasenaHash, 
                   LocalDate fechaNacimiento, String pais, String ciudad, boolean activo) {
        this.idRol = idRol;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasenaHash = contrasenaHash;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.ciudad = ciudad;
        this.activo = activo;
    }

    // --- GETTERS Y SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasenaHash() { return contrasenaHash; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Timestamp getCreadoEn() { return creadoEn; }
    public void setCreadoEn(Timestamp creadoEn) { this.creadoEn = creadoEn; }

    public Timestamp getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(Timestamp actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}