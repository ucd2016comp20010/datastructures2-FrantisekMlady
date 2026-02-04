package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return this.element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return this.next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            this.next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E get(int position) {
        Node<E> node = head;
        if (head == null || size <= position || position < 0){
            return null;
        }

        for(int i = 0; i < position; i++){
            node = node.getNext();
        }

        return node.getElement();
    }

    @Override
    public void add(int position, E e) {
        Node<E> node = head;
        if (head == null || position <= 0){
            addFirst(e);
            return;
        }

        if(position >= size){
            addLast(e);
            return;
        }
        for(int i = 0; i < position-1; i++){
            node = node.getNext();
        }

        node.setNext(new Node<>(e, node.getNext()));
        size++;

    }



    @Override
    public void addFirst(E e) {
        head = new Node<>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {

        if (head == null) {
            addFirst(e);
            return;
        }

        Node<E> node = head;
        while (node.getNext()!=null){
            node = node.getNext();
        }
        node.setNext(new Node<>(e,null));
        size++;
    }

    @Override
    public E remove(int position) {
        if (position <= 0){
            return removeFirst();
        }
        if (position >= size){
            return removeLast();
        }
        Node<E> node = head;
        for(int i = 0; i < position-1; i++){
            node = node.getNext();
        }
        E removedElement = node.getNext().getElement();
        node.next = node.getNext().getNext();
        size--;
        return removedElement;


    }

    @Override
    public E removeFirst() {
        if(head!= null) {
            E headElement = head.getElement();
            head = head.getNext();
            size--;
            return headElement;

        }

        return null;
    }

    @Override
    public E removeLast() {
        if(head!= null){

            if(head.getNext() == null){
                E lastElement = head.getElement();
                head = null;
                size--;
                return lastElement;
            }

            Node<E> node = head;
            while (node.getNext().getNext()!=null) {
                node = node.getNext();
            }
            E lastElement = node.getNext().getElement();
            node.setNext(null);
            size--;
            return lastElement;

        }
        return null;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(0);
        System.out.println(ll);

    }
}
