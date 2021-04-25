import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    // construct an empty deque
    public Deque(){
        first = last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
            size = 1;
        }
        else {
            oldFirst.previous = first;
            ++size;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;
        if (isEmpty()) {
            first = last;
            size = 1;
        }
        else {
            oldLast.next = last;
            ++size;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        --size;
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = first = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        --size;
        Item item = last.item;
        last = last.previous;
        if (isEmpty()) last = first = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());
        deque.addFirst(3);
        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());
        deque.addFirst(4);
        deque.addLast(8);
        StdOut.print(deque.removeLast());
        StdOut.print(deque.removeLast());
        StdOut.print(deque.removeLast());
        StdOut.println();
        deque.addFirst(4);
        deque.addLast(8);
        deque.addFirst(3);
        for (Integer i:deque) {
            StdOut.print(deque.removeFirst());
        }
    }

}