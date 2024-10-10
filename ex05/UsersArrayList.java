package ex05;
import java.util.UUID;

public class UsersArrayList implements UsersList {
    private User[] users = new User[10];
    private int usersLength = 0;

    public int addUser(User newUser) {
        if (usersLength == users.length) {
            User[] newArray = new User[users.length + users.length / 2];
            for(int i = 0; i < users.length; i++) {
                newArray[i] = users[i];
            }
            users = newArray;
            System.out.println("I reached max with a new capacity of: " + users.length);
        }
        users[usersLength] = newUser;
        usersLength++;
        return usersLength;
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
        Transaction debit = new Transaction(recipient, sender, "INCOME", amount);
        Transaction credit = new Transaction(sender, recipient, "OUTCOME", amount * -1);
        UUID transactionId = UUID.randomUUID();
        debit.setId(transactionId);
        credit.setId(transactionId);

        sender.getTransactionsList().addTransaction(debit);
        recipient.getTransactionsList().addTransaction(credit);
    }

    public Transaction[] getUserTransactions(User user) {
        return user.getTransactionsList().transformIntoArray();
    }

    public int removeTransaction(User user, UUID transactionId) {
        return user.getTransactionsList().removeTransactionById(transactionId);
    }

    Transaction[] removeNullElements(Transaction[] oldArray) {
        int maxLength = 0;
        for(Transaction transaction: oldArray) {
            if(transaction == null)
                break;
            maxLength++;
        }
        Transaction[] newArray = new Transaction[maxLength];
        int i = 0;
        for(Transaction transaction: oldArray) {
            newArray[i++] = transaction;
        }
        return newArray;
    }
    public Transaction[] getUnpairedTransactions() {
        int maxLength = 0;
        int arrayLoop = 0;
        for(User user: users) {
            if(user == null)
                break;
            maxLength += user.getTransactionsList().getLengthOfList();
        }
        Transaction[] transactions = new Transaction[maxLength];
        for(User outUser: users) {
            if(outUser == null)
                break;
            Transaction[] outUserTransactions = outUser.getTransactionsList().transformIntoArray();
            for(Transaction outTransaction: outUserTransactions) {
                boolean foundPair = false;
                for(User inUser: users) {
                    if(inUser != outUser) {
                        if(inUser == null)
                        break;
                        Transaction[] inUserTransactions = inUser.getTransactionsList().transformIntoArray();
                        for(Transaction inTransaction: inUserTransactions) {
                            if(inTransaction.getId() == outTransaction.getId())
                                foundPair = true;
                            
                        }
                    }
                }
                if(foundPair == false) {
                    if(outUser == outTransaction.getRecipient()) {
                        outTransaction.setLackingPart(false);
                    }
                    else {
                        outTransaction.setLackingPart(true);
                    }
                    transactions[arrayLoop++] = outTransaction;
                }

            }
        }
        return removeNullElements(transactions);

    }
}
