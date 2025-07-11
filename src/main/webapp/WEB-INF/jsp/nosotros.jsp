<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Acerca de nosotros - Siete Sopas</title>

      <!-- Bootstrap CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
      <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
      <link rel="stylesheet" href="<c:url value='/css/nosotros.css'/>" />
    </head>

    <body class="d-flex flex-column min-vh-100">
      <!-- Navbar -->
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
                  <a class="nav-link active" href="<c:url value='/nosotros'/>">NOSOTROS</a>
                </li>
              </ul>
              <!-- Mostrar información de usuario o enlace de login -->
              <c:choose>
                <c:when test="${not empty sessionScope.usuario}">
                  <div class="d-flex align-items-center">
                    <span class="navbar-text me-3">
                      Hola,
                      <strong>${sessionScope.usuario.nombreCompleto}</strong>
                    </span>
                    <!-- Dropdown del usuario -->
                    <div class="dropdown">
                      <a class="dropdown-toggle d-flex align-items-center text-decoration-none" href="#" role="button"
                        id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="<c:url value='/images/user3.png'/>" alt="Ícono de usuario"
                          style="width: 40px; height: 40px; border-radius: 50%;">
                      </a>
                      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                        <li>
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
                    <!-- Formulario oculto para logout -->
                    <form id="logoutForm" action="<c:url value='/logout'/>" method="post" style="display: none;">
                    </form>
                  </div>
                </c:when>
                <c:otherwise>
                  <a class="navbar-brand login-icon" href="<c:url value='/login'/>">
                    <img src="<c:url value='/images/user3.png'/>" alt="Ícono de usuario">
                  </a>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </nav>
      </header>

      <main class="flex-grow-1">
        <div class="container mt-5 mb-5">
          <div class="about-container">
            <div class="image-section">
              <div class="image-placeholder">
                <img src="<c:url value='/images/sieteSopas_nosotros.jpg'/>" alt="Siete Sopas"
                  style="width: 100%; border-radius: 5px; height: 100%" />
              </div>
            </div>

            <div class="content-section">
              <h1>Siete Sopas</h1>

              <div class="about-us">
                <h2>Acerca de nosotros</h2>
                <p>
                  En Siete Sopas nos especializamos en ofrecer sopas tradicionales
                  y reconfortantes, preparadas con ingredientes frescos y recetas
                  caseras que han pasado de generación en generación.
                </p>
                <p>
                  Nuestro compromiso es brindar a nuestros clientes una
                  experiencia culinaria cálida y satisfactoria, donde cada
                  cucharada transmita el amor y dedicación que ponemos en nuestras
                  preparaciones.
                </p>
              </div>

              <div class="contact-info">
                <h2>Información de contacto</h2>
                <ul>
                  <li>Av. Ricardo Palma 205, Miraflores 15074</li>
                  <li>
                    <a href="https://maps.google.com" class="map-link" target="_blank">Ver en Google Maps</a>
                  </li>
                </ul>
              </div>

              <div class="hours">
                <h2>Horario de atención</h2>
                <ul>
                  <li>Lunes a domingo: 9:00 AM - 1:00 AM</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </main>

      <!-- Footer -->
      <footer style="background-color: #fffbeb" class="text-dark pt-5 pb-5">
        <div class="container">
          <div class="row gx-5">
            <div class="col-md-4 mb-4 px-4 texto-justificado">
              <h5 class="fw-bold">Información de contacto</h5>
              <p>📍 Av. Ricardo Palma 205, Miraflores 15074</p>
              <p>
                <a href="https://maps.app.goo.gl/1Vak4fM1xVT4TR9N8" target="_blank"
                  style="color: #b52e2e; text-decoration: none">
                  Ver en Google Maps
                </a>
              </p>
            </div>
            <div class="col-md-4 mb-4 px-4 text-center">
              <h5 class="fw-bold">Acerca de nosotros</h5>
              <p>
                En Siete Sopas nos especializamos en ofrecer sopas tradicionales y
                reconfortantes, preparadas con ingredientes frescos y recetas
                caseras.
              </p>
            </div>
            <div class="col-md-4 mb-4 px-4 text-center">
              <h5 class="fw-bold">Horas abiertas</h5>
              <div class="horario">
                <p>Lunes a domingo:</p>
                <p>🕘 9:00 AM - 1:00 AM</p>
              </div>
            </div>
          </div>
        </div>
        <div class="text-center text-white py-3 mt-4" style="background-color: #000">
          <p class="mb-0">
            &copy; 2025 Restaurante Siete Sopas. Todos los derechos reservados.
          </p>
        </div>
      </footer>

      <!-- Bootstrap JS -->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>