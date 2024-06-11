-- Copy and paste this script in Oracle Database Actions

-- Create database tables for the project
-- Written in Oracle SQL

CREATE TABLE TODOUSER.usuario (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    nombre1 VARCHAR2(50) NOT NULL,
    nombre2 VARCHAR2(50),
    apellido1 VARCHAR2(50) NOT NULL,
    apellido2 VARCHAR2(50),
    is_manager NUMBER(1,0) NOT NULL,
    telegram_username VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE TODOUSER.tarea (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR2(50) NOT NULL,
    descripcion VARCHAR2(4000) NOT NULL,
    id_usuario NUMBER NOT NULL,
    completado NUMBER(1,0) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES TODOUSER.usuario(id)
);

CREATE TABLE TODOUSER.equipo (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR2(50) NOT NULL,
    id_manager NUMBER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_manager) REFERENCES TODOUSER.usuario(id)
);

CREATE TABLE TODOUSER.equipo_usuario (
    id_equipo NUMBER NOT NULL,
    id_usuario NUMBER NOT NULL,
    PRIMARY KEY (id_equipo, id_usuario),
    FOREIGN KEY (id_equipo) REFERENCES TODOUSER.equipo(id),
    FOREIGN KEY (id_usuario) REFERENCES TODOUSER.usuario(id)
);