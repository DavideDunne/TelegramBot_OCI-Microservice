package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "EQUIPO")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "NOMBRE")
    String nombre;

    @Column(name = "ID_MANAGER")
    Long idManager;

    public Equipo() {}

    public Equipo(int id, String nombre, Long idManager) {
        this.id = id;
        this.nombre = nombre;
        this.idManager = idManager;
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

    public Long getIdManager() {
        return idManager;
    }

    public void setIdManager(Long idManager) {
        this.idManager = idManager;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idManager=" + idManager +
                '}';
    }
}