<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Intranet - Reservas</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet" />
    <link href="<c:url value='/css/styles.css'/>" rel="stylesheet" />
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

    <div class="container mt-5">
      <h2 class="text-success text-center mb-4 fw-bold">Gestión de Reservas</h2>

      <div class="row mb-3">
        <div class="col-md-4 offset-md-4">
          <label for="filtroFecha" class="form-label fw-bold"
            >Filtrar por Fecha:</label
          >
          <input
            type="date"
            class="form-control"
            id="filtroFecha"
            value="${fechaSeleccionada}" />
        </div>
      </div>

      <div class="row justify-content-center mb-4 g-2">
        <div class="col-md-3">
          <label for="filtroNombre" class="form-label">Nombre Cliente</label>
          <input
            type="text"
            class="form-control"
            id="filtroNombre"
            placeholder="Buscar por nombre..." />
        </div>
        <div class="col-md-2">
          <label for="filtroTipoMesa" class="form-label">Tipo Mesa</label>
          <select class="form-select" id="filtroTipoMesa">
            <option value="">Todos</option>
            <c:forEach var="tipo" items="${tiposDeMesa}">
              <option value="${tipo.idTipoMesa}">${tipo.nombre}</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-md-2">
          <label for="filtroHora" class="form-label">Hora</label>
          <select class="form-select" id="filtroHora">
            <option value="">Todas</option>
            <c:forEach var="franja" items="${franjasHorarias}">
              <option value="${franja.idFranja}">
                ${franja.franjaHoraria}
              </option>
            </c:forEach>
          </select>
        </div>
        <div class="col-md-2">
          <label for="filtroEstado" class="form-label">Estado</label>
          <select class="form-select" id="filtroEstado">
            <option value="">Todos</option>
            <option value="CONFIRMADA">Confirmada</option>
            <option value="SE PRESENTÓ">Se Presentó</option>
            <option value="NO SE PRESENTÓ">No se Presentó</option>
          </select>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-bordered table-hover">
          <thead class="table-success">
            <tr>
              <th>Nombre</th>
              <th>Teléfono</th>
              <th>Correo</th>
              <th>Personas</th>
              <th>Tipo Mesa</th>
              <th>Hora</th>
              <th>Comentarios</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody id="tablaReservas">
            <c:forEach var="reserva" items="${reservas}">
              <tr id="reserva-${reserva.idReserva}">
                <td>${reserva.nombreCliente}</td>
                <td>${reserva.telefonoCliente}</td>
                <td>${reserva.correoCliente}</td>
                <td>${reserva.numeroPersonas}</td>
                <td>${reserva.tipoMesa.nombre}</td>
                <td>${reserva.franja.franjaHoraria}</td>
                <td>${reserva.comentarios}</td>
                <td class="fw-bold text-capitalize">${reserva.estado}</td>
                <td>
                  <button
                    class="btn btn-sm btn-success me-1"
                    onclick="actualizarEstado('${reserva.idReserva}', 'SE PRESENTÓ')">
                    <i class="bi bi-check-circle"></i> Se presentó
                  </button>
                  <button
                    class="btn btn-sm btn-danger"
                    onclick="actualizarEstado('${reserva.idReserva}', 'NO SE PRESENTÓ')">
                    <i class="bi bi-x-circle"></i> No se presentó
                  </button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <script>
      // Función para actualizar el estado de una reserva llamando a la API
      function actualizarEstado(idReserva, nuevoEstado) {
        const formData = new FormData();
        formData.append("idReserva", idReserva);
        formData.append("estado", nuevoEstado);

        fetch("<c:url value='/admin/api/reservas/actualizar-estado'/>", {
          method: "POST",
          body: formData,
        })
          .then((response) => response.json())
          .then((data) => {
            if (data.status === "success") {
              // Actualizar la tabla sin recargar la página
              const fechaActual = document.getElementById("filtroFecha").value;
              cargarReservas(fechaActual);
              alert(data.message); // O mostrar una notificación más elegante
            } else {
              alert("Error: " + data.message);
            }
          })
          .catch((error) => console.error("Error:", error));
      }

      const filtros = {
        fecha: document.getElementById("filtroFecha"),
        nombre: document.getElementById("filtroNombre"),
        tipoMesa: document.getElementById("filtroTipoMesa"),
        hora: document.getElementById("filtroHora"),
        estado: document.getElementById("filtroEstado"),
      };

      function cargarReservas() {
        // Construye la URL con los parámetros de los filtros
        const params = new URLSearchParams({
          fecha: filtros.fecha.value,
          nombre: filtros.nombre.value,
          idTipoMesa: filtros.tipoMesa.value,
          idFranja: filtros.hora.value,
          estado: filtros.estado.value,
        });

        // Elimina parámetros vacíos para no enviar ?nombre=&idTipoMesa= etc.
        for (const [key, value] of [...params.entries()]) {
          if (!value) {
            params.delete(key);
          }
        }

        fetch(`<c:url value='/admin/api/reservas'/>?${params.toString()}`)
          .then((response) => response.json())
          .then((data) => {
            const tbody = document.getElementById("tablaReservas");
            tbody.innerHTML = "";
            if (data.length === 0) {
              tbody.innerHTML =
                "<tr><td colspan='9' class='text-center'>No se encontraron reservas con los filtros aplicados.</td></tr>";
              return;
            }
            data.forEach((reserva) => {
              const fila = document.createElement("tr");
              fila.id = "reserva-" + reserva.idReserva;
              fila.innerHTML = `
                            <td>${reserva.nombreCliente}</td>
                            <td>${reserva.telefonoCliente}</td>
                            <td>${reserva.correoCliente}</td>
                            <td>${reserva.numeroPersonas}</td>
                            <td>${reserva.tipoMesa.nombre}</td>
                            <td>${reserva.franja.franjaHoraria}</td>
                            <td>${reserva.comentarios || "-"}</td>
                            <td class="fw-bold text-capitalize">${
                              reserva.estado
                            }</td>
                            <td>
                                <button class="btn btn-sm btn-success me-1" onclick="actualizarEstado(${
                                  reserva.idReserva
                                }, 'SE PRESENTÓ')">
                                    <i class="bi bi-check-circle"></i> Se presentó
                                </button>
                                <button class="btn btn-sm btn-danger" onclick="actualizarEstado(${
                                  reserva.idReserva
                                }, 'NO SE PRESENTÓ')">
                                    <i class="bi bi-x-circle"></i> No se presentó
                                </button>
                            </td>
                        `;
              tbody.appendChild(fila);
            });
          });
      }

      // Asigna el evento a todos los filtros para que recarguen la tabla al cambiar
      Object.values(filtros).forEach((input) => {
        input.addEventListener("change", cargarReservas);
      });
      filtros.nombre.addEventListener("keyup", cargarReservas); // Para el campo de texto
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
