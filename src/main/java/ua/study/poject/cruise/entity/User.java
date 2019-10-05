package ua.study.poject.cruise.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is an entity that corresponds to a row in the "user" table
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5130013188728843121L;

    private Long id = -1L;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String tel;
    private Role role;


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


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id.equals(user.id) &&
            Objects.equals(login, user.login) &&
            Objects.equals(password, user.password) &&
            Objects.equals(firstName, user.firstName) &&
            Objects.equals(secondName, user.secondName) &&
            Objects.equals(email, user.email) &&
            Objects.equals(tel, user.tel) &&
            Objects.equals(role, user.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login, password, firstName, secondName, email, tel, role);
  }

    @Override
    public String toString() {
        return "id: " + id +
                ", login: " + login +
                ", password: " + password +
                ", firstName: " + firstName +
                ", secondName: " + secondName +
                ", email: " + email +
                ", tel: " + tel +
                ", role: " + role;
    }
}
