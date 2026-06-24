<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>American Land - Gestión de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="row">
            <div class="col-12 text-center mb-4">
                <h1 class="display-5 fw-bold text-primary">🇺🇸 American Land Academy</h1>
                <p class="text-muted">Sistema de Gestión - Módulo Maestro de Usuarios</p>
                <hr>
            </div>
        </div>

        <div class="card shadow-sm mb-5">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">
                    <c:choose>
                        <c:when test="${usuarioEditar != null}">✏️ Editar Usuario: ${usuarioEditar.nombreCompleto}</c:when>
                        <c:otherwise>➕ Registrar Nuevo Usuario</c:otherwise>
                    </c:choose>
                </h4>
            </div>
            <div class="card-body">
                <form action="usuarios" method="POST" class="row g-3">
                    
                    <input type="hidden" name="id" value="${usuarioEditar != null ? usuarioEditar.id : ''}">

                    <div class="col-md-4">
                        <label class="form-label">Nombre Completo</label>
                        <input type="text" name="nombre" class="form-control" placeholder="Ej: Carlos Mendoza" 
                               value="${usuarioEditar != null ? usuarioEditar.nombreCompleto : ''}" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Correo Electrónico</label>
                        <input type="email" name="correo" class="form-control" placeholder="ejemplo@americanland.com" 
                               value="${usuarioEditar != null ? usuarioEditar.correo : ''}" required>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Contraseña</label>
                        <input type="password" name="password" class="form-control" placeholder="••••••••"
                               value="${usuarioEditar != null ? usuarioEditar.contrasenaHash : ''}" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">ID Rol</label>
                        <input type="number" name="id_rol" class="form-control" placeholder="Ej: 1" 
                               value="${usuarioEditar != null ? usuarioEditar.idRol : '1'}" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Fecha Nacimiento</label>
                        <input type="date" name="fecha_nacimiento" class="form-control" 
                               value="${usuarioEditar != null ? usuarioEditar.fechaNacimiento : ''}" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">País</label>
                        <input type="text" name="pais" class="form-control" placeholder="Ej: México" 
                               value="${usuarioEditar != null ? usuarioEditar.pais : 'Colombia'}" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Ciudad</label>
                        <input type="text" name="ciudad" class="form-control" placeholder="Ej: Medellín" 
                               value="${usuarioEditar != null ? usuarioEditar.ciudad : 'Bogotá'}" required>
                    </div>
                    <div class="col-12 text-end">
                        <c:if test="${usuarioEditar != null}">
                            <a href="usuarios" class="btn btn-secondary me-2">Cancelar</a>
                        </c:if>
                        <button type="submit" class="btn btn-success px-4">
                            ${usuarioEditar != null ? 'Actualizar en la Nube' : 'Guardar en la Nube'}
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="card shadow-sm">
            <div class="card-header bg-dark text-white">
                <h4 class="mb-0">📋 Listado Oficial de Usuarios</h4>
            </div>
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-striped table-hover mb-0">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>País/Ciudad</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="u" items="${listaUsuarios}">
                                <tr>
                                    <td>${u.id}</td>
                                    <td><strong>${u.nombreCompleto}</strong></td>
                                    <td>${u.correo}</td>
                                    <td>${u.ciudad}, ${u.pais}</td>
                                    <td>
                                        <span class="badge ${u.activo ? 'bg-success' : 'bg-danger'}">
                                            ${u.activo ? 'Activo' : 'Inactivo'}
                                        </span>
                                    </td>
                                    <td>
                                        <a href="usuarios?accion=editar&id=${u.id}" class="btn btn-sm btn-warning">Editar</a>
                                        <a href="usuarios?accion=eliminar&id=${u.id}" class="btn btn-sm btn-danger" 
                                           onclick="return confirm('¿Seguro que deseas eliminar a ${u.nombreCompleto}?');">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty listaUsuarios}">
                                <tr>
                                    <td colspan="6" class="text-center text-muted py-4">No hay usuarios registrados.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
