package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "EQUIPO_USUARIO")
@IdClass(EquipoUsuarioId.class)
public class EquipoUsuario {

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Equipo equipo;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public EquipoUsuario(){}

    public EquipoUsuario(Equipo equipo, Usuario usuario) {
        this.equipo = equipo;
        this.usuario = usuario;
    }

    // Getters and setters


    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
