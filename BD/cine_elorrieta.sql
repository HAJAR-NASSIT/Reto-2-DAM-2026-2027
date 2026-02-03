DROP DATABASE IF EXISTS cine_elorrieta;
CREATE DATABASE cine_elorrieta
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE cine_elorrieta;

CREATE TABLE genero (
  id_genero INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nombre_genero VARCHAR(50) NOT NULL,
  CONSTRAINT uq_genero_nombre UNIQUE (nombre_genero)
);

CREATE TABLE pelicula (
  id_pelicula INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  id_genero INT UNSIGNED NOT NULL,
  titulo VARCHAR(100) NOT NULL,
  duracion INT NOT NULL,
  idioma VARCHAR(30) NOT NULL,
  descripcion TEXT NOT NULL,
  imagen VARCHAR(100) NOT NULL,
  CONSTRAINT chk_duracion CHECK (duracion > 0),
  FOREIGN KEY (id_genero) REFERENCES genero(id_genero)
);

CREATE TABLE sala (
  id_sala INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  capacidad INT NOT NULL,
  CONSTRAINT uq_sala_nombre UNIQUE (nombre),
  CONSTRAINT chk_capacidad CHECK (capacidad > 0)
);

CREATE TABLE sesion (
  id_sesion INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  id_pelicula INT UNSIGNED NOT NULL,
  id_sala INT UNSIGNED NOT NULL,
  fecha DATE NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fin TIME NOT NULL,
  CONSTRAINT chk_hora_fin CHECK (hora_fin > hora_inicio),
  precio DECIMAL(6,2) NOT NULL,
  CONSTRAINT chk_precio_sesion CHECK (precio >= 0),
  FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula),
  FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

CREATE TABLE cliente (
  DNI CHAR(9) PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(256) NOT NULL
);

