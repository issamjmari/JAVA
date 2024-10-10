package ex05;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void addTransaction(Transaction transaction1, Transaction transaction2);

    int removeTransactionById(UUID id);

    Transaction[] transformIntoArray();

    int getLengthOfList();
}
