package queue;

public abstract class AbstractQueue implements Queue{
    protected int head;
    protected int tail;
    public int size() {
        return tail - head;
    }

    public boolean isEmpty() {
        return head == tail;
    }

}
