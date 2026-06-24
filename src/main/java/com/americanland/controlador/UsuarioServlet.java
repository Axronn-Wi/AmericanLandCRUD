package com.americanland.controlador;

import com.americanland.dao.UsuarioDAO;
import com.americanland.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    // 📋 MANEJA CONSULTAS, EDICIÓN Y ELIMINACIÓN (Petición GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        String idParam = request.getParameter("id");

        // 🚨 Rastreador en la consola de VS Code
        System.out.println("=== PETICIÓN DETECTADA EN DOGET ===");
        System.out.println("Acción: " + accion + " | ID: " + idParam);

        if (accion != null && idParam != null) {
            int id = Integer.parseInt(idParam);
            
            if (accion.equals("eliminar")) {
                usuarioDAO.eliminarUsuario(id);
                response.sendRedirect("usuarios");
                return; // Corta la ejecución aquí porque redirigimos
            } else if (accion.equals("editar")) {
                System.out.println("-> Cargando datos para editar al ID: " + id);
                Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorId(id);
                // Pasamos el usuario encontrado al JSP
                request.setAttribute("usuarioEditar", usuarioExistente);
            }
        }
        
        // Obtener la lista actualizada de usuarios desde la nube para pintar la tabla
        List<Usuario> lista = usuarioDAO.listarUsuarios();
        request.setAttribute("listaUsuarios", lista);
        
        // Redireccionar visualmente al JSP
        request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
    }

    // ➕ CREA O ACTUALIZA REGISTROS (Petición POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id"); // Captura el ID oculto si estamos editando
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        int idRol = Integer.parseInt(request.getParameter("id_rol"));
        LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));
        String pais = request.getParameter("pais");
        String city = request.getParameter("ciudad");

        if (idParam != null && !idParam.isEmpty()) {
            // ✏️ Si el formulario trae un ID, significa que estamos ACTUALIZANDO
            int id = Integer.parseInt(idParam);
            System.out.println("-> Procesando ACTUALIZACIÓN en POST para el ID: " + id);
            Usuario usuarioEditado = new Usuario(idRol, nombre, correo, password, fechaNacimiento, pais, city, true);
            usuarioEditado.setId(id);
            usuarioDAO.actualizarUsuario(usuarioEditado);
        } else {
            // ➕ Si NO trae un ID, estamos CREANDO un registro nuevo
            System.out.println("-> Procesando CREACIÓN de nuevo usuario en POST");
            Usuario nuevoUsuario = new Usuario(idRol, nombre, correo, password, fechaNacimiento, pais, city, true);
            usuarioDAO.crearUsuario(nuevoUsuario);
        }
        
        // Recargar la página limpia
        response.sendRedirect("usuarios");
    }
}