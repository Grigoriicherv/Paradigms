package queue;

import java.util.Arrays;

public class TestArrayQueue {
    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        System.out.println(queue1.isEmpty());
        for (int i = 0; i < 4; i++) {
            queue1.enqueue(i);
        }
        System.out.println(queue1.dequeue());
        System.out.println(queue1.element());
        System.out.println(queue1.dequeue());
        queue1.enqueue(100);
        System.out.println(queue1.size());
        System.out.println(queue1.element());
        for (int i = 2; i < 7; i++) {
            queue1.enqueue(i + 7);
        }
        System.out.println(Arrays.toString(queue1.toArray()));
        System.out.println(queue1.isEmpty());
        System.out.println(queue1.size());
        System.out.println(queue1.element());
        queue1.clear();
        System.out.println(queue1.size());
        for (int i = 2; i < 7; i++) {
            queue1.enqueue(i + 7);
        }
        while (!queue1.isEmpty()) {
            System.out.println(queue1.dequeue());
        }
    }
    //      out
//        true
//        0
//        1
//        1
//        3
//        2
//        [2, 3, 100, 9, 10, 11, 12, 13]
//        false
//        8
//        2
//        0
//        9
//        10
//        11
//        12
//        13
}
