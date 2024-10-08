package ex04;
import java.util.UUID;

public class UsersArrayList implements UsersList {
    private User[] users = new User[10];
    private int usersLength = 0;

    public void addUser(User newUser) {
        if (usersLength == users.length) {
            User[] newArray = new User[users.length + users.length / 2];
            for(int i = 0; i < users.length; i++) {
                newArray[i] = users[i];
            }
            users = newArray;
            System.out.println("I reached max with a new capacity of: "+ users.length);
        }
        users[usersLength] = newUser;
        usersLength++;
    }

    public User getUserById(int id) {
        for(int i = 0; i < users.length; i++) {
            if(users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("user not found");
    }

    public User getUserByIndex(int index) {
        return users[index];
    }

    public int getUsersNumber() {
        return usersLength;
    }

    public int getUserBalance(User user) {
        return user.getBalance();
    }

    public void performTransaction(int id1, int id2, int amount) {
        User recipient = this.getUserById(id1);
        User sender = this.getUserById(id2);
        Transaction income = new Transaction(recipient, sender, "INCOME", 300);
        Transaction outcome = new Transaction(sender, recipient, "OUTCOME", -300);
        outcome.setId(income.getId());
        recipient.getTransactionsList().addTransaction(income, outcome);
        sender.getTransactionsList().addTransaction(income, outcome);
    }

    public Transaction[] getUserTransactions(User user) {
        return user.getTransactionsList().transformIntoArray();
    }

    public void removeTransaction(User user, UUID transactionId) {
        user.getTransactionsList().removeTransactionById(transactionId);
    }
}
