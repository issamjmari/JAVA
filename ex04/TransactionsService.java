package ex04;

public class TransactionsService {
    private final UsersList userList;

    TransactionsService(UsersList userList) {
        this.userList = userList;
    }

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
        return new Transaction[0];
    }

    void removeTransaction(int transactionId, int userId) {

    }

    Transaction[] getUnpairedTransactions(int id) {
        return new Transaction[0];
    }


}
