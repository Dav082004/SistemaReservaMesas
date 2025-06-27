<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Reservaciones - Siete Sopas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" />
    <link href="<c:url value='/css/styles.css'/>" rel="stylesheet" />
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
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/'/>">INICIO</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/menu'/>">MENU</a></li>
                        <li class="nav-item"><a class="nav-link active" href="<c:url value='/reservaciones'/>">RESERVACIONES</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/nosotros'/>">NOSOTROS</a></li>
                    </ul>
                    <c:choose>
                        <c:when test="${not empty sessionScope.usuario}">
                            <div class="d-flex align-items-center">
                                <span class="navbar-text me-3">Hola, <strong>${sessionScope.usuario.nombreCompleto}</strong></span>
                                <div class="dropdown">
                                    <a class="dropdown-toggle d-flex align-items-center text-decoration-none" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <img src="<c:url value='/images/user3.png'/>" alt="Ícono de usuario" style="width: 40px; height: 40px; border-radius: 50%;">
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li><a class="dropdown-item" href="<c:url value='/perfil'/>"><i class="bi bi-person-circle me-2"></i>Ver Perfil</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="#" onclick="document.getElementById('logoutForm').submit();"><i class="bi bi-box-arrow-right me-2"></i>Cerrar Sesión</a></li>
                                    </ul>
                                </div>
                                <form id="logoutForm" action="<c:url value='/logout'/>" method="post" style="display: none;"></form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a class="navbar-brand login-icon" href="<c:url value='/login'/>">
                                <img src="<c:url value='/images/user3.png'/>" alt="Ícono de usuario" />
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
            <c:choose>
                <c:when test="${empty sessionScope.usuario}">
                    <div class="row justify-content-center">
                        <div class="col-md-8 col-lg-6">
                            <div class="card shadow-sm">
                                <div class="card-body text-center p-5">
                                    <i class="bi bi-person-lock display-1 text-warning mb-3"></i>
                                    <h3 class="card-title mb-3">¡Necesitas estar logueado!</h3>
                                    <p class="card-text text-muted mb-4">Solo puedes hacer una reserva si estás logueado.</p>
                                    <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                                        <a href="<c:url value='/login'/>" class="btn btn-success btn-lg me-md-2"><i class="bi bi-box-arrow-in-right"></i> Iniciar Sesión</a>
                                        <a href="<c:url value='/register'/>" class="btn btn-outline-success btn-lg"><i class="bi bi-person-plus"></i> Crear Cuenta</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty successMessage}"><div class="alert alert-success">${successMessage}</div></c:if>
                    <c:if test="${not empty errorMessage}"><div class="alert alert-danger">${errorMessage}</div></c:if>
                    <form id="reservaForm" action="<c:url value='/reservaciones'/>" method="post">
                        <div class="row">
                            <div class="col-md-6 mb-4">
                                <h5 class="fw-bold mb-3">Datos del cliente</h5>
                                <div class="mb-3"><label class="form-label">Nombre completo</label><input type="text" class="form-control" value="${sessionScope.usuario.nombreCompleto}" readonly /></div>
                                <div class="mb-3"><label class="form-label">Teléfono</label><input type="tel" class="form-control" value="${sessionScope.usuario.telefono}" readonly /></div>
                                <div class="mb-3"><label class="form-label">Correo electrónico</label><input type="email" class="form-control" value="${sessionScope.usuario.correo}" readonly /></div>
                                <div class="mb-3"><label for="comentarios" class="form-label">Comentarios adicionales</label><textarea class="form-control" id="comentarios" name="comentarios" rows="3" placeholder="Ej. Celebración, silla para bebé, etc."></textarea></div>
                            </div>
                            <div class="col-md-6 mb-4">
                                <h5 class="fw-bold mb-3">Detalles de la reserva</h5>
                                <div class="mb-3"><label for="fecha" class="form-label">Fecha de reserva</label><input type="date" class="form-control" id="fecha" name="fecha" min="${fechaMin}" max="${fechaMax}" required /></div>
                                <div class="mb-3"><label for="idFranja" class="form-label">Franja horaria</label><select class="form-select" id="idFranja" name="idFranja" required><option value="">Selecciona una fecha primero</option></select></div>
                                <div class="mb-3"><label for="numeroPersonas" class="form-label">Número de personas (máx 10)</label><input type="number" class="form-control" id="numeroPersonas" name="numeroPersonas" min="1" max="10" required /></div>
                                <div class="mb-3"><label for="idTipoMesa" class="form-label">Tipo de mesa</label><select class="form-select" id="idTipoMesa" name="idTipoMesa" required><option value="">Selecciona un tipo de mesa</option><c:forEach var="tipoMesa" items="${tiposDeMesa}"><option value="${tipoMesa.idTipoMesa}">${tipoMesa.nombre}</option></c:forEach></select></div>
                            </div>
                        </div>
                        <div class="text-center mt-4"><button type="submit" class="btn btn-success px-5">Confirmar Reserva</button></div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <!-- Footer -->
    <footer style="background-color: #fffbeb" class="text-dark pt-5 pb-4 mt-auto">
        <div class="container text-center text-md-start">
            <div class="row gx-5">
                <div class="col-md-4 mb-4">
                    <h5 class="fw-bold">Contacto</h5>
                    <p>📍 Av. Ricardo Palma 205, Miraflores</p>
                    <p><a href="https://maps.app.goo.gl/gfmHHgNU4oSQq1CS7" target="_blank" style="color: #b52e2e; text-decoration: none;">Ver en Google Maps</a></p>
                </div>
                <div class="col-md-4 mb-4">
                    <h5 class="fw-bold">Nosotros</h5>
                    <p>Especializados en sopas tradicionales y reconfortantes, con ingredientes frescos y recetas caseras.</p>
                </div>
                <div class="col-md-4 mb-4">
                    <h5 class="fw-bold">Horario</h5>
                    <p>Lunes a Domingo: 9:00 AM - 1:00 AM</p>
                </div>
            </div>
        </div>
        <div class="text-center text-white py-3 mt-4" style="background-color: #000">
            <p class="mb-0">&copy; 2025 Restaurante Siete Sopas. Todos los derechos reservados.</p>
        </div>
    </footer>

    <!-- ***** SCRIPT FINAL Y ROBUSTO ***** -->
    <script>
    document.addEventListener("DOMContentLoaded", function () {
        const fechaInput = document.getElementById("fecha");
        const franjaSelect = document.getElementById("idFranja");
        if (!fechaInput) return;

        const handleDateChange = async () => {
            setTimeout(async () => {
                const fechaSeleccionada = fechaInput.value;
                if (!fechaSeleccionada) {
                    console.log("[Depuración] Fecha vacía, no se hace fetch");
                    franjaSelect.innerHTML = '<option value="">Selecciona una fecha primero</option>';
                    return;
                }
                console.log("[Depuración] Fecha seleccionada para fetch:", fechaSeleccionada);
                franjaSelect.innerHTML = '<option value="">Cargando...</option>';
                try {
                    const url = `/api/disponibilidad/franjas?fecha=${fechaSeleccionada}`;
                    console.log("Llamando a la URL definitiva:", url);
                    const response = await fetch(url);
                    if (!response.ok) {
                        throw new Error(`Error HTTP: ${response.status}`);
                    }
                    const data = await response.json();
                    console.log("Datos recibidos:", data);
                    franjaSelect.innerHTML = '<option value="">Selecciona un horario</option>';
                    if (data.length === 0) {
                        franjaSelect.innerHTML = '<option value="" disabled>No hay horarios disponibles</option>';
                        return;
                    }
                    data.forEach((franja) => {
                        // Solo mostrar el horario y la cantidad de mesas disponibles, sin mensajes especiales
                        let optionText = `${franja.franjaHoraria} (${franja.mesasDisponibles} mesas disponibles)`;
                        const option = new Option(optionText, franja.idFranja);
                        if (franja.mesasDisponibles <= 0) {
                            option.disabled = true;
                        }
                        franjaSelect.appendChild(option);
                    });
                } catch (error) {
                    console.error("Fetch error:", error);
                    franjaSelect.innerHTML = '<option value="">Error al cargar horarios</option>';
                }
            }, 10);
        };

        // Se asigna la función al evento "change".
        fechaInput.addEventListener("change", handleDateChange);
    });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
