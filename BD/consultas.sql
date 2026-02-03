USE cine_elorrieta;

-- 1) El dinero recaudado por cada película con recaudación superior a una cifra dada.
SELECT p.titulo,
       SUM(e.importe) AS recaudacion_total
FROM pelicula p
JOIN sesion s  ON p.id_pelicula = s.id_pelicula
JOIN entrada e ON s.id_sesion  = e.id_sesion
GROUP BY p.id_pelicula, p.titulo
HAVING SUM(e.importe) > 10;

-- 2) La duración media de las películas por género.
SELECT g.nombre_genero,
       AVG(p.duracion) AS duracion_media
FROM genero g
JOIN pelicula p ON g.id_genero = p.id_genero
GROUP BY g.id_genero, g.nombre_genero;

-- 3) El número de sesiones ofrecidas por película (sin filtros).
SELECT p.titulo,
       COUNT(s.id_sesion) AS total_sesiones
FROM pelicula p
LEFT JOIN sesion s ON p.id_pelicula = s.id_pelicula
GROUP BY p.id_pelicula, p.titulo;

-- 3b) El número de sesiones ofrecidas por película filtrando por género.
SELECT p.titulo,
       COUNT(s.id_sesion) AS total_sesiones
FROM pelicula p
JOIN genero g ON p.id_genero = g.id_genero
LEFT JOIN sesion s ON p.id_pelicula = s.id_pelicula
WHERE g.nombre_genero = 'Accion'
GROUP BY p.id_pelicula, p.titulo;

-- 3c) El número de sesiones ofrecidas por película filtrando por precio .
SELECT p.titulo,
       COUNT(s.id_sesion) AS total_sesiones_precio
FROM pelicula p
LEFT JOIN sesion s 
       ON p.id_pelicula = s.id_pelicula
      AND s.precio >= 6
GROUP BY p.id_pelicula, p.titulo;

-- 4) El precio medio de las películas por género.
SELECT g.nombre_genero,
       AVG(pm.precio_medio_pelicula) AS precio_medio_genero
FROM genero g
JOIN pelicula p ON p.id_genero = g.id_genero
JOIN (
    SELECT id_pelicula, AVG(precio) AS precio_medio_pelicula
    FROM sesion
    GROUP BY id_pelicula
) pm ON pm.id_pelicula = p.id_pelicula
GROUP BY g.id_genero, g.nombre_genero;

-- 5) Datos de las películas con mayor recaudación.
SELECT p.id_pelicula,
       p.titulo,
       g.nombre_genero,
       p.duracion,
       p.idioma,
       SUM(e.importe) AS recaudacion_total
FROM pelicula p
JOIN genero g ON g.id_genero = p.id_genero
JOIN sesion s  ON p.id_pelicula = s.id_pelicula
JOIN entrada e ON s.id_sesion  = e.id_sesion
GROUP BY p.id_pelicula, p.titulo, g.nombre_genero, p.duracion, p.idioma
ORDER BY recaudacion_total DESC;

-- 6) Clientes con mayores descuentos.
SELECT c.DNI,
       c.nombre,
       c.apellido,
       SUM(co.descuento_total) AS descuento_total
FROM cliente c
JOIN compra co ON c.DNI = co.DNI
GROUP BY c.DNI, c.nombre, c.apellido
ORDER BY descuento_total DESC
LIMIT 10;

-- 7) Clientes que han adquirido mayor número de entradas.
SELECT c.DNI,
       c.nombre,
       c.apellido,
       COUNT(e.id_entrada) AS total_entradas
FROM cliente c
JOIN compra co ON c.DNI = co.DNI
JOIN entrada e ON co.id_compra = e.id_compra
GROUP BY c.DNI, c.nombre, c.apellido
ORDER BY total_entradas DESC
LIMIT 10;

-- 8) Clientes que han gastado más dinero.
SELECT c.DNI,
       c.nombre,
       c.apellido,
       SUM(co.precio_total) AS gasto_total
FROM cliente c
JOIN compra co ON c.DNI = co.DNI
GROUP BY c.DNI, c.nombre, c.apellido
ORDER BY gasto_total DESC
LIMIT 10;

-- 9) Películas con espectadores inferior a una cantidad dada.
SELECT p.titulo,
       COUNT(e.id_entrada) AS espectadores
FROM pelicula p
LEFT JOIN sesion s  ON p.id_pelicula = s.id_pelicula
LEFT JOIN entrada e ON s.id_sesion  = e.id_sesion
GROUP BY p.id_pelicula, p.titulo
HAVING COUNT(e.id_entrada) < 10;

-- 10) Clientes que aún no han comprado ninguna entrada.
SELECT c.DNI, c.nombre, c.apellido, c.email
FROM cliente c
LEFT JOIN compra co ON c.DNI = co.DNI
WHERE co.id_compra IS NULL;


/*Opción alternativa: clientes sin entradas registradas (incluye el caso raro de compras sin entradas).*/
SELECT c.DNI,
       c.nombre,
       c.apellido,
       c.email
FROM cliente c
LEFT JOIN compra co  ON c.DNI = co.DNI
LEFT JOIN entrada e  ON co.id_compra = e.id_compra
GROUP BY c.DNI, c.nombre, c.apellido, c.email
HAVING COUNT(e.id_entrada) = 0;

