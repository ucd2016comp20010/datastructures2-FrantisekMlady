package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.CircularlyLinkedList;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {
    private CircularlyLinkedList<E> ll = new CircularlyLinkedList<>();
    public static void main(String[] args) {
        LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<>();
        lcq.enqueue(1);
        lcq.enqueue(2);
        lcq.enqueue(3);
        System.out.println(lcq.first());
        lcq.rotate();
        System.out.println(lcq.first());
        System.out.println(lcq.dequeue());
        System.out.println(lcq.dequeue());
        System.out.println(lcq.dequeue());

    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        ll.addLast(e);

    }

    @Override
    public E first() {
        return ll.first();
    }

    @Override
    public E dequeue() {
        return ll.removeFirst();
    }
    public void rotate() {
        ll.rotate();
    }

}
