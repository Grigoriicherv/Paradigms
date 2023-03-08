package queue;

//Invariant: FromHeadToTail.length >= 0 && for i in [0, tail): a[i] != null
//FromHeadTTail -- if head > tail {a[head], a[head + 1].. a[elements.length - 1], a[0].. a[tail - 1]}
//                 else {a[head].. a[tail - 1]}
public class ArrayQueue extends AbstractQueue{

    private Object[] elements = new Object[5];

    //Pred: element != null
    //Post: FromHeadTTail.length' == FromHeadTTail.length + 1 && a[tail] == element && tail' = tail + 1 &&
    // for i: 0 <= i < FromHeadTTail.length {a[i] == a'[i]}
    public void enqueue(Object element) {

        if (size() == elements.length - 1) {
            ensureCapacity();
        }
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    //Pred: FromHeadTTail.length >= 1
    //Post: R == a[head] && FromHeadTTail.length' == FromHeadTTail.length - 1 && head' = head + 1 &&
    // for i: 0 <= i < FromHeadTTail.length' {a[i + 1] == a'[i]}
    public Object dequeue() {
        Object object = elements[head];
        head = (head + 1) % elements.length;
        return object;
    }
    //Pred: true
    //Post: R = (head == tail)
//    public boolean isEmpty() {
//        return head == tail;
//    }
    //Pred: true
    //Post: tail == 0 && head == 0 && elements == Object[5]
//    public static void clear() {
//        elements = new Object[5];
//        tail = 0;
//        head = 0;
//    }




    //Pred: FromHeadToTail.length >= 1
    //Post: R == a[head] && FromHeadToTail.length' == FromHeadToTail.length && for i: 0 <= i < n {a[i] == a'[i]}
    public Object element() {
        return elements[head];
    }

    //Pred: True
    //Post: R == FromHeadToTail && FromHeadToTail.length' == FromHeadToTail.length
    public int size() {
        if (head > tail) {
            return elements.length - head + tail;
        } else
            return super.size();
    }

    //Pred: True
    //Post: head == 0 && tail == elements.length-1 && elements == Object[elements.length * 2]
    private void ensureCapacity() {
        Object[] elementsNew = new Object[elements.length * 2];
        System.arraycopy(elements, head, elementsNew, 0, elements.length - head);
        System.arraycopy(elements, 0, elementsNew, elements.length - head, head);
        tail = elements.length - 1;
        elements = elementsNew;
        head = 0;

    }
    //Pred: True
    //Post: R = ([a[head], ... a[tail]] || [a[head], a[head + 1]... a[elements.size - 1], a[0]... a[tail - 1]])
    public Object[] toArray(){
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
