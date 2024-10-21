package fr._42.chat.models;

import java.util.Objects;
public class User {
    private Long id;
    private String login;
    private String password;

    // Getters and setters...
    public void setId(Long i) {
        this.id = i;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
