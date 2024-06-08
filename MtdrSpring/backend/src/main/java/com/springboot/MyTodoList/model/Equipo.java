package com.springboot.MyTodoList.model;

/**
 * FIXME user id shouldn't go in this table, it should be on the the Usuario-Equipo model instead. Unless you are referring to the manager then change the name to managerId
 * TODO add the annotations to this class
 * TODO add controller, repository and service classes for this model
 * representation of the Equipo table that exists already
 * @author Herbert Euroza
 * @author Davide Dunne
 */
public class Equipo {
    int Id;
    String nombre;
}
