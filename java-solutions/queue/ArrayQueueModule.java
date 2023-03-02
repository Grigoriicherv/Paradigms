package queue;


//Invariant: size() >= 0 && for i in [0, size()): a[i] != null
public class ArrayQueueModule {
    private static Object[] elements = new Object[5];
    private static int tail;
    private static int head;

    //Pred: element != null
    //Post: size()' == size() + 1 && a[tail] == element
    public static void enqueue(Object element) {
        if (size() == elements.length - 1) {
            ensureCapacity();
        }
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    //Pred: size() >= 1
    //Post: R == a[head] && size()' == size() - 1
    public static Object dequeue() {
        Object object = elements[head];

        head = (head + 1) % elements.length;
        return object;
    }

    //Pred: true
    //Post: R = (head == tail)
    public static boolean isEmpty() {
        return head == tail;
    }

    //Pred: size >= 1
    //Post: R == a[head] && size()' == size()
    public static Object element() {

        return elements[head];
    }

    //Pred: true
    //Post: R == {from head to tail} && size()' == size()
    public static int size() {

        if (head > tail) {
            return elements.length - head + tail;
        } else
            return tail - head;
    }

    //Pred: true
    //Post: tail == 0 && head == 0 && elements == Object[5]
    public static void clear() {
        elements = new Object[5];
        tail = 0;
        head = 0;
    }

    //Pred: true
    //Post: head == 0 && tail == elements.length-1 && elements == Object[elements.length * 2]
    private static void ensureCapacity() {
        Object[] elementsNew = new Object[elements.length * 2];
        System.arraycopy(elements, head, elementsNew, 0, elements.length - head);
        System.arraycopy(elements, 0, elementsNew, elements.length - head, head);
        tail = elements.length - 1;
        elements = elementsNew;
        head = 0;

    }
    //Pred: true
    //Post: R = [arr[head], ... arr[tail]]
    public static Object[] toArray(){

        Object[] elementsNew = new Object[size()];
        if (tail >= head){
            System.arraycopy(elements, head, elementsNew, 0, tail - head);
        }
        else{
            System.arraycopy(elements, head, elementsNew, 0, elements.length - head);
            System.arraycopy(elements, 0, elementsNew, elements.length - head, tail);
        }
        return (elementsNew);
    }
}


