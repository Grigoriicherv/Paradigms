package queue;

//Invariant: FromHeadToTail.length >= 0 && for i in [0, tail): a[i] != null
//FromHeadTTail -- if head > tail {a[head], a[head + 1].. a[elements.length - 1], a[0].. a[tail - 1]}
//                 else {a[head].. a[tail - 1]}
public class ArrayQueueADT {
    private Object[] elements = new Object[5];
    private int tail;
    private int head;

    //Pred: element != null queue != null
    //Post: FromHeadTTail.length' == FromHeadTTail.length + 1 && a[tail] == element && tail' = tail + 1 &&
    // for i: 0 <= i < FromHeadTTail.length {a[i] == a'[i]}
    public static void enqueue(ArrayQueueADT queue, Object element) {
        if (ArrayQueueADT.size(queue) == queue.elements.length - 1) {
            ensureCapacity(queue);
        }
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.elements.length;
    }

    //Pred: FromHeadTTail.length >= 1 && queue != null
    //Post: R == a[head] && FromHeadTTail.length' == FromHeadTTail.length - 1 && head' = head + 1 &&
    // for i: 0 <= i < FromHeadTTail.length' {a[i + 1] == a'[i]}
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

    //Pred: FromHeadToTail.length >= 1 && queue != null
    //Post: R == a[head] && FromHeadToTail.length' == FromHeadToTail.length && for i: 0 <= i < n {a[i] == a'[i]}
    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    //Pred: queue != null
    //Post: R == FromHeadToTail && FromHeadToTail.length' == FromHeadToTail.length
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
    //Post: R = queue && queue == new ArrayQueueADT() && elements = Object[5]
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
    //Pred: True
    //Post: R = ([a[head], ... a[tail]] || [a[head], a[head + 1]... a[elements.size - 1], a[0]... a[tail - 1]])
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
