package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "TAREA")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "NOMBRE")
    String nombre;

    @Column(name = "DESCRIPCION")
    String descripcion;

    @Column(name = "ID_USUARIO")
    int idUsuario;

    @Column(name = "COMPLETADO")
    boolean completado;

    public Tarea() {}

    public Tarea(int id, String nombre, String descripcion, int idUsuario, boolean completado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.completado = completado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idUsuario=" + idUsuario +
                ", completado=" + completado +
                '}';
    }
}
