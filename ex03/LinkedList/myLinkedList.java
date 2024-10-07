package ex03.LinkedList;

import java.util.UUID;

import ex03.Transaction;

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
    public void removeById(String id) {
        Node tailTemp = tail;
        while(tailTemp != null) {
            Transaction currData = tailTemp.data;
            UUID uuid = UUID.fromString(id);
            if(uuid.equals(currData.getId())) {
                deleteNode(tailTemp);
            }
            tailTemp = tailTemp.next;
        }
    }
    
}