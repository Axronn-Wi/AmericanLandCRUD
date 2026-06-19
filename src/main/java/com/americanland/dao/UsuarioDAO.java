package com.americanland.dao;

import com.americanland.conexion.ConexionBD;
import com.americanland.modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // 1. CREATE: Insertar un nuevo usuario en la nube
    public boolean crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id_rol, nombre_completo, correo, contraseña_hash, fecha_nacimiento, pais, ciudad, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, usuario.getIdRol());
            ps.setString(2, usuario.getNombreCompleto());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasenaHash());
            
            // Convertimos el LocalDate de Java al Date que entiende SQL
            ps.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
            ps.setString(6, usuario.getPais());
            ps.setString(7, usuario.getCiudad());
            ps.setBoolean(8, usuario.isActivo());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al crear usuario en TiDB Cloud: " + e.getMessage());
            return false;
        }
    }

    // 2. READ: Listar todos los usuarios actuales
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setCorreo(rs.getString("correo"));
                u.setContrasenaHash(rs.getString("contraseña_hash"));
                
                // Convertimos el Date de SQL de vuelta a LocalDate de Java
                if (rs.getDate("fecha_nacimiento") != null) {
                    u.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                }
                
                u.setPais(rs.getString("pais"));
                u.setCiudad(rs.getString("ciudad"));
                u.setActivo(rs.getBoolean("activo"));
                u.setCreadoEn(rs.getTimestamp("creado_en"));
                u.setActualizadoEn(rs.getTimestamp("actualizado_en"));
                
                lista.add(u);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios de TiDB Cloud: " + e.getMessage());
        }
        return lista;
    }
}