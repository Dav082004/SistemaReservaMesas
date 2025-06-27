<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Registro - Siete Sopas</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
  </head>

  <body>
    <!-- Navbar (igual que antes, no se muestra por brevedad) -->
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
                    <a class="navbar-brand login-icon" href="<c:url value='/login'/>">
                        <img src="<c:url value='/images/user3.png'/>" alt="Ícono de usuario" />
                    </a>
                </div>
            </div>
        </nav>
    </header>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
          <div class="card shadow-sm">
            <div class="card-body p-4">
              <h3 class="card-title text-center mb-4">Crear una Cuenta</h3>

              <!-- Mensaje de error de registro -->
              <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" role="alert">
                  ${errorMessage}
                </div>
              </c:if>

              <!-- Se usa un formulario HTML estándar -->
              <form action="<c:url value='/register'/>" method="post">
                <div class="mb-3">
                  <label for="nombreCompleto" class="form-label">Nombre Completo</label>
                  <input id="nombreCompleto" type="text" name="nombreCompleto" class="form-control" required />
                </div>
                <div class="mb-3">
                  <label for="telefono" class="form-label">Teléfono</label>
                  <input id="telefono" type="text" name="telefono" class="form-control" />
                </div>
                <div class="mb-3">
                  <label for="correo" class="form-label">Correo Electrónico</label>
                  <input id="correo" type="email" name="correo" class="form-control" required />
                </div>
                <div class="mb-3">
                  <label for="usuario" class="form-label">Nombre de Usuario</label>
                  <input id="usuario" type="text" name="usuario" class="form-control" required />
                </div>
                <div class="mb-3">
                  <label for="contrasena" class="form-label">Contraseña</label>
                  <input id="contrasena" type="password" name="contrasena" class="form-control" required />
                </div>
                <button type="submit" class="btn btn-success w-100 fw-bold mb-2">
                  Registrarse
                </button>
                <a class="btn btn-link w-100" href="<c:url value='/login'/>">
                  ¿Ya tienes cuenta? Inicia sesión
                </a>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
