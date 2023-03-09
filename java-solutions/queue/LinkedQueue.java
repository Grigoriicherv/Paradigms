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
    public void clear(){
        Head = null;
        Tail = null;
        head = 0;
        tail = 0;
    }


    public int lastIndexOf(Object elem) {
        Elem temp = Head;
        int index = -1;
        int i = 0;
        do {
            if (temp == null){
                continue;
            }
            if (temp.value.equals(elem)) {
                index = i;
            }
            i++;
            temp = temp.next;
        }
        while (temp != null);
        return index;
    }

    public int indexOf(Object elem) {
        Elem temp = Head;
        int i = 0;
        do {
            if (temp == null){
                continue;
            }
            if (temp.value.equals(elem)) {
                return i;
            }
            i++;
            temp = temp.next;
        }
        while (temp != null);
        return -1;
    }

    public Object element(){
        return Head.value;
    }








}
