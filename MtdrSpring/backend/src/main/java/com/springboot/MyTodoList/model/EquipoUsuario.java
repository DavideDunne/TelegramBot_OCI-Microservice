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

    // Getters and setters
}
