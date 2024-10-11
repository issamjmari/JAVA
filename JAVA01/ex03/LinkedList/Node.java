package ex03.LinkedList;

import ex03.Transaction;

public class Node {
    Transaction data;
    Node next;
    Node prev;
    
    Node(Transaction data) {
        this.data = data;
    }
}

