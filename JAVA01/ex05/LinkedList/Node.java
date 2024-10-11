package ex05.LinkedList;

import ex05.Transaction;

public class Node {
    Transaction data;
    Node next;
    Node prev;
    
    Node(Transaction data) {
        this.data = data;
    }
}

