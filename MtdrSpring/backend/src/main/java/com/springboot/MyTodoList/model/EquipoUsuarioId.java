package com.springboot.MyTodoList.model;

import java.io.Serializable;
import java.util.Objects;

public class EquipoUsuarioId implements Serializable {

    private Long equipo;
    private Long usuario;

    // Constructors, equals, and hashCode

    public EquipoUsuarioId(){}

    public EquipoUsuarioId(Long equipo, Long usuario) {
        this.equipo = equipo;
        this.usuario = usuario;
    }

    public Long getEquipo() {
        return equipo;
    }

    public void setEquipo(Long equipo) {
        this.equipo = equipo;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
}
