package com.springboot.MyTodoList.model;

import javax.persistence.*;

/**
 * representation of the USERTYPE table that exists already
 * Tells us what are the different types of users that can exist
 * @author Davide Dunne
 */
@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @Column(name = "name")
    String name;
    public UserType(){

    }
    public UserType(int Id, String name) {
        this.Id = Id;
        this.name = name;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
