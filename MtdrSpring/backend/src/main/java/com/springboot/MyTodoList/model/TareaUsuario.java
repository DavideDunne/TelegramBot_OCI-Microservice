package com.springboot.MyTodoList.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "tarea")
public class TareaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTarea;

//    @Column(name = "TAREA_ID")
//    private String id_Tarea;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "isdone")
    private boolean isDone;

    @Column(name = "id_usuario", insertable = false, updatable = false)
    private String id_usuario;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UsuarioTarea usuarioTarea;

    // getters and setters

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public UsuarioTarea getUsuarioTarea() {
        return usuarioTarea;
    }

    public void setUsuarioTarea(UsuarioTarea usuarioTarea) {
        this.usuarioTarea = usuarioTarea;
    }
}
