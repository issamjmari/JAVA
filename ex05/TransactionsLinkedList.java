package ex05;

import java.util.UUID;

import ex05.LinkedList.myLinkedList;

public class TransactionsLinkedList implements TransactionsList {
    myLinkedList myLinkedList = new myLinkedList();

    public void addTransaction(Transaction transaction) {
        myLinkedList.addAtHead(transaction);
    }

    public void addTransaction(Transaction transaction1, Transaction transaction2) {
        myLinkedList.addAtHead(transaction1);
        myLinkedList.addAtHead(transaction2);
    }

    public void removeTransactionById(UUID id) {
        myLinkedList.removeById(id);
    }

    public Transaction[] transformIntoArray() {
        return myLinkedList.toArray();
    }

    public int getLengthOfList() {
        return myLinkedList.getLength();
    }
}
