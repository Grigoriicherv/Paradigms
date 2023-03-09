package queue;

public class LinkedQueue extends AbstractQueue{
    private Elem Head;
    private Elem Tail;
    private static class Elem {
        private Elem next;
        private final Object value;
        Elem (Object element){
            value = element;
        }
    }

    public void enqueue (Object element){
        tail ++;
        if(Head == null){
            Head = new Elem(element);
            Tail = Head;
        }
        else{
            Tail.next = new Elem(element);
            Tail = Tail.next;
        }

    }
    public Object dequeue(){
        head ++;
        Object element = Head.value;
        Head = Head.next;
        return element;
    }

    @Override
    public Object[] toArray() {
        Object[] elementsNew = new Object[size()];
        Elem temp = Head;
        int i = 0;
        do{
            elementsNew[i++] = temp.value;
            temp = temp.next;
        }
        while (temp != null);
        return elementsNew;
    }

    public Object element(){
        return Head.value;
    }








}
