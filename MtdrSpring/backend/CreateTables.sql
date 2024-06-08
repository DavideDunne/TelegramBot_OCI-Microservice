-- Create tables for the application
-- Load it into the OCI database actions

CREATE TABLE usuarios(
usuario_id binary(16) primary key NOT NULL,
nombre1 varchar(255) NOT NULL,
nombre2 varchar(255),
apellido1 varchar(255) NOT NULL,
apellido2 varchar(255)
)

CREATE TABLE tareas(
tarea_id binary(16) primary key NOT NULL,
titulo varchar(255) NOT NULL,
usuario_id binary(16),
foreign key (usuario_id) references usuarios(usuario_id)
)

CREATE TABLE estado(
estado_id int primary key NOT NULL,
tarea_id binary(16),
foreign key (tarea_id) references tareas(tarea_id)
)

CREATE TABLE tipo_usuario(
tipo varchar(255) primary key NOT NULL,
usuario_id binary(16),
foreign key (usuario_id) references usuarios(usuario_id)
)

CREATE TABLE equipo (
equipo_id binary(16) primary key NOT NULL,
nombre_equipo varchar(255) NOT NULL,
usuario_id binary(16),
foreign key (usuario_id) references usuarios(usuario_id)
)

CREATE TABLE usuario_equipo (
usuario_id binary(16),
equipo_id binary(16),
foreign key (usuario_id) references usuarios(usuario_id),
foreign key (equipo_id) references equipo(equipo_id)
)
