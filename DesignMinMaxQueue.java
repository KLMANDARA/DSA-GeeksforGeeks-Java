import java.util.*;

class SpecialQueue {
    private Queue<Integer> queue;
    private Deque<Integer> minDeque;
    private Deque<Integer> maxDeque;

    public SpecialQueue() {
        queue = new LinkedList<>();
        minDeque = new LinkedList<>();
        maxDeque = new LinkedList<>();
    }

    public void enqueue(int x) {
        queue.offer(x);

        // Maintain increasing order in minDeque
        while (!minDeque.isEmpty() && minDeque.peekLast() > x) {
            minDeque.pollLast();
        }
        minDeque.offerLast(x);

        // Maintain decreasing order in maxDeque
        while (!maxDeque.isEmpty() && maxDeque.peekLast() < x) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(x);
    }

    public void dequeue() {
        if (queue.isEmpty()) return;

        int removed = queue.poll();

        // Update minDeque if front matches removed
        if (!minDeque.isEmpty() && minDeque.peekFirst() == removed) {
            minDeque.pollFirst();
        }

        // Update maxDeque if front matches removed
        if (!maxDeque.isEmpty() && maxDeque.peekFirst() == removed) {
            maxDeque.pollFirst();
        }
    }

    public int getFront() {
        return queue.peek(); // Queue is guaranteed to be non-empty
    }

    public int getMin() {
        return minDeque.peekFirst(); // Min is at front
    }

    public int getMax() {
        return maxDeque.peekFirst(); // Max is at front
    }
}
