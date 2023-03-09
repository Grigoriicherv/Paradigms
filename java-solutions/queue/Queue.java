package queue;
//Model:
//     a[a0, a1, a2 ... an]
//     n + 1 -- length of queue
//Invariant:
//     n >= 0 && for i : 0 <= i <= n {a[i] != null} && a[0] -- head && a[n] -- tail
public interface Queue {

    // Pred: element != null
    // Post: n' = n + 1 && a[n'] == element && for i: 0 <= i <= n {a[i] == a'[i]}
    void enqueue(Object element);

    // Pred: n >= 1
    // Post: R = a[0] && for i: 0 <= i <= n' {a[i] == a'[i]}
    Object element();

    // Pred: n >= 1
    // Post: R = a[0] && n' = n - 1 && for i: 0 <= i <= n' {a[i + 1] == a'[i]}
    Object dequeue();
    // Pred: True
    // Post: result = n && for i: 0 <= i <= n {a[i] == a'[i]}
    int size();
    // Pred: True
    // Post: result = (n == 0) && for i: 0 <= i <= n {a[i] == a'[i]}
    boolean isEmpty();
    // Pred: True
    // Post: n = 0
    void clear();
    //Pred: True
    //Post: R = [a[0], a[1], a[2]... a[n]]
    Object[] toArray();
}