CREATE TABLE compra (
  id_compra INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  DNI CHAR(9) NOT NULL,
  fecha_compra DATE NOT NULL,
  hora_compra TIME NOT NULL,
  precio_total DECIMAL(8,2) NOT NULL DEFAULT 0.00,
  descuento_total DECIMAL(6,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT chk_precio_total CHECK (precio_total >= 0),
  CONSTRAINT chk_descuento_total CHECK (descuento_total >= 0),
  tipo_compra ENUM('Web','directo') NOT NULL,
  FOREIGN KEY (DNI) REFERENCES cliente(DNI)
);

CREATE TABLE entrada (
  id_entrada INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  id_sesion INT UNSIGNED NOT NULL,
  id_compra INT UNSIGNED NOT NULL,
  importe DECIMAL(6,2) NOT NULL,
  CONSTRAINT chk_importe CHECK (importe >= 0),
  FOREIGN KEY (id_sesion) REFERENCES sesion(id_sesion) ON UPDATE CASCADE,
  FOREIGN KEY (id_compra) REFERENCES compra(id_compra) ON DELETE CASCADE
);

-- =========================
-- INSERTS
-- =========================

INSERT INTO genero (nombre_genero) VALUES
('Accion'),
('Comedia'),
('Drama'),
('Terror'),
('Animacion');

INSERT INTO pelicula (id_genero, titulo, duracion, idioma, descripcion, imagen) VALUES
(1, 'Mision Imposible', 120, 'Español', 'Película de acción con misiones imposibles.', '1.jpg'),
(2, 'La Risa Infinita', 95, 'Español', 'Comedia divertida para toda la familia.', '2.jpg'),
(3, 'Lagrimas de Abril', 110, 'Español', 'Drama emotivo sobre la vida.', '3.jpg'),
(4, 'Noche Oscura', 100, 'Inglés', 'Película de terror que te pondrá los pelos de punta.', '4.jpg'),
(5, 'El Viaje de Luna', 90, 'Español', 'Película animada para niños.', '5.jpg'),
(1, 'Rápido y Furioso 9', 130, 'Español', 'Carreras de autos y acción sin límites.', '6.jpg'),
(2, 'El Gran Showman', 105, 'Español', 'Comedia musical que te hará sonreír.', '7.jpg'),
(3, 'El Secreto ', 125, 'Español', 'Un drama de misterio y amor.', '8.jpg'),
(4, 'El Conjuro', 112, 'Inglés', 'Historia basada en hechos paranormales.', '9.jpg'),
(5, 'Coco', 105, 'Español', 'Aventura animada sobre la familia y la música.', '10.jpg'),
(1, 'John Wick', 110, 'Inglés', 'Un exasesino vuelve a la acción por venganza.', '11.jpg'),
(2, 'La Casa de Papel', 100, 'Español', 'Comedia de crimen con un toque divertido.', '12.jpg'),
(3, 'La La Land', 128, 'Inglés', 'Historia de amor y sueños en Los Ángeles.', '13.jpg'),
(4, 'It', 135, 'Inglés', 'Payaso terrorífico aterroriza a un pueblo.', '14.jpg'),
(5, 'Frozen 2', 103, 'Español', 'Aventura mágica de Elsa y Anna.', '15.jpg');

INSERT INTO sala (nombre, capacidad) VALUES
('Sala 1', 100),
('Sala 2', 80),
('Sala 3', 2);

INSERT INTO sesion (id_pelicula, id_sala, fecha, hora_inicio, hora_fin, precio) VALUES
(1,1,'2026-06-18','16:00:00','18:00:00',5.50),
(1,2,'2026-06-18','18:30:00','20:30:00',5.50),
(1,3,'2026-06-18','21:00:00','23:00:00',6.00),
(1,1,'2026-06-19','16:30:00','18:30:00',5.50),
(1,2,'2026-06-19','19:00:00','21:00:00',5.50),
(1,3,'2026-06-19','21:30:00','23:30:00',6.00),
(1,1,'2026-06-20','17:00:00','19:00:00',5.50),
(1,2,'2026-06-20','19:30:00','21:30:00',5.50),
(1,3,'2026-06-20','22:00:00','23:59:00',6.00),
(1,1,'2026-06-21','16:00:00','18:00:00',5.50),

(2,1,'2026-06-18','15:30:00','17:05:00',4.50),
(2,2,'2026-06-18','17:30:00','19:05:00',4.50),
(2,3,'2026-06-18','19:30:00','21:05:00',5.00),
(2,1,'2026-06-19','16:00:00','17:35:00',4.50),
(2,2,'2026-06-19','18:00:00','19:35:00',4.50),
(2,3,'2026-06-19','20:00:00','21:35:00',5.00),
(2,1,'2026-06-20','16:30:00','18:05:00',4.50),
(2,2,'2026-06-20','18:30:00','20:05:00',4.50),
(2,3,'2026-06-20','20:30:00','22:05:00',5.00),
(2,1,'2026-06-21','17:00:00','18:35:00',4.50),

(3,1,'2026-06-18','16:00:00','17:50:00',6.00),
(3,2,'2026-06-18','18:00:00','19:50:00',6.00),
(3,3,'2026-06-18','20:00:00','21:50:00',6.50),
(3,1,'2026-06-19','16:30:00','18:20:00',6.00),
(3,2,'2026-06-19','18:30:00','20:20:00',6.00),
(3,3,'2026-06-19','20:30:00','22:20:00',6.50),
(3,1,'2026-06-20','17:00:00','18:50:00',6.00),
(3,2,'2026-06-20','19:00:00','20:50:00',6.00),
(3,3,'2026-06-20','21:00:00','22:50:00',6.50),
(3,1,'2026-06-21','16:00:00','17:50:00',6.00),

(4,1,'2026-06-18','17:00:00','18:40:00',6.50),
(4,2,'2026-06-18','19:00:00','20:40:00',6.50),
(4,3,'2026-06-18','21:00:00','22:40:00',7.00),
(4,1,'2026-06-19','16:30:00','18:10:00',6.50),
(4,2,'2026-06-19','18:30:00','20:10:00',6.50),
(4,3,'2026-06-19','20:30:00','22:10:00',7.00),
(4,1,'2026-06-20','17:00:00','18:40:00',6.50),
(4,2,'2026-06-20','19:00:00','20:40:00',6.50),
(4,3,'2026-06-20','21:00:00','22:40:00',7.00),
(4,1,'2026-06-21','16:00:00','17:40:00',6.50),

(5,1,'2026-06-18','15:30:00','17:00:00',5.00),
(5,2,'2026-06-18','17:15:00','18:45:00',5.00),
(5,3,'2026-06-18','19:00:00','20:30:00',5.50),
(5,1,'2026-06-19','16:00:00','17:30:00',5.00),
(5,2,'2026-06-19','17:45:00','19:15:00',5.00),
(5,3,'2026-06-19','19:30:00','21:00:00',5.50),
(5,1,'2026-06-20','16:30:00','18:00:00',5.00),
(5,2,'2026-06-20','18:15:00','19:45:00',5.00),
(5,3,'2026-06-20','20:00:00','21:30:00',5.50),
(5,1,'2026-06-21','15:30:00','17:00:00',5.00),

(6,1,'2026-06-18','16:00:00','18:10:00',6.00),
(6,2,'2026-06-18','18:30:00','20:40:00',6.00),
(6,3,'2026-06-18','21:00:00','23:10:00',6.50),
(6,1,'2026-06-19','16:30:00','18:40:00',6.00),
(6,2,'2026-06-19','19:00:00','21:10:00',6.00),
(6,3,'2026-06-19','21:30:00','23:40:00',6.50),
(6,1,'2026-06-20','17:00:00','19:10:00',6.00),
(6,2,'2026-06-20','19:30:00','21:40:00',6.00),
(6,3,'2026-06-20','22:00:00','23:59:00',6.50),
(6,1,'2026-06-21','16:00:00','18:10:00',6.00),

(7,1,'2026-06-18','15:30:00','17:15:00',5.50),
(7,2,'2026-06-18','17:30:00','19:15:00',5.50),
(7,3,'2026-06-18','19:30:00','21:15:00',6.00),
(7,1,'2026-06-19','16:00:00','17:45:00',5.50),
(7,2,'2026-06-19','18:00:00','19:45:00',5.50),
(7,3,'2026-06-19','20:00:00','21:45:00',6.00),
(7,1,'2026-06-20','16:30:00','18:15:00',5.50),
(7,2,'2026-06-20','18:30:00','20:15:00',5.50),
(7,3,'2026-06-20','20:30:00','22:15:00',6.00),
(7,1,'2026-06-21','15:30:00','17:15:00',5.50),

(8,1,'2026-06-18','16:00:00','18:05:00',6.50),
(8,2,'2026-06-18','18:30:00','20:35:00',6.50),
(8,3,'2026-06-18','21:00:00','23:05:00',7.00),
(8,1,'2026-06-19','16:30:00','18:35:00',6.50),
(8,2,'2026-06-19','19:00:00','21:05:00',6.50),
(8,3,'2026-06-19','21:30:00','23:35:00',7.00),
(8,1,'2026-06-20','17:00:00','19:05:00',6.50),
(8,2,'2026-06-20','19:30:00','21:35:00',6.50),
(8,3,'2026-06-20','22:00:00','23:59:00',7.00),
(8,1,'2026-06-21','16:00:00','18:05:00',6.50),

(9,1,'2026-06-18','17:00:00','18:50:00',6.50),
(9,2,'2026-06-18','19:00:00','20:50:00',6.50),
(9,3,'2026-06-18','21:00:00','22:50:00',7.00),
(9,1,'2026-06-19','16:30:00','18:20:00',6.50),
(9,2,'2026-06-19','18:30:00','20:20:00',6.50),
(9,3,'2026-06-19','20:30:00','22:20:00',7.00),
(9,1,'2026-06-20','17:00:00','18:50:00',6.50),
(9,2,'2026-06-20','19:00:00','20:50:00',6.50),
(9,3,'2026-06-20','21:00:00','22:50:00',7.00),
(9,1,'2026-06-21','16:00:00','17:50:00',6.50),

(10,1,'2026-06-18','15:30:00','17:15:00',5.50),
(10,2,'2026-06-18','17:30:00','19:15:00',5.50),
(10,3,'2026-06-18','19:30:00','21:15:00',6.00),
(10,1,'2026-06-19','16:00:00','17:45:00',5.50),
(10,2,'2026-06-19','18:00:00','19:45:00',5.50),
(10,3,'2026-06-19','20:00:00','21:45:00',6.00),
(10,1,'2026-06-20','16:30:00','18:15:00',5.50),
(10,2,'2026-06-20','18:30:00','20:15:00',5.50),
(10,3,'2026-06-20','20:30:00','22:15:00',6.00),
(10,1,'2026-06-21','15:30:00','17:15:00',5.50);

INSERT INTO sesion (id_pelicula, id_sala, fecha, hora_inicio, hora_fin, precio) VALUES
(11, 2, '2026-06-21', '18:00:00', '20:00:00', 6.00),
(12, 2, '2026-06-21', '20:15:00', '22:15:00', 5.50),
(13, 1, '2026-06-21', '18:30:00', '20:40:00', 6.50),
(14, 1, '2026-06-21', '21:00:00', '23:20:00', 7.00),
(15, 2, '2026-06-21', '16:00:00', '17:50:00', 5.50);

INSERT INTO cliente (DNI, nombre, apellido, email, password) VALUES
('00000000T', 'Marta','Lopez', 'c0@test.com', SHA2('12345678',256)),
('00000001R', 'Sophie','Sophe','c1@test.com', SHA2('12345678',256)),
('00000002W', 'Juan','Lopez', 'c2@test.com', SHA2('12345678',256)),
('00000003A', 'Josi','Mari', 'c3@test.com', SHA2('12345678',256)),
('00000004G', 'Mariano','Lopez', 'c4@test.com', SHA2('12345678',256)),
('00000005M', 'Teresa','Sabado', 'c5@test.com', SHA2('12345678',256)),
('00000006Y', 'Hanae','El Has', 'c6@test.com', SHA2('12345678',256)),
('00000007F', 'Hajar','Hosni','c7@test.com', SHA2('12345678',256)),
('00000008P', 'Mohamed','Awragh', 'c8@test.com', SHA2('12345678',256)),
('00000009D', 'Mariam','Maria','c9@test.com', SHA2('12345678',256)),
('12345678Z', 'Juan','Perez', 'juan@example.com', SHA2('12345678',256)),
('87654321X', 'Maria','Lopez', 'maria@example.com', SHA2('12345678',256)),
('22222222R', 'Laura','Martinez', 'laura@example.com', SHA2('12345678',256));


INSERT INTO compra (DNI, fecha_compra, hora_compra, precio_total, descuento_total, tipo_compra) VALUES
('00000000T', '2026-06-18', '10:00:00', 22.50, 1.50, 'Web'),
('00000001R', '2026-06-18', '10:10:00',  6.50, 0.00, 'directo'),
('00000002W', '2026-06-18', '10:20:00', 26.50, 3.00, 'Web'),
('00000003A', '2026-06-18', '10:30:00', 14.00, 0.00, 'directo'),
('00000004G', '2026-06-18', '10:40:00', 26.00, 2.00, 'Web'),
('00000005M', '2026-06-18', '10:50:00',  8.00, 0.00, 'directo'),
('00000006Y', '2026-06-18', '11:00:00', 17.55, 1.95, 'Web'),
('00000007F', '2026-06-18', '11:10:00', 11.00, 0.00, 'directo'),
('00000008P', '2026-06-18', '11:20:00', 34.80, 4.20, 'Web'),
('00000009D', '2026-06-18', '11:30:00',  6.00, 0.00, 'directo'),
('12345678Z', '2026-06-19', '12:00:00', 21.60, 2.40, 'Web'),
('87654321X', '2026-06-19', '12:10:00', 13.00, 0.00, 'directo'),
('22222222R', '2026-06-19', '12:20:00', 28.80, 3.20, 'Web');


INSERT INTO compra (DNI, fecha_compra, hora_compra, precio_total, descuento_total, tipo_compra) VALUES
('12345678Z', '2026-06-21', '10:00:00', 12.00, 0.00, 'Web'),
('87654321X', '2026-06-21', '10:10:00', 11.00, 0.00, 'directo'),
('22222222R', '2026-06-21', '10:20:00', 13.00, 0.00, 'Web'),
('00000006Y', '2026-06-21', '10:30:00', 14.00, 0.00, 'directo'),
('00000008P', '2026-06-21', '10:40:00', 11.00, 0.00, 'Web');


INSERT INTO entrada (id_sesion, id_compra, importe) VALUES
(1, 1, 8.00),
(1, 1, 8.00),
(2, 1, 8.00),
(3, 2, 6.50),
(4, 3, 6.50),
(4, 3, 6.50),
(5, 3, 5.00),
(6, 3, 6.00),
(7, 3, 5.50),
(8, 4, 7.00),
(9, 4, 7.00),
(10, 5, 6.00),
(10, 5, 6.00),
(2, 5, 8.00),
(2, 5, 8.00),
(1, 6, 8.00),
(3, 7, 6.50),
(3, 7, 6.50),
(3, 7, 6.50),
(5, 8, 5.00),
(6, 8, 6.00),
(7, 9, 5.50),
(7, 9, 5.50),
(8, 9, 7.00),
(8, 9, 7.00),
(9, 9, 7.00),
(9, 9, 7.00),
(10, 10, 6.00),
(2, 11, 8.00),
(2, 11, 8.00),
(2, 11, 8.00),
(4, 12, 6.50),
(4, 12, 6.50),
(1, 13, 8.00),
(1, 13, 8.00),
(1, 13, 8.00),
(1, 13, 8.00),

(101, 14, 6.00),
(101, 14, 6.00),
(102, 15, 5.50),
(102, 15, 5.50),
(103, 16, 6.50),
(103, 16, 6.50),
(104, 17, 7.00),
(104, 17, 7.00),
(105, 18, 5.50),
(105, 18, 5.50);