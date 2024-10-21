package fr._42.chat.models;

import java.util.List;
import java.util.Objects;
import fr._42.chat.models.User;
import fr._42.chat.models.Message;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messages; // List of messages in the chatroom

    // Getters and setters...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chatroom)) return false;
        Chatroom chatroom = (Chatroom) o;
        return Objects.equals(id, chatroom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
