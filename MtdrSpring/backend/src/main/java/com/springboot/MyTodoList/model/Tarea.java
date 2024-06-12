package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "TAREAS")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tarea_id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "isDone")
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Tarea(){}

    public Tarea(String nombre, String descripcion, Boolean isDone, Usuario usuario) {
        this(null, nombre, descripcion, isDone, usuario);
    }

    public Tarea(Long tarea_id, String nombre, String descripcion, Boolean isDone, Usuario usuario) {
        this.tarea_id = tarea_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.isDone = isDone;
        this.usuario = usuario;
    }

    // Getters and setters


    public Long getTarea_id() {
        return tarea_id;
    }

    public void setTarea_id(Long tarea_id) {
        this.tarea_id = tarea_id;
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

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
