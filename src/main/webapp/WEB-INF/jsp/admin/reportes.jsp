<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Reportes de Reservas</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" />
    <link rel="stylesheet" href="<c:url value='/css/reportes.css'/>" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                  class="nav-link"
                  href="<c:url value='/admin/reservas-anonimas'/>"
                  >RESERVAS ANÓNIMAS</a
                >
              </li>
              <li class="nav-item">
                <a
                  class="nav-link active"
                  href="<c:url value='/admin/reportes'/>"
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

    <!-- Report Section -->
    <section class="report-section container mt-4">
      <h2>Reportes de Reservas</h2>

      <!-- Filtros -->
      <div class="row mt-4 g-3">
        <div class="col-md-4">
          <label for="filtro-fecha" class="form-label">Rango de Fechas</label>
          <input type="date" class="form-control" id="fechaInicio" />
          <input type="date" class="form-control mt-1" id="fechaFin" />
        </div>
        <div class="col-md-3">
          <label class="form-label">Estado</label>
          <select class="form-select" id="filtro-estado">
            <option value="">Todos</option>
            <option value="CONFIRMADA">Confirmada</option>
            <option value="SE PRESENTÓ">Se Presentó</option>
            <option value="NO SE PRESENTÓ">No se Presentó</option>
          </select>
        </div>
        <div class="col-md-3">
          <label class="form-label">Tipo de Mesa</label>
          <select class="form-select" id="filtro-tipo">
            <option value="">Todos</option>
            <c:forEach var="tipo" items="${tiposDeMesa}">
              <option value="${tipo.idTipoMesa}">${tipo.nombre}</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-md-2 d-grid align-items-end">
          <button class="btn btn-success mt-3" onclick="filtrar()">
            Filtrar
          </button>
        </div>
      </div>

      <!-- Tabla -->
      <div class="table-responsive mt-4">
        <table class="table table-bordered table-striped align-middle">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Fecha</th>
              <th>Hora</th>
              <th>Tipo Mesa</th>
              <th>Estado</th>
              <th>Personas</th>
              <th>Comentarios</th>
            </tr>
          </thead>
          <tbody id="tabla-reservas">
            <c:forEach var="reserva" items="${reservas}">
              <tr>
                <td>${reserva.nombreCliente}</td>
                <td>${reserva.fechaReserva}</td>
                <td>${reserva.franja.franjaHoraria}</td>
                <td>${reserva.tipoMesa.nombre}</td>
                <td>
                  <c:choose>
                    <c:when test="${reserva.estado == 'CONFIRMADA'}">
                      <span class="badge bg-warning text-dark">Confirmada</span>
                    </c:when>
                    <c:when test="${reserva.estado == 'SE PRESENTÓ'}">
                      <span class="badge bg-success">Se Presentó</span>
                    </c:when>
                    <c:when test="${reserva.estado == 'NO SE PRESENTÓ'}">
                      <span class="badge bg-danger">No se Presentó</span>
                    </c:when>
                    <c:otherwise>
                      <span class="badge bg-secondary">${reserva.estado}</span>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td>${reserva.numeroPersonas}</td>
                <td>
                  ${reserva.comentarios != null ? reserva.comentarios : '-'}
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <!-- Export Buttons -->
      <div class="btn-export d-flex gap-2">
        <button class="btn btn-outline-success" onclick="exportarExcel()">
          <i class="bi bi-file-earmark-excel"></i> Exportar Excel
        </button>
        <button class="btn btn-outline-danger" onclick="exportarPDF()">
          <i class="bi bi-file-earmark-pdf"></i> Exportar PDF
        </button>
      </div>

      <!-- Gráficos -->
      <div class="row mt-5">
        <div class="col-md-6">
          <h5 class="text-center">Reservas por Estado</h5>
          <canvas id="graficoEstado"></canvas>
        </div>
        <div class="col-md-6">
          <h5 class="text-center">Reservas por Tipo de Mesa</h5>
          <canvas id="graficoMesa"></canvas>
        </div>
      </div>
    </section>
    <script>
      // Datos para gráficos desde el servidor
      const datosEstado = (
        <c:out value="${estadisticasEstado}" default="[]" escapeXml="false" />
      );
      const datosTipoMesa = (
        <c:out value="${estadisticasTipoMesa}" default="[]" escapeXml="false" />
      );

      // Gráfico de Estado
      const ctxEstado = document
        .getElementById("graficoEstado")
        .getContext("2d");
      new Chart(ctxEstado, {
        type: "pie",
        data: {
          labels: datosEstado.map((item) => item.estado) || [
            "Confirmada",
            "Se Presentó",
            "No se Presentó",
          ],
          datasets: [
            {
              label: "Cantidad",
              data: datosEstado.map((item) => item.cantidad) || [5, 10, 2],
              backgroundColor: ["#ffc107", "#198754", "#dc3545"],
            },
          ],
        },
      });

      // Gráfico de Tipo Mesa
      const ctxMesa = document.getElementById("graficoMesa").getContext("2d");
      new Chart(ctxMesa, {
        type: "bar",
        data: {
          labels: datosTipoMesa.map((item) => item.tipoMesa) || [
            "Mesa Común",
            "Terraza",
            "Mesa Privada",
          ],
          datasets: [
            {
              label: "Reservas",
              data: datosTipoMesa.map((item) => item.cantidad) || [4, 6, 3],
              backgroundColor: "#198754",
            },
          ],
        },
      });

      function filtrar() {
        const fechaInicio = document.getElementById("fechaInicio").value;
        const fechaFin = document.getElementById("fechaFin").value;
        const estado = document.getElementById("filtro-estado").value;
        const tipoMesa = document.getElementById("filtro-tipo").value;

        const params = new URLSearchParams();
        if (fechaInicio) params.append("fechaInicio", fechaInicio);
        if (fechaFin) params.append("fechaFin", fechaFin);
        if (estado) params.append("estado", estado);
        if (tipoMesa) params.append("tipoMesa", tipoMesa);

        window.location.href =
          '<c:url value="/admin/reportes"/>?' + params.toString();
      }

      function exportarExcel() {
        window.location.href =
          '<c:url value="/admin/reportes/exportar/excel"/>';
      }

      function exportarPDF() {
        window.location.href = '<c:url value="/admin/reportes/exportar/pdf"/>';
      }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
