-- 0. Asegurar que el autonumérico de los campos ID esté sincronizado
SELECT setval(
    pg_get_serial_sequence('pais', 'id'),
    COALESCE((SELECT MAX(id) FROM pais) + 1, 1),
    false
);

SELECT setval(
    pg_get_serial_sequence('ciudad', 'id'),
    COALESCE((SELECT MAX(id) FROM ciudad) + 1, 1),
    false
);

SELECT setval(
    pg_get_serial_sequence('tipo', 'id'),
    COALESCE((SELECT MAX(id) FROM tipo) + 1, 1),
    false
);

SELECT setval(
    pg_get_serial_sequence('ruta', 'id'),
    COALESCE((SELECT MAX(id) FROM ruta) + 1, 1),
    false
);

SELECT setval(
    pg_get_serial_sequence('parada', 'id'),
    COALESCE((SELECT MAX(id) FROM parada) + 1, 1),
    false
);

-- 1. Agregar tipos de transporte turístico
INSERT INTO tipo (nombre)
VALUES
    ('Fluvial'),
    ('Terrestre'),
    ('Aéreo'),
    ('Peatonal')
ON CONFLICT(nombre) DO NOTHING;

-- 2. Agregar países
INSERT INTO pais (nombre, codigoalfa2)
VALUES
    ('España', 'ES'),
    ('Francia', 'FR'),
    ('Italia', 'IT'),
    ('México', 'MX'),
    ('Colombia', 'CO'),
    ('Perú', 'PE'),
    ('Egipto', 'EG'),
    ('Japón', 'JP'),
    ('Estados Unidos', 'US')
ON CONFLICT(nombre) DO NOTHING;

-- 3. Agregar ciudades con coordenadas
INSERT INTO ciudad (nombre, idpais, longitud, latitud)
SELECT datos.nombre, P.id, datos.longitud, datos.latitud
FROM (
    VALUES
        ('Barcelona', 'España', 2.1734035, 41.3850639),
        ('Madrid', 'España', -3.7037902, 40.4167754),
        ('París', 'Francia', 2.3522219, 48.8566140),
        ('Roma', 'Italia', 12.4963655, 41.9027835),
        ('Venecia', 'Italia', 12.3155151, 45.4408474),
        ('Ciudad de México', 'México', -99.1332785, 19.4326077),
        ('Cancún', 'México', -86.8515279, 21.1619110),
        ('Bogotá', 'Colombia', -74.0721566, 4.7109886),
        ('Medellín', 'Colombia', -75.5812119, 6.2476376),
        ('Cusco', 'Perú', -71.9674626, -13.5319814),
        ('El Cairo', 'Egipto', 31.2357116, 30.0444196),
        ('Tokio', 'Japón', 139.6503106, 35.6761919),
        ('Kioto', 'Japón', 135.7681, 35.0116),
        ('Nueva York', 'Estados Unidos', -74.0059728, 40.7127753)
) AS datos(nombre, pais, longitud, latitud)
JOIN pais P ON datos.pais = P.nombre;

-- 4. Agregar rutas turísticas
INSERT INTO ruta (nombre, idtipo, idciudad, descripcion)
SELECT datos.nombre, T.id, C.id, datos.descripcion
FROM (
    VALUES
        ('Crucero Histórico por el Sena', 'Fluvial', 'París', 'Recorrido por el río Sena con vistas a los principales monumentos de París'),
        ('Tour Gastronómico del Barrio Gótico', 'Peatonal', 'Barcelona', 'Descubre la historia y gastronomía del Barrio Gótico'),
        ('Ruta de los Templos', 'Terrestre', 'Kioto', 'Visita los templos históricos más importantes de Kioto'),
        ('Tour por la Ciudad Imperial', 'Peatonal', 'Tokio', 'Explora el Palacio Imperial y sus jardines'),
        ('Recorrido por el Centro Histórico', 'Peatonal', 'Ciudad de México', 'Visita los sitios históricos del centro de la ciudad'),
        ('Tour de Arte Urbano', 'Terrestre', 'Medellín', 'Conoce el arte urbano y la transformación de Medellín')
) AS datos(nombre, tipo, ciudad, descripcion)
JOIN tipo T ON datos.tipo = T.nombre
JOIN ciudad C ON datos.ciudad = C.nombre;

-- 5. Agregar paradas para la ruta "Crucero Histórico por el Sena" (París)
INSERT INTO parada (nombre, orden, idruta, longitud, latitud, tiempo, descripcion)
SELECT datos.nombre, datos.orden, R.id, datos.longitud, datos.latitud, datos.tiempo, datos.descripcion
FROM (
    VALUES
        ('Embarcadero Torre Eiffel', 1, 'Crucero Histórico por el Sena', 2.2931, 48.8592, 15, 'Punto de inicio a los pies de la Torre Eiffel'),
        ('Muelle del Museo de Orsay', 2, 'Crucero Histórico por el Sena', 2.3265, 48.8606, 20, 'Avistamiento de la antigua estación convertida en museo'),
        ('Isla de la Cité - Notre Dame', 3, 'Crucero Histórico por el Sena', 2.3499, 48.8534, 25, 'Parada junto a la emblemática catedral')
) AS datos(nombre, orden, ruta, longitud, latitud, tiempo, descripcion)
JOIN ruta R ON datos.ruta = R.nombre;

-- 6. Agregar paradas para la ruta "Tour Gastronómico del Barrio Gótico" (Barcelona)
INSERT INTO parada (nombre, orden, idruta, longitud, latitud, tiempo, descripcion)
SELECT datos.nombre, datos.orden, R.id, datos.longitud, datos.latitud, datos.tiempo, datos.descripcion
FROM (
    VALUES
        ('Plaza Real', 1, 'Tour Gastronómico del Barrio Gótico', 2.1750, 41.3798, 10, 'Plaza histórica rodeada de restaurantes típicos'),
        ('Mercado de La Boquería', 2, 'Tour Gastronómico del Barrio Gótico', 2.1716, 41.3818, 30, 'Famoso mercado con productos locales'),
        ('Catedral de Barcelona', 3, 'Tour Gastronómico del Barrio Gótico', 2.1763, 41.3838, 20, 'Catedral gótica en el corazón del barrio'),
        ('Plaza Sant Jaume', 4, 'Tour Gastronómico del Barrio Gótico', 2.1769, 41.3828, 15, 'Centro político e histórico de Barcelona')
) AS datos(nombre, orden, ruta, longitud, latitud, tiempo, descripcion)
JOIN ruta R ON datos.ruta = R.nombre;

-- 7. Agregar paradas para la ruta "Ruta de los Templos" (Kioto)
INSERT INTO parada (nombre, orden, idruta, longitud, latitud, tiempo, descripcion)
SELECT datos.nombre, datos.orden, R.id, datos.longitud, datos.latitud, datos.tiempo, datos.descripcion
FROM (
    VALUES
        ('Templo Kinkaku-ji', 1, 'Ruta de los Templos', 135.7292, 35.0394, 40, 'El famoso Pabellón Dorado'),
        ('Templo Ryoan-ji', 2, 'Ruta de los Templos', 135.7183, 35.0345, 30, 'Conocido por su jardín zen de rocas'),
        ('Templo Kiyomizu-dera', 3, 'Ruta de los Templos', 135.7850, 34.9949, 45, 'Templo con impresionantes vistas de Kioto')
) AS datos(nombre, orden, ruta, longitud, latitud, tiempo, descripcion)
JOIN ruta R ON datos.ruta = R.nombre;
