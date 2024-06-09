package com.springboot.MyTodoList.model;
import javax.persistence.*;

/**
 * FIXME maybe only need to use an enum for ToDoItemAssignment, I doubt there will be anything else other than "TODO, IN_PROGRESS, DONE"
 * representation of the TODOITEM_STATE table that exists already
 * It represents the states that a task can be in
 * @author Davide Dunne
 */
@Entity
@Table(name = "todoitem_state")
public class ToDoItemState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @Column(name = "name")
    String name;
    public ToDoItemState(){
    }
    public ToDoItemState(int Id, String name) {
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
