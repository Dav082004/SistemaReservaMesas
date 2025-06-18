<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Gestión de Reservas Anónimas - Admin</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" />
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
                <a class="nav-link" href="<c:url value='/admin'/>">RESERVAS</a>
              </li>
              <li class="nav-item">
                <a
                  class="nav-link active"
                  href="<c:url value='/admin/reservas-anonimas'/>"
                  >RESERVAS ANÓNIMAS</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value='/admin/reportes'/>"
                  >REPORTES</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value='/admin/perfiladmin'/>"
                  >PERFIL</a
                >
              </li>
            </ul>
            <c:if test="${not empty sessionScope.usuario}">
              <div class="d-flex align-items-center">
                <span class="navbar-text me-3">
                  Admin: <strong>${sessionScope.usuario.username}</strong>
                </span>
                <form
                  action="<c:url value='/logout'/>"
                  method="post"
                  class="d-inline">
                  <button
                    class="btn btn-outline-danger btn-sm"
                    type="submit"
                    title="Cerrar sesión">
                    <i class="bi bi-box-arrow-right"></i>
                  </button>
                </form>
              </div>
            </c:if>
          </div>
        </div>
      </nav>
    </header>

    <!-- Gestión de Reservas Admin -->
    <div class="container mt-5 mb-5">
      <h2 class="text-success text-center mb-4 fw-bold">
        Gestión de Reservas Anónimas - Sede Miraflores
      </h2>

      <!-- Mensajes de éxito/error -->
      <c:if test="${not empty successMessage}">
        <div class="alert alert-success" role="alert">${successMessage}</div>
      </c:if>
      <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">${errorMessage}</div>
      </c:if>

      <form
        id="adminReservaForm"
        action="<c:url value='/admin/reservas-anonimas/crear'/>"
        method="post">
        <div class="row">
          <!-- Datos del cliente -->
          <div class="col-md-6 mb-4">
            <h5 class="fw-bold mb-3">Datos del cliente</h5>
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre completo</label>
              <input
                type="text"
                class="form-control"
                id="nombre"
                name="nombreCliente"
                placeholder="Nombre del cliente"
                required />
            </div>
            <div class="mb-3">
              <label for="telefono" class="form-label">Teléfono</label>
              <input
                type="tel"
                class="form-control"
                id="telefono"
                name="telefonoCliente"
                placeholder="Teléfono del cliente"
                required />
            </div>
            <div class="mb-3">
              <label for="correo" class="form-label">Correo electrónico</label>
              <input
                type="email"
                class="form-control"
                id="correo"
                name="correoCliente"
                placeholder="Correo del cliente" />
            </div>
          </div>

          <!-- Detalles de reserva -->
          <div class="col-md-6 mb-4">
            <h5 class="fw-bold mb-3">Detalles de la reserva</h5>
            <div class="mb-3">
              <label for="fecha" class="form-label">Fecha de reserva</label>
              <input
                type="date"
                class="form-control"
                id="fecha"
                name="fechaReserva"
                required />
            </div>
            <div class="mb-3">
              <label for="hora" class="form-label">Franja horaria</label>
              <select class="form-select" id="hora" name="idFranja" required>
                <option value="">Selecciona una franja</option>
                <c:forEach var="franja" items="${franjasHorarias}">
                  <option value="${franja.idFranja}">
                    ${franja.franjaHoraria}
                  </option>
                </c:forEach>
              </select>
            </div>
            <div class="mb-3">
              <label for="personas" class="form-label"
                >Número de personas (máx 20)</label
              >
              <input
                type="number"
                class="form-control"
                id="personas"
                name="numeroPersonas"
                min="1"
                max="20"
                required />
            </div>
            <div class="mb-3">
              <label for="tipoMesa" class="form-label">Tipo de mesa</label>
              <select
                class="form-select"
                id="tipoMesa"
                name="idTipoMesa"
                required>
                <option value="">Selecciona tipo de mesa</option>
                <c:forEach var="tipo" items="${tiposDeMesa}">
                  <option value="${tipo.idTipoMesa}">${tipo.nombre}</option>
                </c:forEach>
              </select>
            </div>
            <div class="mb-3">
              <label for="comentarios" class="form-label">Comentarios</label>
              <textarea
                class="form-control"
                id="comentarios"
                name="comentarios"
                rows="3"
                placeholder="Comentarios adicionales (opcional)"></textarea>
            </div>
          </div>
        </div>

        <div class="text-center mt-4">
          <button type="submit" class="btn btn-success px-5">
            Registrar Reserva Anónima
          </button>
        </div>
      </form>
    </div>

    <script>
      // JavaScript para validaciones y funcionalidades adicionales
      const fechaInput = document.getElementById("fecha");
      const personasInput = document.getElementById("personas");
      const adminReservaForm = document.getElementById("adminReservaForm");

      // Configurar fecha mínima (hoy) y máxima (fin del año)
      window.addEventListener("DOMContentLoaded", () => {
        const hoy = new Date();
        const min = hoy.toISOString().split("T")[0];
        const max = `${hoy.getFullYear()}-12-31`;
        fechaInput.setAttribute("min", min);
        fechaInput.setAttribute("max", max);
      });

      // Validación del formulario
      adminReservaForm.addEventListener("submit", function (e) {
        const personas = parseInt(personasInput.value);

        if (isNaN(personas) || personas < 1 || personas > 20) {
          e.preventDefault();
          alert("❌ El número de personas debe estar entre 1 y 20.");
          return;
        }

        // Validación adicional si es necesaria
        const fecha = fechaInput.value;
        if (!fecha) {
          e.preventDefault();
          alert("❌ Por favor selecciona una fecha para la reserva.");
          return;
        }
      });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
