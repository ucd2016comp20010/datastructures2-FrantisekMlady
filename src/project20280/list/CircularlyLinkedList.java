package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (tail == null ||  i < 0){
            return null;
        }
        Node<E> node = tail.getNext();

        for(int j = 0; j < i; j++){
            node = node.getNext();
        }

        return node.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i <= 0){
            addFirst(e);
            return;
        }

        if(i >= size){
            addLast(e);
            return;
        }
        Node<E> node = tail.getNext();
        for (int j = 0; j < i - 1; j++) {
            node = node.getNext();
        }

        node.setNext(new Node<E>(e, node.getNext()));
        size++;
    }

    @Override
    public E remove(int i) {
        if (i <= 0){
            return removeFirst();
        }
        if (i >= size-1){
            return removeLast();
        }
        Node<E> node = tail.getNext();
        for(int j = 0; j < i-1; j++){
            node = node.getNext();
        }
        E removedElement = node.getNext().getData();
        node.setNext(node.getNext().getNext());
        size--;
        return removedElement;
    }

    public void rotate() {
        if(size == 0 || tail == null){return;}
        tail = tail.getNext();
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

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
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (tail == null) {return null;}

        Node<E> head = tail.getNext();
        if (head == tail) {
            E data = head.getData();
            tail = null;
            size--;
            return data;
        }

        E data = head.getData();
        tail.setNext(head.getNext());
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (tail == null) {return null;  }

        Node<E> head = tail.getNext();
        if (head == tail) {
            E data = tail.getData();
            tail = null;
            size--;
            return data;
        }

        Node<E> node = head;
        while (node.getNext() != tail) {
            node = node.getNext();
        }

        E data = tail.getData();
        node.setNext(head);
        tail = node;
        size--;
        return data;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, null);

        if (tail == null) {
            newNode.setNext(newNode);
            tail = newNode;
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
