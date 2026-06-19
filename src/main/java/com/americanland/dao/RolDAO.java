package com.americanland.dao;

import com.americanland.conexion.ConexionBD;
import com.americanland.modelo.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RolDAO {

    // CREATE - INSERT
    public void crearRol(Rol rol) {

        String sql = "INSERT INTO roles(nombre_rol, descripcion) VALUES (?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rol.getNombreRol());
            ps.setString(2, rol.getDescripcion());

            ps.executeUpdate();

            System.out.println("Rol creado correctamente");

        } catch (Exception e) {
            System.out.println("Error al crear rol: " + e.getMessage());
        }
    }

    // READ - SELECT
    public void listarRoles() {

        String sql = "SELECT * FROM roles";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                        rs.getString("nombre_rol") + " - " +
                        rs.getString("descripcion")
                );
            }

        } catch (Exception e) {
            System.out.println("Error al listar roles: " + e.getMessage());
        }
    }

    // UPDATE
    public void actualizarRol(Rol rol) {

        String sql = "UPDATE roles SET nombre_rol = ?, descripcion = ? WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, rol.getNombreRol());
            ps.setString(2, rol.getDescripcion());
            ps.setInt(3, rol.getId());

            ps.executeUpdate();

            System.out.println("Rol actualizado correctamente");

        } catch (Exception e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
        }
    }

    // DELETE
    public void eliminarRol(int id) {

        String sql = "DELETE FROM roles WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Rol eliminado correctamente");

        } catch (Exception e) {
            System.out.println("Error al eliminar rol: " + e.getMessage());
        }
    }
}