package com.springboot.MyTodoList.model;
import javax.persistence.*;

/**
 * FIXME maybe only need to use an enum for todoItemState, I doubt there will be anything else other than "TODO, IN_PROGRESS, DONE"
 * representation of the TODOITEM_ASSIGNMENT table that exists already
 * It represents the assignment of a task to a user
 * It also represents the state of the task assigned to the user
 * @author Davide Dunne
 */
@Entity
@Table(name = "todoitem_assignment")
public class ToDoItemAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    /**
     * The task that is assigned to the user
     * There may be one task assigned to many users
     * However they are just instances of the same task
     */
    @ManyToOne
    @JoinColumn(name = "todoitem_id")
    ToDoItem todoItem;
    /**
     * The user that the task is assigned to
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    /**
     * The state of the task that is assigned to the user
     */
    @ManyToOne
    @JoinColumn(name = "todoitem_state_id")
    ToDoItemState todoItemState;
    public ToDoItemAssignment(){
    }
    public ToDoItemAssignment(int Id, ToDoItem todoItem, User user, ToDoItemState todoItemState) {
        this.Id = Id;
        this.todoItem = todoItem;
        this.user = user;
        this.todoItemState = todoItemState;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }
    public void setToDoItem(ToDoItem todoItem) {
        this.todoItem = todoItem;
    }
    public ToDoItem getToDoItem() {
        return todoItem;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void setToDoItemState(ToDoItemState todoItemState) {
        this.todoItemState = todoItemState;
    }
    public ToDoItemState getToDoItemState() {
        return todoItemState;
    }
}
