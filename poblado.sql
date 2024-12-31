-- Poblado de datos para la tabla cliente
INSERT INTO cliente (dni, nombre, apellido) VALUES
('72517138','Juan','Gomez'),
('49082615','Maria','Fernandez'),
('82104567','Carlos','Rodriguez'),
('36519824','Ana','Martinez'),
('60793281','Luis','Perez'),
('18274390','Sofia','Lopez'),
('95471032','Pablo','Sanchez'),
('30928456','Isabel','Garcia'),
('67351842','Jorge','Ruiz'),
('51829763','Valeria','Jimenez'),
('87654321','Veronica','Diaz'),
('13579246','Diego','Hernandez'),
('65432109','Patricia','Alvarez'),
('98765432','Ricardo','Moreno'),
('45678901','Beatriz','Castro'),
('24681357','Alejandro','Ortega'),
('57902468','Laura','Navarro'),
('31467289','Roberto','Molina'),
('20398176','Carmen','Ramos'),
('89012345','Fernando','Gutierrez');


-- Poblado de datos para la tabla habitacion
INSERT INTO habitacion (numero, numero_camas, disponible, ventana) VALUES
(200,3,true,true),
(201,2,true,false),
(202,1,true,false),
(203,2,true,true),
(204,4,true,true),
(205,1,true,false),
(206,3,true,true),
(207,2,true,false),
(208,1,true,false),
(209,2,true,true),
(210,2,true,true),
(211,3,true,false),
(212,1,true,false),
(213,2,true,true),
(214,4,true,true),
(215,1,true,false),
(216,3,true,true),
(217,2,true,false),
(218,1,true,false),
(219,2,true,true);

-- Poblado de datos para la tabla empleado
INSERT INTO empleado (dni,nombre, apellido, username, clave) VALUES
('75465361','Nilton','Ramos Encarnación','admin', '123'),
('74638291','José','Vásquez Ramos','josev', '456'),
('74802462','Diego','Panta Piscoche','diegop', '789'),
('73546281','Alex','Alcantara Zuñiga','alexA', '1234'),
('73543452','Emmanuel','Rojas Jimenez','rojas', '12345');


-- Poblado de datos para la tabla producto
INSERT INTO producto (nombre, stock, precio_venta, precio_compra, categoria) VALUES
('Cepillo',100,4.50,3.00,'USO_PERSONAL'),
('Agua Cielo',100,1.50,0.75,'ALIMENTOS'),
('Jabón',100,4.00,2.50,'USO_PERSONAL'),
('Pan Integral',100,2.75,1.50,'ALIMENTOS'),
('Champú',100,6.50,4.00,'USO_PERSONAL'),
('Yogurt Natural',100,2.25,1.20,'ALIMENTOS'),
('Crema Hidratante',100,8.75,5.50,'USO_PERSONAL'),
('Huevos',100,0.60,0.30,'ALIMENTOS'),
('Pasta Dental',100,4.25,2.75,'USO_PERSONAL'),
('Frutas Varias',100,6.00,3.50,'ALIMENTOS'),
('Loción Corporal',100,9.75,6.50,'USO_PERSONAL'),
('Sopa Instantánea',100,1.50,0.80,'ALIMENTOS'),
('Cepillo de Dientes',100,3.75,2.25,'USO_PERSONAL'),
('Galletas Integrales',100,2.25,1.20,'ALIMENTOS'),
('Acondicionador',100,7.00,4.25,'USO_PERSONAL'),
('Leche Descremada',100,1.75,1.00,'ALIMENTOS'),
('Desodorante',100,6.00,3.75,'USO_PERSONAL'),
('Arroz Integral',100,1.80,0.90,'ALIMENTOS'),
('Toallas de Papel',100,4.00,2.50,'USO_PERSONAL'),
('Yogurt de Frutas',100,2.75,1.50,'ALIMENTOS');


-- Poblado de datos para la tabla proveedor
INSERT INTO proveedor (ruc, razon_social) VALUES
('12345678901','Proveedor A'),
('23456789012','Proveedor B'),
('34567890123','Proveedor C'),
('45678901234','Proveedor D'),
('56789012345','Proveedor E');

INSERT INTO registro_reserva (empleado_id, fecha, turno, liquidacion) VALUES
(1, '2024-01-30', 'MANANA', 150.00),
(2, '2024-01-30', 'TARDE', 200.00),
(3, '2024-01-30', 'NOCHE', 70.00),
(4, '2024-01-31', 'MANANA', 180.00),
(5, '2024-01-31', 'TARDE', 70.00),
(1, '2024-01-31', 'NOCHE', 160.00),
(3, '2024-02-01', 'MANANA', 70.00),
(4, '2024-02-01', 'TARDE', 190.00),
(1, '2024-02-01', 'NOCHE', 70.00);

-- Poblado de datos para la tabla registro_compra

-- Poblado de datos para la tabla registro_venta

-- Poblado de datos para la tabla venta

-- Poblado de datos para la tabla compra

-- Poblado de datos para la tabla linea_venta

-- Poblado de datos para la tabla linea_compra

-- Poblado de datos para la tabla reserva
INSERT INTO reserva (registro_reserva_id, cliente_id, habitacion_id, numero_huespedes, estado_reserva, hora_ingreso, hora_salida, hora_reservadas, costo_efectivo, costo_yape) VALUES
(1, 1, 1, 1, 'PENDIENTE', '06:00:00', '09:00:00', '3', NULL, NULL),
(2, 2, 2, 1, 'PENDIENTE', '12:05:00', '12:05:00', '24', NULL, NULL),
(3, 3, 3, 2, 'PENDIENTE', '18:00:00', '21:00:00', '3', NULL, NULL),
(4, 4, 4, 1, 'PENDIENTE', '08:00:00', '11:00:00', '3', NULL, NULL),
(5, 5, 5, 2, 'PENDIENTE', '15:00:00', '18:00:00', '3', NULL, NULL),
(5, 11, 11, 2, 'PENDIENTE', '17:00:00', '17:00:00', '24', NULL, NULL),
(6, 6, 6, 3, 'PENDIENTE', '19:00:00', '22:00:00', '3', NULL, NULL),
(7, 7, 7, 1, 'PENDIENTE', '09:00:00', '09:00:00', '24', NULL, NULL),
(8, 8, 8, 1, 'PENDIENTE', '15:00:00', '18:00:00', '3', NULL, NULL),
(8, 16, 16, 2, 'PENDIENTE', '16:00:00', '19:00:00', '3', NULL, NULL),
(8, 17, 17, 2, 'PENDIENTE', '14:00:00', '17:00:00', '3', NULL, NULL),
(8, 18, 18, 1, 'PENDIENTE', '14:00:00', '17:00:00', '3', NULL, NULL),
(9, 16, 16, 2, 'PENDIENTE', '18:00:00', '21:00:00', '3', NULL, NULL),
(9, 17, 17, 2, 'PENDIENTE', '21:00:00', '00:00:00', '3', NULL, NULL),
(9, 18, 18, 1, 'PENDIENTE', '19:00:00', '22:00:00', '3', NULL, NULL);


