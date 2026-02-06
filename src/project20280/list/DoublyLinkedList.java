package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newNode = new Node<>(e, pred, succ);
        pred.next = newNode;
        succ.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if(i <= 0){return first();}
        if (i > size-1){return last();}
        Node<E> node;
        if(i < size/2) {
            node = head.getNext();
            for (int j = 0; j < i; j++) {
                node = node.next;
            }
        }
        else{
            node = tail.getPrev();
            for (int j = size-1; j > i; j--) {
                node = node.prev;
            }
        }
        return node.getData();
    }

    @Override
    public void add(int i, E e) {
        if(i <= 0){addFirst(e); return;}
        if (i > size-1){addLast(e); return;}
        Node<E> node = head;
        for(int j = 0; j < i; j++){
            node = node.next;
        }
        addBetween(e, node, node.getNext());
    }

    @Override
    public E remove(int i) {
        if(i <= 0){return removeFirst();}
        if (i > size-1){return removeLast();}
        Node<E> node = head.getNext();
        for(int j = 0; j < i; j++){
            node = node.next;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.getData();
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        Node<E> node = head;
        while(!node.equals(n)){
            if(node.equals(tail)){return null;}
            node = node.next;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.getData();
    }

    public E first() {
        if (isEmpty()) {return null;}
        return head.next.getData();
    }

    public E last() {
        if (isEmpty()) {return null;}
        return tail.prev.getData();
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){return null;}
        if(size == 1){
            Node<E> removed = head.getNext();
            head.next = tail;
            tail.prev = head;
            size--;
            return removed.getData();
        }
        Node<E> removed = head.getNext();
        head.next = removed.getNext();
        removed.next.prev = head;
        size--;
        return removed.getData();
    }

    @Override
    public E removeLast() {
        if(size <= 1){return removeFirst();}
        Node<E> removed = tail.getPrev();
        removed.prev.next = tail;
        tail.prev = removed.prev;
        size--;
        return removed.getData();
    }

    @Override
    public void addLast(E e) {
        if (isEmpty()){addFirst(e); return;}
        Node<E> newNode = new Node<>(e, tail.prev, tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    @Override
    public void addFirst(E e) {
        if(isEmpty()) {
            Node<E> newNode = new Node<>(e, head, tail);
            head.next = newNode;
            tail.prev = newNode;
            size++;
            return;
        }
        Node<E> newNode = new Node<>(e, head, head.next);
        head.next = newNode;
        head.next.prev = newNode;
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}