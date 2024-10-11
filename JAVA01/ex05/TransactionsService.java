package ex05;

import java.util.UUID;

public class TransactionsService {
    private UsersList userList = new UsersArrayList();

    int addUser(User user) {
        return this.userList.addUser(user);
    }

    User getUserById(int userId) {
        User user = userList.getUserById(userId);
        return user;
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

    int removeTransaction(UUID transactionId, int userId) {
        User user = this.userList.getUserById(userId);
        return this.userList.removeTransaction(user, transactionId);
    }

    Transaction[] getUnpairedTransactions() {
        return this.userList.getUnpairedTransactions();
    }


}
