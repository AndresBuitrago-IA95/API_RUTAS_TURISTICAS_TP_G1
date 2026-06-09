-- Ejecutar primero
DROP DATABASE IF EXISTS RutasTuristicas;

-- Ejecutar segundo
CREATE DATABASE RutasTuristicas; 

-- Para las siguientes instrucciones, se debe cambiar la conexión

-- Crear tabla PAIS
CREATE TABLE Pais (
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    CodigoAlfa2 VARCHAR(2) NOT NULL
);

-- Crear índice único para PAIS
CREATE UNIQUE INDEX ixPais ON Pais(Nombre);

-- Crear tabla CIUDAD
CREATE TABLE Ciudad (
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    IdPais INT NOT NULL,
    Longitud DECIMAL(10, 7) NOT NULL,
    Latitud DECIMAL(10, 7) NOT NULL,
    CONSTRAINT fkCiudad_Pais FOREIGN KEY (IdPais) REFERENCES Pais(Id)
);

-- Crear índice para CIUDAD
CREATE INDEX ixCiudad ON Ciudad(Nombre);

-- Crear tabla TIPO
CREATE TABLE Tipo (
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL
);

-- Crear índice único para TIPO
CREATE UNIQUE INDEX ixTipo ON Tipo(Nombre);

-- Crear tabla RUTA
CREATE TABLE Ruta (
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(200) NOT NULL,
    IdTipo INT NOT NULL,
    IdCiudad INT NOT NULL,
    Descripcion TEXT,
    CONSTRAINT fkRuta_Tipo FOREIGN KEY (IdTipo) REFERENCES Tipo(Id),
    CONSTRAINT fkRuta_Ciudad FOREIGN KEY (IdCiudad) REFERENCES Ciudad(Id)
);

-- Crear índice para RUTA
CREATE INDEX ixRuta_Ciudad ON Ruta(IdCiudad);
CREATE INDEX ixRuta_Tipo ON Ruta(IdTipo);

-- Crear tabla PARADA
CREATE TABLE Parada (
    Id SERIAL PRIMARY KEY,
    Nombre VARCHAR(200) NOT NULL,
    Orden INT NOT NULL,
    IdRuta INT NOT NULL,
    Longitud DECIMAL(10, 7) NOT NULL,
    Latitud DECIMAL(10, 7) NOT NULL,
    Tiempo INT NOT NULL, -- Tiempo en minutos
    Descripcion TEXT,
    CONSTRAINT fkParada_Ruta FOREIGN KEY (IdRuta) REFERENCES Ruta(Id) ON DELETE CASCADE
);

-- Crear índice para PARADA
CREATE INDEX ixParada_Ruta ON Parada(IdRuta, Orden);
