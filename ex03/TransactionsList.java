package ex03;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void removeTransactionById(String id);

    Transaction[] transformIntoArray();
}
