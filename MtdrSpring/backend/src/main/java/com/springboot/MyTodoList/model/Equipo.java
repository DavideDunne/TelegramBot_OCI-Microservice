package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EQUIPO")
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

    // Getters and setters
}
