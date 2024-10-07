package ex03;

import ex03.LinkedList.myLinkedList;

public class TransactionsLinkedList implements TransactionsList {
    myLinkedList myLinkedList = new myLinkedList();

    public void addTransaction(Transaction transaction) {
        myLinkedList.addAtHead(transaction);
    }

    public void removeTransactionById(String id) {
        myLinkedList.removeById(id);
    }

    public Transaction[] transformIntoArray() {
        return new Transaction[0];
    }
}
