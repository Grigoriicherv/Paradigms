package queue;

import java.util.Arrays;

public class TestArrayQueueModule {
    public static void main(String[] args) {

        System.out.println(ArrayQueueModule.isEmpty());
        for (int i = 0; i < 4; i++) {
            ArrayQueueModule.enqueue(i);
        }
        System.out.println(ArrayQueueModule.dequeue());
        System.out.println(ArrayQueueModule.element());
        System.out.println(ArrayQueueModule.dequeue());
        ArrayQueueModule.enqueue(100);
        System.out.println(ArrayQueueModule.size());
        System.out.println(ArrayQueueModule.element());
        for (int i = 2; i < 7; i++) {
            ArrayQueueModule.enqueue(i + 7);
        }
        System.out.println(Arrays.toString(ArrayQueueModule.toArray()));
        System.out.println(ArrayQueueModule.isEmpty());
        System.out.println(ArrayQueueModule.size());
        System.out.println(ArrayQueueModule.element());
        ArrayQueueModule.clear();
        System.out.println(ArrayQueueModule.size());
        for (int i = 2; i < 7; i++) {
            ArrayQueueModule.enqueue(i + 7);
        }

        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.dequeue());
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
