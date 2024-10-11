package ex04;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void addTransaction(Transaction transaction1, Transaction transaction2);

    void removeTransactionById(UUID id);

    Transaction[] transformIntoArray();

    int getLengthOfList();
}
