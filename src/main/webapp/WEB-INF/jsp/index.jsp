<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Inicio - Siete Sopas</title>

      <!-- Bootstrap CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
      <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
    </head>

    <body>
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
                  <a class="nav-link" href="<c:url value='/nosotros'/>">NOSOTROS</a>
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
                        <li> <a class="dropdown-item" href="<c:url value='/perfil'/>">
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

      <!-- MENSAJE DE VERIFICACIÓN JSP -->
      <div style="
        background-color: yellow;
        text-align: center;
        padding: 10px;
        font-weight: bold;
      ">
        🎉 JSP FUNCIONANDO CORRECTAMENTE - Fecha actual: <%= new java.util.Date() %>
      </div>

      <!-- Carrusel Responsive -->
      <div id="carruselPrincipal" class="carousel slide carousel-fade carrusel-solapado" data-bs-ride="carousel">
        <div class="carousel-inner">
          <!-- Slide 1 -->
          <div class="carousel-item active">
            <img src="<c:url value='/images/sede.jpg'/>" class="d-block w-100 img-fluid" alt="Siete Sopas Fachada" />
            <div class="carousel-caption custom-caption text-start">
              <h2 class="fw-bold">SEDE MIRAFLORES</h2>
              <p>Tradición peruana servida en cada plato y sopa</p>
              <a href="<c:url value='/reservaciones'/>" class="btn btn-success">RESERVAR MESA</a>
            </div>
          </div>

          <!-- Slide 2 -->
          <div class="carousel-item">
            <img src="<c:url value='/images/menu1.jpg'/>" class="d-block w-100 img-fluid" alt="Plato de sopa" />
            <div class="carousel-caption custom-caption text-start">
              <h2 class="fw-bold">¡Tu mesa te espera!</h2>
              <p>Abiertos siempre, listos para servirte</p>
              <a href="<c:url value='/reservaciones'/>" class="btn btn-success">RESERVAR MESA</a>
            </div>
          </div>
        </div>

        <!-- Controles -->
        <button class="carousel-control-prev" type="button" data-bs-target="#carruselPrincipal" data-bs-slide="prev">
          <span class="carousel-control-prev-icon bg-danger p-2 rounded-circle"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carruselPrincipal" data-bs-slide="next">
          <span class="carousel-control-next-icon bg-danger p-2 rounded-circle"></span>
        </button>
      </div>

      <!-- Reel embed  -->
      <section id="menu" class="reel-section text-white py-5">
        <div class="container text-center">
          <h2 class="mb-4 text-white fw-bold">Reel destacado</h2>

          <div class="d-flex justify-content-center">
            <blockquote class="instagram-media" data-instgrm-permalink="https://www.instagram.com/reel/C_049GiSbTI/"
              data-instgrm-version="14" style="
              background: #fff;
              border: 0;
              max-width: 540px;
              width: 100%;
            "></blockquote>
          </div>

          <script async src="//www.instagram.com/embed.js"></script>
        </div>
      </section>

      <!-- Footer -->
      <footer style="background-color: #fffbeb" class="text-dark pt-5 pb-5">
        <div class="container">
          <div class="row gx-5">
            <!-- Columna 1: Contacto -->
            <div class="col-md-4 mb-4 px-4 texto-justificado">
              <h5 class="fw-bold">Información de contacto</h5>
              <div class="texto-justificado">
                <p>📍 Av. Ricardo Palma 205, Miraflores 15074</p>
                <p>
                  <a href="https://maps.app.goo.gl/gfmHHgNU4oSQq1CS7" target="_blank"
                    style="color: #b52e2e; text-decoration: none">
                    Ver en Google Maps
                  </a>
                </p>
              </div>
            </div>

            <!-- Columna 2: Nosotros -->
            <div class="col-md-4 mb-4 px-4 text-center">
              <h5 class="fw-bold">Acerca de nosotros</h5>
              <div class="texto-justificado">
                <p>
                  En Siete Sopas nos especializamos en ofrecer sopas tradicionales
                  y reconfortantes, preparadas con ingredientes frescos y recetas
                  caseras. <br />
                </p>
              </div>
            </div>

            <!-- Columna 3: Horario -->
            <div class="col-md-4 mb-4 px-4 text-center">
              <h5 class="fw-bold">Horas abiertas</h5>
              <div class="horario">
                <p>Lunes a domingo:</p>
                <p>🕘 9:00 AM - 1:00 AM</p>
              </div>
            </div>
          </div>
        </div>

        <!-- Línea inferior -->
        <div class="text-center text-white py-3 mt-4" style="background-color: #000">
          <p class="mb-0">
            &copy; 2025 Restaurante Siete Sopas. Todos los derechos reservados.
          </p>
        </div>
      </footer>

      <!-- Bootstrap JS Bundle -->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

      <!-- Carga el script de Instagram para mostrar el post -->
      <script async src="//www.instagram.com/embed.js"></script>
    </body>

    </html>