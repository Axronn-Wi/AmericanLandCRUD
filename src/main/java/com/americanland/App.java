package com.americanland;

import com.americanland.dao.RolDAO;
import com.americanland.modelo.Rol;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Aplicación American Land ===");

        // 1. Instanciamos el DAO (el que maneja la base de datos)
        RolDAO rolDao = new RolDAO();

        // 2. Creamos un nuevo objeto Rol de prueba con datos para American Land
        Rol nuevoRol = new Rol();
        nuevoRol.setNombreRol("Teacher"); 
        nuevoRol.setDescripcion("Docente encargado de las clases de inglés de American Land");

        System.out.println("Intentando registrar el rol: " + nuevoRol.getNombreRol());

        // 3. Ejecutamos tu método real: crearRol
        try {
            rolDao.crearRol(nuevoRol); 
            System.out.println("¡Proceso terminado!");
            
            // 4. Aprovechemos para listar los roles que hay en la base de datos
            System.out.println("\n=== Lista Actual de Roles ===");
            rolDao.listarRoles();
            
        } catch (Exception e) {
            System.out.println("Upps... Algo falló en la ejecución principal.");
            e.printStackTrace();
        }
    }
}