package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIO")
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

    // Getters and setters
}
