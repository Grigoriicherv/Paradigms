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
    public Object element(){
        return Head.value;
    }








}
