<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Mi Perfil</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
    <link rel="stylesheet" href="<c:url value='/css/perfil.css'/>" />
  </head>

  <body>
    <!-- Navbar -->
    <header class="top-navbar">
      <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
          <a class="navbar-brand" href="<c:url value='/'/>">
            <img
              src="<c:url value='/images/47sopas.png'/>"
              alt="Logo Siete Sopas" />
          </a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav">
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
                <a class="nav-link" href="<c:url value='/reservaciones'/>"
                  >RESERVACIONES</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value='/nosotros'/>"
                  >NOSOTROS</a
                >
              </li>
              <c:if test="${not empty sessionScope.usuario}">
                <li class="nav-item">
                  <a class="nav-link active" href="<c:url value='/perfil'/>"
                    >PERFIL</a
                  >
                </li>
              </c:if>
              <c:if test="${sessionScope.usuario.rol == 'ADMIN'}">
                <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/admin'/>"
                    >PANEL ADMIN</a
                  >
                </li>
              </c:if>
            </ul>
            <c:if test="${not empty sessionScope.usuario}">
              <div class="d-flex align-items-center">
                <span class="navbar-text me-3">
                  Hola, <strong>${sessionScope.usuario.usuario}</strong>
                </span>
                <form
                  action="<c:url value='/logout'/>"
                  method="post"
                  class="d-inline">
                  <button class="btn btn-outline-danger btn-sm" type="submit">
                    Cerrar Sesión
                  </button>
                </form>
              </div>
            </c:if>
          </div>
        </div>
      </nav>
    </header>

    <!-- Perfil del Usuario -->
    <div class="container profile-container">
      <div class="row g-4">
        <!-- Información de Perfil -->
        <div class="col-md-4">
          <div class="card text-center shadow-sm">
            <div class="card-header">Perfil</div>
            <div class="card-body">
              <img
                src="<c:url value='/images/admin-placeholder.png'/>"
                class="rounded-circle mb-3"
                width="120"
                height="120"
                alt="Foto perfil" />
              <h5 class="card-title">${usuario.usuario}</h5>
              <p class="card-text">${usuario.correo}</p>
            </div>
          </div>
        </div>

        <div class="col-md-8">
          <!-- Mensajes de éxito/error para DATOS PERSONALES -->
          <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
              ${successMessage}
            </div>
          </c:if>
          <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">${errorMessage}</div>
          </c:if>

          <!-- Datos personales -->
          <div class="card shadow-sm">
            <div class="card-header">Datos Personales</div>
            <div class="card-body">
              <form
                id="form-perfil"
                action="<c:url value='/perfil/actualizar'/>"
                method="post">
                <div class="row g-3">
                  <div class="col-md-6">
                    <label class="form-label">Nombres Completos</label>
                    <input
                      type="text"
                      class="form-control"
                      name="nombreCompleto"
                      value="${usuario.nombreCompleto}"
                      required />
                  </div>
                  <div class="col-md-6">
                    <label class="form-label">Correo</label>
                    <input
                      type="email"
                      class="form-control"
                      name="correo"
                      value="${usuario.correo}"
                      disabled />
                  </div>
                  <div class="col-md-6">
                    <label class="form-label">Teléfono</label>
                    <input
                      type="text"
                      class="form-control"
                      name="telefono"
                      value="${usuario.telefono}" />
                  </div>
                  <div class="col-12 d-flex justify-content-end mt-3">
                    <button type="submit" class="btn btn-success">
                      Guardar Cambios
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>

          <!-- Mensajes de éxito/error para CONTRASEÑA -->
          <c:if test="${not empty passwordSuccess}">
            <div class="alert alert-success mt-4" role="alert">
              ${passwordSuccess}
            </div>
          </c:if>
          <c:if test="${not empty passwordError}">
            <div class="alert alert-danger mt-4" role="alert">
              ${passwordError}
            </div>
          </c:if>

          <!-- Cambiar contraseña -->
          <div class="card shadow-sm mt-4">
            <div class="card-header">Cambiar Contraseña</div>
            <div class="card-body">
              <form
                id="form-password"
                action="<c:url value='/perfil/cambiar-contrasena'/>"
                method="post">
                <div class="row g-3">
                  <div class="col-md-6">
                    <label class="form-label">Contraseña Actual</label>
                    <input
                      type="password"
                      class="form-control"
                      name="contrasenaActual"
                      required />
                  </div>
                  <div class="col-md-6">
                    <label class="form-label">Nueva Contraseña</label>
                    <input
                      type="password"
                      class="form-control"
                      name="nuevaContrasena"
                      required />
                  </div>
                  <div class="col-md-12">
                    <label class="form-label">Confirmar Nueva Contraseña</label>
                    <input
                      type="password"
                      class="form-control"
                      name="confirmarContrasena"
                      required />
                  </div>
                  <div class="col-12 d-flex justify-content-end mt-3">
                    <button type="submit" class="btn btn-danger">
                      Actualizar Contraseña
                    </button>
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
