package ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList userList = new UsersArrayList();

    void addUser(User user) {
        this.userList.addUser(user);
    }

    int getUserBalance(User user) {
        return userList.getUserBalance(user);
    }

    void performTransaction(int id1, int id2, int amount) {
        this.userList.performTransaction(id1, id2, amount);
    }

    Transaction[] getUserTransfers(int id) {
        User user = userList.getUserById(id);
        return userList.getUserTransactions(user);
    }

    void removeTransaction(UUID transactionId, int userId) {
        User user = this.userList.getUserById(userId);
        this.userList.removeTransaction(user, transactionId);
    }

    Transaction[] getUnpairedTransactions() {
        return this.userList.getUnpairedTransactions();
    }


}
