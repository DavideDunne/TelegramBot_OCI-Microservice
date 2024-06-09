package com.springboot.MyTodoList.model;
import javax.persistence.*;
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @Column(name = "name")
    String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User manager;
}
