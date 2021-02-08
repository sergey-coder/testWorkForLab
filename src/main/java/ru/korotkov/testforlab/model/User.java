package ru.korotkov.testforlab.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
    private String username;
    private String userpatronymic;
    private String userfamily;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public User() {
    }

    public User(String login, String password, String email, String username, String userpatronymic, String userfamily, Role role, Status status) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.username = username;
        this.userpatronymic = userpatronymic;
        this.userfamily = userfamily;
        this.role = role;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpatronymic() {
        return userpatronymic;
    }

    public void setUserpatronymic(String userpatronymic) {
        this.userpatronymic = userpatronymic;
    }

    public String getUserfamily() {
        return userfamily;
    }

    public void setUserfamily(String userfamily) {
        this.userfamily = userfamily;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

