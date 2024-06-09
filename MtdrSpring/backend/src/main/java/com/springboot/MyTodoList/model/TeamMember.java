package com.springboot.MyTodoList.model;
import javax.persistence.*;
import java.util.List;

/**
 * representation of the TEAM_MEMBER table that exists already
 * It represents the members of a team
 * @author Davide Dunne
 */
@Entity
@Table(name = "team_member")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    List<User> user;
    public TeamMember(){
    }
    public TeamMember(int Id, Team team, List<User> user) {
        this.Id = Id;
        this.team = team;
        this.user = user;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public Team getTeam() {
        return team;
    }
    public void setUser(List<User> user) {
        this.user = user;
    }
    public List<User> getUser() {
        return user;
    }
}
