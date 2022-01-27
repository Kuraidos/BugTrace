package com.bugtace.bugtraceserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;
//Holds data about user
@Entity(name="user_data")
public class User
{
    @Column(nullable = false)
    private String username;
    @Id
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private UUID teamId;

    public User(@JsonProperty("username") String username,@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
