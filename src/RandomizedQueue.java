import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randQueue;
    private int size = 0;
    private int maxSize = 10;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randQueue = (Item[]) new Object[maxSize];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size >= maxSize) {
            Item[] bufferQueue = randQueue;
            maxSize = maxSize * 2;
            randQueue = (Item[]) new Object[maxSize];
            for (int i = 0; i < size; ++i) {
                randQueue[i] = bufferQueue[i];
            }
        }
        randQueue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int numberEl = StdRandom.uniform(0, size);
        //System.out.println("  -" + numberEl + "-  ");
        Item buffEl = randQueue[numberEl];
        if (size + 5 < maxSize/4) {
            Item[] bufferQueue = randQueue;
            maxSize = maxSize / 2;
            randQueue = (Item[]) new Object[maxSize];
            for (int i = 0; i < size; ++i) {
                randQueue[i] = bufferQueue[i];
            }
        }
        size--;
        randQueue[numberEl] = randQueue[size];
        randQueue[size] = null;
        return buffEl;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return randQueue[StdRandom.uniform(0, size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private int i = size;
        public boolean hasNext() { return i > 0; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (i == 0) throw new NoSuchElementException();
            return randQueue[--i];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        StdOut.println(randomizedQueue.size());
        StdOut.println(randomizedQueue.isEmpty());

        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(6);
        randomizedQueue.enqueue(8);

        for (Integer i:randomizedQueue) {
            StdOut.print(randomizedQueue.sample());
        }

        StdOut.println(randomizedQueue.size());
        StdOut.println(randomizedQueue.isEmpty());

        for (Integer i:randomizedQueue) {
            StdOut.print(randomizedQueue.dequeue());
        }

        StdOut.println(randomizedQueue.size());
        StdOut.println(randomizedQueue.isEmpty());

        //for (int i = 0; i < 10; ++i) {
        //    StdOut.print(randomizedQueue.sample());
        //}
    }

}