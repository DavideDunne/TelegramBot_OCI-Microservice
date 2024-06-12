package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EQUIPOS")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipo_id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Usuario manager;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipoUsuario> equipoUsuarios;

    public Equipo(){}

    public Equipo(String nombre, Usuario manager) {
        this(null, null, manager, nombre);
    }

    public Equipo(Long equipo_id, List<EquipoUsuario> equipoUsuarios, Usuario manager, String nombre) {
        this.equipo_id = equipo_id;
        this.equipoUsuarios = equipoUsuarios;
        this.manager = manager;
        this.nombre = nombre;
    }

    // Getters and setters


    public Long getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(Long equipo_id) {
        this.equipo_id = equipo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getManager() {
        return manager;
    }

    public void setManager(Usuario manager) {
        this.manager = manager;
    }

    public List<EquipoUsuario> getEquipoUsuarios() {
        return equipoUsuarios;
    }

    public void setEquipoUsuarios(List<EquipoUsuario> equipoUsuarios) {
        this.equipoUsuarios = equipoUsuarios;
    }
}
