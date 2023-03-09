package queue;

import java.util.Arrays;

public class TestArrayQueueADT {
    public static void main(String[] args) {
        ArrayQueueADT queue1 = ArrayQueueADT.create();
        System.out.println(ArrayQueueADT.isEmpty(queue1));
        for (int i = 0; i < 4; i++) {
            ArrayQueueADT.enqueue(queue1, i);
        }
        System.out.println(ArrayQueueADT.dequeue(queue1));
        System.out.println(ArrayQueueADT.element(queue1));
        System.out.println(ArrayQueueADT.dequeue(queue1));
        ArrayQueueADT.enqueue(queue1, 100);
        System.out.println(ArrayQueueADT.size(queue1));
        System.out.println(ArrayQueueADT.element(queue1));
        for (int i = 2; i < 7; i++) {
            ArrayQueueADT.enqueue(queue1, i + 7);
        }

        System.out.println(ArrayQueueADT.isEmpty(queue1));
        System.out.println(ArrayQueueADT.size(queue1));
        System.out.println(ArrayQueueADT.element(queue1));
        ArrayQueueADT.clear(queue1);
        System.out.println(ArrayQueueADT.size(queue1));
        for (int i = 2; i < 7; i++) {
            ArrayQueueADT.enqueue(queue1, i + 7);
        }
        while (!ArrayQueueADT.isEmpty(queue1)) {
            System.out.println(ArrayQueueADT.dequeue(queue1));
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
}
