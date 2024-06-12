package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_id;

    @Column(name = "nombre1", nullable = false)
    private String nombre1;

    @Column(name = "nombre2")
    private String nombre2;

    @Column(name = "apellido1", nullable = false)
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "isManager", nullable = false)
    private Boolean isManager;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Usuario manager;

    public Usuario(){}

    public Usuario(String nombre1, String nombre2, String apellido1, String apellido2, Boolean isManager) {
        this(null, nombre1, nombre2, apellido1, apellido2, isManager, null, null);
    }

    public Usuario(Long usuario_id, String nombre1, String nombre2, String apellido1, String apellido2, Boolean isManager, List<Tarea> tareas, Usuario manager) {
        this.usuario_id = usuario_id;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.isManager = isManager;
        this.tareas = tareas;
        this.manager = manager;
    }

    // Getters and setters


    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Usuario manager) {
        this.manager = manager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
}
