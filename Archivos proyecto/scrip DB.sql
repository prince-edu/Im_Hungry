CREATE DATABASE Im_Hungry;

USE Im_Hungry;


CREATE TABLE `Valoracion` (
  `id_valoracion` int NOT NULL AUTO_INCREMENT,
  `descripcion` NVARCHAR(100),
  `calificacion` int,
  primary key(`id_valoracion`)
);

CREATE TABLE Pedido (
  id_pedido int NOT NULL AUTO_INCREMENT,
  preferencias  NVARCHAR(100),
  `fecha_pedido` date,
  `precio_total` double, 
  `estado` NVARCHAR(10),
  `id_venta` int,
  `matricula` NVARCHAR(12),
  `id_producto` int,
  primary key (`id_pedido`),
  FOREIGN KEY (`id_venta`) REFERENCES Venta(`id_venta`),
  FOREIGN KEY (`matricula`) REFERENCES Estudiante(`matricula`),
  FOREIGN KEY (`id_producto`) REFERENCES Producto(`id_producto`)
);

CREATE TABLE `Venta` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `cantidad` int,
  `fecha_venta` date,
  `precio_total` double,
  primary key (id_venta)
);

CREATE TABLE `Comprador` (
  matricula NVARCHAR(12),
  id_producto int,
  FOREIGN KEY (`matricula`) REFERENCES Estudiante(`matricula`),
  FOREIGN KEY (`id_producto`) REFERENCES Producto(`id_producto`)
);

CREATE TABLE `ProductosFavoritos` (
  matricula NVARCHAR(12),
  id_producto int,
  FOREIGN KEY (`matricula`) REFERENCES Estudiante(`matricula`),
  FOREIGN KEY (`id_producto`) REFERENCES Producto(`id_producto`)
);

CREATE TABLE `Vendedor` (
  matricula NVARCHAR(12),
  id_producto int,
  FOREIGN KEY (`matricula`) REFERENCES Estudiante(`matricula`),
  FOREIGN KEY (`id_producto`) REFERENCES Producto(`id_producto`)
);

CREATE TABLE `Producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` NVARCHAR(50),
  `descripcion` NVARCHAR(100),
  `cantidad_disponible` int,
  `hora_venta_inicial` time,
  `hora_venta_final` time,
  `punto_encuentro` NVARCHAR(50),
  `precio` double,
  `estado` NVARCHAR(10),
  `foto` NVARCHAR(100),
  primary key (`id_producto`)
);

CREATE TABLE `Estudiante` (
  `matricula` NVARCHAR(12),
  `nombre` NVARCHAR(50),
  `apellido_paterno` NVARCHAR(20),
  `apellido_materno` NVARCHAR(20),
  `correo_institucional` NVARCHAR(50),
  `tipo_perfil_vendedor` boolean,
  `tipo_perfil_comprador` boolean,
  `foto_perfil` NVARCHAR(100),
  `foto_credencial` NVARCHAR(100),
  `password` NVARCHAR(20),
  primary key(matricula)
);