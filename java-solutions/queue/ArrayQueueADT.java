package queue;

//Invariant: size() >= 0 && for i in [0, size()): a[i] != null
public class ArrayQueueADT {
    private Object[] elements = new Object[5];
    private int tail;
    private int head;

    //Pred: element != null && queue != null
    //Post: size()' == size() + 1 && a[tail] == element
    public static void enqueue(ArrayQueueADT queue, Object element) {
        if (ArrayQueueADT.size(queue) == queue.elements.length - 1) {
            ensureCapacity(queue);
        }
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    //Pred: size() >= 1 && queue != null
    //Post: R == a[head] && size()' == size() - 1
    public static Object dequeue(ArrayQueueADT queue) {

        Object object = queue.elements[queue.head];
        queue.head = (queue.head + 1) % queue.elements.length;
        return object;
    }

    //Pred: queue != null
    //Post: R = (head == tail)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.head == queue.tail;
    }

    //Pred: size >= 1 && queue != null
    //Post: R == a[head] && size()' == size()
    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    //Pred: queue != null
    //Post: R == {from head to tail} && size()' == size()
    public static int size(ArrayQueueADT queue) {

        if (queue.head > queue.tail) {
            return queue.elements.length - queue.head + queue.tail;
        } else
            return queue.tail - queue.head;
    }

    //Pred: queue != null
    //Post: tail == 0 && head == 0 && elements == Object[5]
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[5];
        queue.tail = 0;
        queue.head = 0;
    }

    //Pred: true
    //Post: R = queue && queue == ArrayQueueADT() && elements = Object[5]
    public static ArrayQueueADT create() {
        ArrayQueueADT queue = new ArrayQueueADT();
        queue.elements = new Object[5];
        return queue;
    }

    //Pred: queue != 0
    //Post: head == 0 && tail == elements.length-1 && elements == Object[elements.length * 2]
    private static void ensureCapacity(ArrayQueueADT queue) {
        Object[] elementsNew = new Object[queue.elements.length * 2];
        System.arraycopy(queue.elements, queue.head, elementsNew, 0, queue.elements.length - queue.head);
        System.arraycopy(queue.elements, 0, elementsNew, queue.elements.length - queue.head, queue.head);
        queue.tail = queue.elements.length - 1;
        queue.elements = elementsNew;
        queue.head = 0;


    }
    //Pred: queue != null
    //Post: R = [arr[head], ... arr[tail]]
    public static Object[] toArray(ArrayQueueADT queue){
        Object[] elementsNew = new Object[size(queue)];
        if (queue.tail >= queue.head){
            System.arraycopy(queue.elements, queue.head, elementsNew, 0, queue.tail - queue.head);
        }
        else{
            System.arraycopy(queue.elements, queue.head, elementsNew, 0, queue.elements.length - queue.head);
            System.arraycopy(queue.elements, 0, elementsNew, queue.elements.length - queue.head, queue.tail);
        }
        return (elementsNew);
    }

}
