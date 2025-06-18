<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Login - Siete Sopas</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
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
              <li class="nav-item">
                <a class="nav-link" href="<c:url value='/perfil'/>">PERFIL</a>
              </li>
            </ul>
            <a class="navbar-brand login-icon" href="<c:url value='/login'/>">
              <img
                src="<c:url value='/images/user3.png'/>"
                alt="Ícono de usuario" />
            </a>
          </div>
        </div>
      </nav>
    </header>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
          <div class="card shadow-sm">
            <div class="card-body p-4">
              <h3 class="card-title text-center mb-4">Iniciar Sesión</h3>

              <!-- Mensaje de error de login -->
              <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                  Usuario o contraseña incorrectos.
                </div>
              </c:if>

              <!-- Mensaje de logout exitoso -->
              <c:if test="${param.logout != null}">
                <div class="alert alert-success" role="alert">
                  Has cerrado sesión exitosamente.
                </div>
              </c:if>

              <!-- Mensaje de registro exitoso -->
              <c:if test="${not empty successMessage}">
                <div class="alert alert-success" role="alert">
                  ${successMessage}
                </div>
              </c:if>
              <form method="post" action="<c:url value='/login'/>">
                <div class="mb-3">
                  <label for="username" class="form-label">Usuario</label>
                  <input
                    type="text"
                    id="username"
                    name="username"
                    class="form-control"
                    required
                    autofocus />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Contraseña</label>
                  <input
                    type="password"
                    id="password"
                    name="password"
                    class="form-control"
                    required />
                </div>
                <button
                  type="submit"
                  class="btn btn-success w-100 fw-bold mb-2">
                  Iniciar sesión
                </button>
                <a
                  class="btn btn-outline-success w-100 fw-bold"
                  href="<c:url value='/register'/>"
                  >¿No tienes cuenta? Regístrate</a
                >
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
