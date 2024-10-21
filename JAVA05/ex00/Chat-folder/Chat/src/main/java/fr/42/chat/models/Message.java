package fr._42.chat.models;

import java.util.Objects;
import fr._42.chat.models.User;
import fr._42.chat.models.Chatroom;
import java.sql.Timestamp;
public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private Timestamp timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
