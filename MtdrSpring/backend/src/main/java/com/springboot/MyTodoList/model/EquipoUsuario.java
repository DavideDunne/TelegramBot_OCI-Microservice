package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "EQUIPO_USUARIO")
public class EquipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    @Column(name = "TEAM_ID")
    int idTeam;
    
    @Column(name = "USER_ID")
    int idUser;

    public EquipoUsuario() {}

    public EquipoUsuario(int idTeam, int idUser) {
        this.idTeam = idTeam;
        this.idUser = idUser;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "EquipoUsuario{" +
                "idTeam=" + idTeam +
                ", idUser=" + idUser +
                '}';
    }
}