package com.springboot.MyTodoList.model;

import javax.persistence.*;

/**
 * TODO make sure the builder pattern works
 * TODO add controller, repository and service classes for this model
 * representation of the Usuario table that exists already
 * User who will use the Telegram bot
 * @author Herbert Euroza
 * @author Davide Dunne
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    /**
     * First name of user
     * Mandatory
     */
    @Column(name = "nombre1")
    String nombre1;
    /**
     * Second name of user
     * Optional
     */
    @Column(name = "nombre2")
    String nombre2;
    /**
     * First surname of user
     * Mandatory
     */
    @Column(name = "apellido1")
    String apellido1;
    /**
     * Second surname of user
     * Optional
     */
    @Column(name = "apellido2")
    String apellido2;

    public Usuario() {

    }

    /**
     * Use the builder pattern in order to create a new Usuario
     * <a href="https://stackoverflow.com/a/997883/17398340">Example</a>
     * @author Davide Dunne
     */
    public static class UsuarioBuilder {
        private String nombre1;
        private String nombre2;
        private String apellido1;
        private String apellido2;

        public UsuarioBuilder(String nombre1, String apellido1) {
            this.nombre1 = nombre1;
            this.apellido1 = apellido1;
        }

        public UsuarioBuilder nombre2(String nombre2) {
            this.nombre2 = nombre2;
            return this;
        }

        public UsuarioBuilder apellido2(String apellido2) {
            this.apellido2 = apellido2;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    /**
     * Constructor for the Usuario class using the builder pattern, nombre2 and apellido2 are optional
     * <a href="https://stackoverflow.com/a/997883/17398340">Example</a>
     * <pre>
     * Usuario usuario = new Usuario.UsuarioBuilder("nombre1", "apellido1")
     *     .nombre2("nombre2") // Optional, can be omitted
     *     .apellido2("apellido2") // Optional, can be omitted
     *     .build();
     * </pre>
     * @param builder the builder object
     */
    public Usuario(UsuarioBuilder builder) {
        this.nombre1 = builder.nombre1;
        this.nombre2 = builder.nombre2;
        this.apellido1 = builder.apellido1;
        this.apellido2 = builder.apellido2;
    }
}
