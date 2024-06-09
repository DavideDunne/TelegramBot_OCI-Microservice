package com.springboot.MyTodoList.model;
import javax.persistence.*;

/**
 * representation of the USER table that exists already
 * Telegram users that can interact with the bot
 * @author Davide Dunne
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    /**
     * The name of the user.
     * Example: "Davide Dunne"
     */
    @Column(name = "name")
    String name;
    /**
     * The telegram username of the user
     * Shows up as "@name" in Telegram
     */
    @Column(name = "telegram_username")
    String TelegramUsername;
    /**
     * The type of user.
     * Can be a regular user or an admin.
     * Many to one relationship because one type can have many users.
     */
    @ManyToOne
    @JoinColumn(name = "user_type_id")
    UserType userType;
    public User(){

    }
    public User(int Id, String name, String TelegramUsername, UserType userType) {
        this.Id = Id;
        this.name = name;
        this.TelegramUsername = TelegramUsername;
        this.userType = userType;
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
    public void setTelegramUsername(String TelegramUsername) {
        this.TelegramUsername = TelegramUsername;
    }
    public String getTelegramUsername() {
        return TelegramUsername;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public UserType getUserType() {
        return userType;
    }
}
