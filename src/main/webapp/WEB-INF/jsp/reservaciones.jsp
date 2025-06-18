<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Reservaciones - Siete Sopas</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" />
    <link href="<c:url value='/css/styles.css'/>" rel="stylesheet" />
  </head>

  <body class="d-flex flex-column min-vh-100">
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
                <a
                  class="nav-link active"
                  href="<c:url value='/reservaciones'/>"
                  >RESERVACIONES</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value='/nosotros'/>"
                  >NOSOTROS</a
                >
              </li>
            </ul>            <!-- Mostrar información de usuario o enlace de login -->
            <c:choose>
              <c:when test="${not empty sessionScope.usuario}">
                <div class="d-flex align-items-center">
                  <span class="navbar-text me-3">
                    Hola,
                    <strong>${sessionScope.usuario.nombreCompleto}</strong>
                  </span>
                  <!-- Dropdown del usuario -->
                  <div class="dropdown">
                    <a class="dropdown-toggle d-flex align-items-center text-decoration-none" 
                       href="#" role="button" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                      <img src="<c:url value='/images/user3.png'/>" 
                           alt="Ícono de usuario" 
                           style="width: 40px; height: 40px; border-radius: 50%;">
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">                      <li>
                        <a class="dropdown-item" href="<c:url value='/usuario/perfil'/>">
                          <i class="bi bi-person-circle me-2"></i>Ver Perfil
                        </a>
                      </li>
                      <li><hr class="dropdown-divider"></li>
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
                <a
                  class="navbar-brand login-icon"
                  href="<c:url value='/login'/>">
                  <img
                    src="<c:url value='/images/user3.png'/>"
                    alt="Ícono de usuario" />
                </a>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </nav>
    </header>
    <main class="flex-grow-1">
      <div class="container mt-5 mb-5">
        <h2 class="text-center text-success mb-4 fw-bold">Reserva tu Mesa</h2>
        <!-- Verificación de autenticación -->
        <c:choose>
          <c:when test="${empty sessionScope.usuario}">
            <div class="row justify-content-center">
              <div class="col-md-8 col-lg-6">
                <div class="card shadow-sm">
                  <div class="card-body text-center p-5">                    <i
                      class="bi bi-person-lock display-1 text-warning mb-3"></i>
                    <h3 class="card-title mb-3">¡Necesitas estar logueado!</h3>
                    <p class="card-text text-muted mb-4">
                      Solo puedes hacer una reserva si estás logueado en el sistema. 
                      Si ya tienes una cuenta, inicia sesión. Si no, créate una cuenta gratis.
                    </p>
                    <div
                      class="d-grid gap-2 d-md-flex justify-content-md-center">
                      <a
                        href="<c:url value='/login'/>"
                        class="btn btn-success btn-lg me-md-2">
                        <i class="bi bi-box-arrow-in-right"></i> Iniciar Sesión
                      </a>
                      <a
                        href="<c:url value='/register'/>"
                        class="btn btn-outline-success btn-lg">
                        <i class="bi bi-person-plus"></i> Crear Cuenta
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <!-- Usuario autenticado - mostrar formulario de reserva -->

            <!-- Mensajes de feedback -->
            <c:if test="${not empty successMessage}">
              <div class="alert alert-success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
              <div class="alert alert-danger">${errorMessage}</div>
            </c:if>

            <form:form
              id="reservaForm"
              action="${pageContext.request.contextPath}/reservaciones/crear"
              method="post"
              modelAttribute="reservaForm">
              <div class="row">
                <!-- Columna 1: Datos del cliente -->
                <div class="col-md-6 mb-4">
                  <h5 class="fw-bold mb-3">Datos del cliente</h5>
                  <div class="mb-3">
                    <label for="nombre" class="form-label"
                      >Nombre completo</label
                    >
                    <form:input
                      type="text"
                      class="form-control"
                      id="nombre"
                      path="nombreCliente"
                      required="true"
                      readonly="true" />
                  </div>
                  <div class="mb-3">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <form:input
                      type="tel"
                      class="form-control"
                      id="telefono"
                      path="telefonoCliente"
                      required="true"
                      readonly="true" />
                  </div>
                  <div class="mb-3">
                    <label for="correo" class="form-label"
                      >Correo electrónico</label
                    >
                    <form:input
                      type="email"
                      class="form-control"
                      id="correo"
                      path="correoCliente"
                      required="true"
                      readonly="true" />
                  </div>
                  <div class="mb-3">
                    <label for="comentarios" class="form-label"
                      >Comentarios adicionales</label
                    >
                    <form:textarea
                      class="form-control"
                      id="comentarios"
                      rows="3"
                      path="comentarios"
                      placeholder="Ej. Celebración, silla para bebé, etc." />
                  </div>
                </div>

                <!-- Columna 2: Detalles de la reserva -->
                <div class="col-md-6 mb-4">
                  <h5 class="fw-bold mb-3">Detalles de la reserva</h5>
                  <div class="mb-3">
                    <label for="fecha" class="form-label"
                      >Fecha de reserva</label
                    >
                    <form:input
                      type="date"
                      class="form-control"
                      id="fecha"
                      path="fecha"
                      min="${fechaMin}"
                      max="${fechaMax}"
                      required="true" />
                  </div>
                  <div class="mb-3">
                    <label for="idFranja" class="form-label"
                      >Franja horaria</label
                    >
                    <form:select
                      class="form-select"
                      id="idFranja"
                      path="idFranja"
                      required="true">
                      <form:option value=""
                        >Selecciona una fecha primero</form:option
                      >
                    </form:select>
                  </div>
                  <div class="mb-3">
                    <label for="personas" class="form-label"
                      >Número de personas (máx 10)</label
                    >
                    <form:input
                      type="number"
                      class="form-control"
                      id="personas"
                      path="numeroPersonas"
                      min="1"
                      max="10"
                      required="true" />
                  </div>
                  <div class="mb-3">
                    <label for="idTipoMesa" class="form-label"
                      >Tipo de mesa</label
                    >
                    <form:select
                      class="form-select"
                      id="idTipoMesa"
                      path="idTipoMesa"
                      required="true">
                      <form:option value=""
                        >Selecciona tipo de mesa</form:option
                      >
                      <form:options
                        items="${tiposDeMesa}"
                        itemValue="idTipoMesa"
                        itemLabel="nombre" />
                    </form:select>
                  </div>
                </div>
              </div>
              <!-- Avisos y botón -->
              <div class="text-center mt-4">
                <!-- ... (avisos) ... -->
                <button type="submit" class="btn btn-success px-5">
                  Confirmar Reserva
                </button>
              </div>
            </form:form>
          </c:otherwise>
        </c:choose>
      </div>
    </main>

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
                <a
                  href="https://maps.app.goo.gl/gfmHHgNU4oSQq1CS7"
                  target="_blank"
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
      <div
        class="text-center text-white py-3 mt-4"
        style="background-color: #000">
        <p class="mb-0">
          &copy; 2025 Restaurante Siete Sopas. Todos los derechos reservados.
        </p>
      </div>
    </footer>

    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const fechaInput = document.getElementById("fecha");
        const franjaSelect = document.getElementById("idFranja");
        const tipoMesaSelect = document.getElementById("idTipoMesa");

        // Función para actualizar las franjas horarias
        function actualizarFranjas() {
          const fechaSeleccionada = fechaInput.value;
          if (!fechaSeleccionada) {
            franjaSelect.innerHTML =
              '<option value="">Selecciona una fecha primero</option>';
            tipoMesaSelect.innerHTML =
              '<option value="">Selecciona una franja primero</option>';
            return;
          }

          fetch(
            `${pageContext.request.contextPath}/api/disponibilidad?fecha=${fechaSeleccionada}`
          )
            .then((response) => response.json())
            .then((data) => {
              franjaSelect.innerHTML =
                '<option value="">Selecciona un horario</option>';
              data.forEach((franja) => {
                let optionText = franja.estaCasiLleno
                  ? `${franja.franjaHoraria} - (Ocupado +70%)`
                  : `${franja.franjaHoraria} - ${franja.mesasDisponibles} mesas disponibles`;

                const option = new Option(optionText, franja.idFranja);
                if (franja.mesasDisponibles <= 0) option.disabled = true;
                franjaSelect.appendChild(option);
              });
            });
        }

        // Función para actualizar los tipos de mesa
        function actualizarTiposDeMesa() {
          const fechaSeleccionada = fechaInput.value;
          const franjaSeleccionada = franjaSelect.value;

          if (!fechaSeleccionada || !franjaSeleccionada) {
            tipoMesaSelect.innerHTML =
              '<option value="">Selecciona una franja primero</option>';
            return;
          }

          fetch(
            `${pageContext.request.contextPath}/api/disponibilidad-tipos?fecha=${fechaSeleccionada}&idFranja=${franjaSeleccionada}`
          )
            .then((response) => response.json())
            .then((data) => {
              tipoMesaSelect.innerHTML =
                '<option value="">Selecciona tipo de mesa</option>';
              data.forEach((tipo) => {
                const optionText = `${tipo.nombreTipoMesa} (${tipo.mesasDisponibles} disponibles)`;
                const option = new Option(optionText, tipo.idTipoMesa);
                if (tipo.mesasDisponibles <= 0) option.disabled = true;
                tipoMesaSelect.appendChild(option);
              });
            });
        }

        fechaInput.addEventListener("change", actualizarFranjas);
        franjaSelect.addEventListener("change", actualizarTiposDeMesa);
      });
    </script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
