package com.americanland;

import com.americanland.dao.UsuarioDAO;
import com.americanland.modelo.Usuario;
import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DEL MÓDULO DE USUARIOS ===");

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // 1. CREAR UN USUARIO DE PRUEBA
        // Nota: Usamos idRol = 1 (Asegúrate de que el id_rol 1 exista en tu tabla roles, ej: Admin)
        Usuario nuevoUsuario = new Usuario(
            1, 
            "Aaron Cabrera Test", 
            "aaron.test@americanland.com", 
            "password_encriptado_123", 
            LocalDate.of(2000, 5, 15), 
            "Colombia", 
            "Bogotá", 
            true
        );

        System.out.println("Intentando registrar a: " + nuevoUsuario.getNombreCompleto() + " en la nube...");
        boolean insertado = usuarioDAO.crearUsuario(nuevoUsuario);

        if (insertado) {
            System.out.println("✅ ¡Usuario creado con éxito en TiDB Cloud!");
        } else {
            System.out.println("❌ No se pudo crear el usuario. Revisa la consola para ver el error.");
        }

        System.out.println("\n=== LISTADO ACTUAL DE USUARIOS EN LA BASE DE DATO ===");
        // 2. LISTAR LOS USUARIOS PARA VERIFICAR
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombreCompleto() + " | Correo: " + u.getCorreo() + " | Estado: " + (u.isActivo() ? "Activo" : "Inactivo"));
        }
    }
}