-- Inserta datos iniciales en la tabla Usuarios

-- IMPORTANTE: En una aplicación real, las contraseñas DEBEN ser hasheadas (ej: con BCrypt).
-- Por ahora, las guardamos como texto plano solo para fines de desarrollo y prueba.

-- Usuario con rol de Administrador
INSERT INTO Usuarios (nombreCompleto, correo, telefono, usuario, contrasena, rol) VALUES
('Admin Principal', 'admin@sietesopas.com', '999888777', 'admin', 'admin123', 'ADMINISTRADOR');

-- Usuario con rol de Cliente/Usuario
INSERT INTO Usuarios (nombreCompleto, correo, telefono, usuario, contrasena, rol) VALUES
('Carlos Velez', 'carlos.velez@email.com', '111222333', 'carlosv', 'usuario123', 'USUARIO');

-- Inserta datos iniciales en la tabla TipoMesa (Regla #7)
INSERT INTO TipoMesa (nombre, descripcion) VALUES
('Mesa Común', 'Mesas estándar en el salón principal.'),
('Mesa en Terraza', 'Disfruta del aire libre en nuestra cómoda terraza.'),
('Mesa Privada', 'Un espacio reservado para ocasiones especiales.'),
('Mesa Cerca a la Ventana', 'Disfruta de la vista mientras comes.');


-- Inserta datos iniciales en la tabla ConfiguracionFranja (Reglas #4 y #5)
-- idFranja, franjaHoraria, capacidadMaxima (personas), cantidadMesas
INSERT INTO ConfiguracionFranja (franjaHoraria, capacidadMaxima, cantidadMesas) VALUES
('10:00 - 12:00', 100, 20),
('12:00 - 14:00', 100, 20),
('14:00 - 16:00', 100, 20),
('16:00 - 18:00', 100, 20),
('18:00 - 20:00', 100, 20),
('20:00 - 22:00', 100, 20),
('22:00 - 00:00', 100, 20);

-- Inserta 20 mesas físicas (5 de cada tipo, como indica la Regla #7)
-- idMesa 1-5 son Común (idTipoMesa=1)
INSERT INTO Mesa (idTipoMesa, capacidad) VALUES (1, 5), (1, 5), (1, 5), (1, 5), (1, 5);
-- idMesa 6-10 son Terraza (idTipoMesa=2)
INSERT INTO Mesa (idTipoMesa, capacidad) VALUES (2, 5), (2, 5), (2, 5), (2, 5), (2, 5);
-- idMesa 11-15 son Privada (idTipoMesa=3)
INSERT INTO Mesa (idTipoMesa, capacidad) VALUES (3, 5), (3, 5), (3, 5), (3, 5), (3, 5);
-- idMesa 16-20 son Cerca a la ventana (idTipoMesa=4)
INSERT INTO Mesa (idTipoMesa, capacidad) VALUES (4, 5), (4, 5), (4, 5), (4, 5), (4, 5);
