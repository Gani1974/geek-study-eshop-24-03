package ru.geekbrains.controllers.repr;

import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UserRepr {

    private Long id;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    private Set<Role> roles;

    public UserRepr() {
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserRepr{" +
                "id=" + id +
                ", username='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
