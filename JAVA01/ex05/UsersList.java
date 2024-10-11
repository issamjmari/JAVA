package ex05;
import java.util.UUID;
public interface UsersList {
    int addUser(User user);

    User getUserById(int id);

    User getUserByIndex(int index);

    int getUsersNumber();

    int getUserBalance(User user);

    void performTransaction(int id1, int id2, int amount);

    Transaction[] getUserTransactions(User user);

    int removeTransaction(User user, UUID Transactionid);

    Transaction[] getUnpairedTransactions();
}