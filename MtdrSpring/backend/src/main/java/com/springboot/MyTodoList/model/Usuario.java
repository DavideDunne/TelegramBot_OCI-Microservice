package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "NOMBRE1")
    String nombre1;

    @Column(name = "NOMBRE2")
    String nombre2;

    @Column(name = "APELLIDO1")
    String apellido1;

    @Column(name = "APELLIDO2")
    String apellido2;

    @Column(name = "IS_MANAGER")
    boolean isManager;

    @Column(name = "TELEGRAM_USERNAME")
    String telegramUsername;

    public Usuario() {}

    public Usuario(int id, String nombre1, String nombre2, String apellido1, String apellido2, boolean isManager, String telegramUsername) {
        this.id = id;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.isManager = isManager;
        this.telegramUsername = telegramUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre1='" + nombre1 + '\'' +
                ", nombre2='" + nombre2 + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", isManager=" + isManager +
                ", telegramUsername='" + telegramUsername + '\'' +
                '}';
    }
}
