-- Datos iniciales básicos - inserción simple

-- Tipos de Mesa (solo nombre y descripcion segun la entidad TipoMesa)
INSERT INTO TIPO_MESA (nombre, descripcion) VALUES 
('Mesa comun', 'Mesa estandar del restaurante'),
('Mesa en terraza', 'Mesa ubicada en la terraza con vista'),
('Mesa privada', 'Mesa en area privada para mayor intimidad'),
('Mesa cerca a ventana', 'Mesa con vista hacia el exterior');

-- Franjas Horarias (segun la entidad ConfiguracionFranja)
INSERT INTO CONFIGURACION_FRANJA (franja_horaria, capacidad_maxima, cantidad_mesas) VALUES 
('Desayuno 07:00-10:00', 50, 12),
('Almuerzo temprano 11:00-13:00', 40, 10),
('Almuerzo 13:00-15:00', 60, 12),
('Tarde 15:00-17:00', 30, 8),
('Cena temprana 17:00-19:00', 45, 10),
('Cena 19:00-21:00', 60, 12),
('Cena tardia 21:00-23:00', 35, 8);

-- Mesas (solo capacidad e idTipoMesa segun la entidad Mesa)
INSERT INTO MESA (capacidad, id_tipo_mesa) VALUES 
-- Mesas comunes
(4, 1),
(4, 1),
(2, 1),
(4, 1),
-- Mesas en terraza
(4, 2),
(6, 2),
(4, 2),
-- Mesas privadas
(6, 3),
(8, 3),
-- Mesas cerca a ventana
(2, 4),
(4, 4),
(4, 4);

-- Usuarios de prueba (segun la entidad Usuario)
INSERT INTO USUARIOS (nombre_completo, correo, telefono, usuario, contrasena, rol) VALUES 
('Admin Sistema', 'admin@7sopas.com', '1234567890', 'admin', 'admin123', 'ADMIN'),
('Usuario Prueba', 'user@7sopas.com', '0987654321', 'user', 'user123', 'USER');