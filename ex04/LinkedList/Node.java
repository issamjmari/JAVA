package ex04.LinkedList;

import ex04.Transaction;

public class Node {
    Transaction data;
    Node next;
    Node prev;
    
    Node(Transaction data) {
        this.data = data;
    }
}

