<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Mi Perfil</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" />
      <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
      <link rel="stylesheet" href="<c:url value='/css/perfil.css'/>" />
    </head>

    <body>
      <!-- Navbar (igual que en las otras páginas) -->
      <header class="top-navbar">
        <nav class="navbar navbar-expand-lg navbar-light">
          <div class="container">
            <a class="navbar-brand" href="<c:url value='/'/>">
              <img src="<c:url value='/images/47sopas.png'/>" alt="Logo Siete Sopas" />
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav me-auto ms-4">
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/'/>">INICIO</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/menu'/>">MENU</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/reservaciones'/>">RESERVACIONES</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/nosotros'/>">NOSOTROS</a>
                </li>
              </ul>
              <!-- Dropdown del usuario -->
              <c:if test="${not empty sessionScope.usuario}">
                <div class="d-flex align-items-center">
                  <span class="navbar-text me-3">Hola, <strong>${sessionScope.usuario.nombreCompleto}</strong></span>
                  <div class="dropdown">
                    <a class="dropdown-toggle d-flex align-items-center text-decoration-none" href="#" role="button"
                      id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                      <img src="<c:url value='/images/user3.png'/>" alt="Ícono de usuario"
                        style="width: 40px; height: 40px; border-radius: 50%;">
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                      <li>
                        <!-- ***** CORRECCIÓN AQUÍ ***** -->
                        <a class="dropdown-item" href="<c:url value='/perfil'/>">
                          <i class="bi bi-person-circle me-2"></i>Ver Perfil
                        </a>
                      </li>
                      <li>
                        <hr class="dropdown-divider">
                      </li>
                      <li>
                        <a class="dropdown-item" href="#" onclick="document.getElementById('logoutForm').submit();">
                          <i class="bi bi-box-arrow-right me-2"></i>Cerrar Sesión
                        </a>
                      </li>
                    </ul>
                  </div>
                  <form id="logoutForm" action="<c:url value='/logout'/>" method="post" style="display: none;"></form>
                </div>
              </c:if>
            </div>
          </div>
        </nav>
      </header>

      <!-- Perfil del Usuario -->
      <div class="container profile-container">
        <div class="row g-4">
          <!-- Columna de Información -->
          <div class="col-md-4">
            <div class="card text-center shadow-sm">
              <div class="card-header">Perfil</div>
              <div class="card-body">
                <img src="<c:url value='/images/admin-placeholder.png'/>" class="rounded-circle mb-3" width="120"
                  height="120" alt="Foto perfil" />
                <h5 class="card-title">${sessionScope.usuario.usuario}</h5>
                <p class="card-text">${sessionScope.usuario.correo}</p>
              </div>
            </div>
          </div>

          <!-- Columna de Formularios -->
          <div class="col-md-8">
            <!-- Mensajes de éxito/error para DATOS PERSONALES -->
            <c:if test="${not empty successMessage}">
              <div class="alert alert-success" role="alert">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
              <div class="alert alert-danger" role="alert">${errorMessage}</div>
            </c:if>

            <!-- Datos personales -->
            <div class="card shadow-sm">
              <div class="card-header">Datos Personales</div>
              <div class="card-body">
                <form id="form-perfil" action="<c:url value='/perfil/datos'/>" method="post">
                  <div class="row g-3">
                    <div class="col-md-6">
                      <label class="form-label">Nombres Completos</label>
                      <input type="text" class="form-control" name="nombreCompleto"
                        value="${sessionScope.usuario.nombreCompleto}" required />
                    </div>
                    <div class="col-md-6">
                      <label class="form-label">Correo</label>
                      <input type="email" class="form-control" name="correo" value="${sessionScope.usuario.correo}"
                        disabled />
                    </div>
                    <div class="col-md-6">
                      <label class="form-label">Teléfono</label>
                      <input type="text" class="form-control" name="telefono"
                        value="${sessionScope.usuario.telefono}" />
                    </div>
                    <div class="col-12 d-flex justify-content-end mt-3">
                      <button type="submit" class="btn btn-success">Guardar Cambios</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>

            <!-- Mensajes de éxito/error para CONTRASEÑA -->
            <c:if test="${not empty passwordSuccess}">
              <div class="alert alert-success mt-4" role="alert">${passwordSuccess}</div>
            </c:if>
            <c:if test="${not empty passwordError}">
              <div class="alert alert-danger mt-4" role="alert">${passwordError}</div>
            </c:if>

            <!-- Cambiar contraseña -->
            <div class="card shadow-sm mt-4">
              <div class="card-header">Cambiar Contraseña</div>
              <div class="card-body">
                <form id="form-password" action="<c:url value='/perfil/password'/>" method="post">
                  <div class="row g-3">
                    <div class="col-md-6">
                      <label class="form-label">Contraseña Actual</label>
                      <input type="password" class="form-control" name="contrasenaActual" required />
                    </div>
                    <div class="col-md-6">
                      <label class="form-label">Nueva Contraseña</label>
                      <input type="password" class="form-control" name="nuevaContrasena" required />
                    </div>
                    <div class="col-md-12">
                      <label class="form-label">Confirmar Nueva Contraseña</label>
                      <input type="password" class="form-control" name="confirmarContrasena" required />
                    </div>
                    <div class="col-12 d-flex justify-content-end mt-3">
                      <button type="submit" class="btn btn-danger">Actualizar Contraseña</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>