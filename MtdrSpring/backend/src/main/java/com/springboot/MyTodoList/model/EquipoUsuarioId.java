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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipoUsuarioId that = (EquipoUsuarioId) o;
        return Objects.equals(equipo, that.equipo) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipo, usuario);
    }
}
