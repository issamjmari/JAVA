package ex05.LinkedList;

import java.util.UUID;

import ex05.Transaction;
import ex05.TransactionNotFoundException;

public class myLinkedList {
    Node head;
    Node tail;

    public void addAtHead(Transaction transaction) {
        Node newNode = new Node(transaction);
        if(head == null) {
            head = tail = newNode;
        }
        else {
            head.next = newNode;
            newNode.prev = head;
            head = newNode;
        }
    }
    void deleteNode(Node node) {
        if (node == tail) {
            tail = node.next;
            if (tail != null)
                tail.prev = null;
        }
        else if (node == head) {
            head = node.prev;
            if (head != null)
                head.next = null;
        }
        else {
            if (node.prev != null)
                node.prev.next = node.next;
            if (node.next != null) 
                node.next.prev = node.prev;
        }

        node.next = null;
        node.prev = null;
    }
    public int removeById(UUID id) {
        Node tailTemp = tail;
        boolean deleted = false;
        while(tailTemp != null) {
            Transaction currData = tailTemp.data;
            if(id.equals(currData.getId())) {
                deleteNode(tailTemp);
                deleted = true;
                return currData.getAmount();
            }
            tailTemp = tailTemp.next;
        }
        if(deleted == false)
            throw new TransactionNotFoundException("Transaction not found");
        return -1;
    }

    public Transaction[] toArray() {
        int size = 0;
        Node tailTemp = tail;   
        while(tailTemp != null) {
            size++;
            tailTemp = tailTemp.next;
        }
        Transaction[] arr = new Transaction[size];
        tailTemp = tail;
        int i = 0;
        while(i < size) {
            arr[i++] = tailTemp.data;
            tailTemp = tailTemp.next;
        }
        return arr;
    }

    public int getLength() {
        int length = 0;
        Node tailTemp = tail;
        while(tailTemp != null) {
            length++;
            tailTemp = tailTemp.next;
        }
        return length;
    }
    
}