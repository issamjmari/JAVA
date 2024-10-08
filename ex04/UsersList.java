package ex04;
import java.util.UUID;
public interface UsersList {
    void addUser(User user);

    User getUserById(int id);

    User getUserByIndex(int index);

    int getUsersNumber();

    int getUserBalance(User user);

    void performTransaction(int id1, int id2, int amount);

    Transaction[] getUserTransactions(User user);

    void removeTransaction(User user, UUID id);
}