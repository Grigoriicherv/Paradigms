package search;

public class BinarySearchMax {
    //Pred: exist k, so for all i and j : -1 < i < k < j < arr.length{(1) ((arr[i] < arr[i + 1] || arr[k - 1] < arr[k]) &&
    // (arr[j] < arr[j + 1] || j == arr.length - 1) && arr[k] > arr[k + 1] && arr[0] > arr[arr.length-1])
    // || (2) (k == arr.length - 1 && arr[k] is max && arr[r] >= arr[l])}
    // arr.length > 0
    //Post: R = k
    private static int iterativeSearch(int[] arr) {
        //Pred
        int l = 0;
        //Pred && l == 0
        int r = arr.length - 1;
        //Pred && l == 0 && r ==  arr.length - 1
        if (arr[r] >= arr[l]) {
            //Pred && l == 0 && r ==  arr.length - 1 && arr[r] >= arr[l]
            //Pred (2): {arr[r] >= arr[l]}
            //Pred (2): {arr[arr.length - 1] is max}
            return arr[r];
            //R = arr[r]
        }
        //Pred && l == 0 && r ==  arr.length - 1 -> Inv
        //  if arr.length == 1 {(2) arr[r] >= arr[l]} else {r - l >= 1}
        //  Pred(1) => arr[l'] > arr[r']
        //Inv: r' - l' >= 1 && arr[l'] > arr[r']
        while (r - l > 1) {
            //Inv && r - l > 1
            if (arr[l] > arr[l + (r - l) / 2]) {
                //r' - l' > 1 && arr[l'] > arr[l' + (r'-l')/2]
                r = l + (r - l) / 2;
                //arr[l'] > arr[r''] && r'' - l' >= 1 -> Inv
            } else {
                //r' - l' > 1 && arr[l' + (r'-l')/2] > arr[r']
                l = l + (r - l) / 2;
                //arr[l''] > arr[r'] && r' - l'' >=1 -> Inv
            }
            //Inv
        }
        //Inv && r - l <= 1 -> Post
        //  r - l == 1 && arr[l] > arr[r]
        //  arr[l] > arr[l + 1]
        //Post: arr[l] is max
        return arr[l];
        //R = arr[l]
    }

    //Pred: l <= idx(max_elem) <= r && exist k, so for all i and j : l-1 < i < k < j < r + 1{ (1) ((arr[i] < arr[i + 1]
    // || arr[k - 1] < arr[k]) && (arr[j] < arr[j + 1] || j == r) && arr[r - 1] < arr[r] && arr[k] > arr[k + 1]
    // && arr[0] > arr[arr.length-1]) || (2) (k + 1 == arr.length && arr[k] is max && arr[r] >= arr[l])}
    // arr.length > 0
    //Post: R = k
    private static int reccursiveSearch(int[] arr, int l, int r) {
        //Pred
        if (arr[r] >= arr[l]) {
            //Pred (2) && arr[r] >= arr[l] -> arr[r] is max
            return arr[r];
            //R = arr[r]
        }
        //Pred && arr[r] < arr[l] -> Pred (1)
        if (r - l == 1) {
            // Pred (1) && r - l == 1 -> r == l + 1 && arr[l] > arr[r] -> arr[l] is max
            return (arr[l]);
            // R = arr[l]
        }
        //Pred (1)
        if (arr[l] > arr[l + (r - l) / 2]) {
            //Pred (1) && arr[l'] > arr[l' + (r'-l')/2]
            r = l + (r - l) / 2;
            //Pred (1) && arr[l'] > arr[r'']
            return reccursiveSearch(arr, l, r);
            //R = reccursiveSearch(arr, l, r)
        } else {
            //Pred (1) && arr[l'] <= arr[l' + (r'-l')/2]
            l = l + (r - l) / 2;
            //Pred (1) && arr[l''] > arr[r']
            return reccursiveSearch(arr, l, r);
            //R = reccursiveSearch(arr, l, r)
        }

    }


    //Pred: for all i: -1 < i < arr.length  {arr[i] is int}
    //Post: R = back: for all i:  -1 < i < arr.length {back += arr[i]}
    private static int sum(int[] arr) {
        //Pred
        int back = 0;
        //Pred && back = 0
        int j = 0;
        //Pred && back == 0 && j = 0 ->
        //Inv: j <= arr.length && back == arr[0] + arr[1] +...+ arr[j-1]
        while (j < arr.length) {
            //j' < arr.length && Inv
            //j' < arr.length && back = arr[0] + arr[1] +...+ arr[j' - 1]
            back = back + arr[j];
            // j' < arr.length && back = arr[0] + arr[1] +...+ arr[j']
            j = j + 1;
            //j'' <= arr.length && back == arr[0] + arr[1] +...+ arr[j'' - 1]
        }
        //Inv && j >= arr.length -> Post
        //Post: j = arr.length && back == arr[0] + arr[1] +...+ arr[j-1]
        return back;
        //R = back
    }

    //Pred: all i ParseInt(args[i]) == true && exist k, so for all i and j : -1 < i < k < j < args.length{ (1) (args[i] < args[i + 1]
    // && args[k - 1] < args[k] && args[j] < args[j + 1] && args[arr.length - 2] < args[arr.length - 1] && args[k] > args[k + 1]
    // && args[0] > arr[args.length-1]) or (2) (k + 1 == args.length && args[k] is max)}
    // args.length > 0
    //Post: stdout: R
    public static void main(String[] args) {
        //Pred
        int[] arr = new int[args.length];
        //arr.length == args.length && Pred
        int i = 0;
        //arr.length == args.length && Pred && i == 0 -> Inv
        //Inv: i <= args.length && for all j: -1 < j < i' {arr[j] is int}
        while (i < args.length) {
            // i' < args.length
            arr[i] = Integer.parseInt(args[i++]);
            // i'' == i' + 1  && arr[i'] == args[i'] -> Inv
        }
        // Inv && !cond -> i ==  arr.length
        if (sum(arr) % 2 == 0) {
            //sum(arr) % 2 == 0
            System.out.println(reccursiveSearch(arr, 0, arr.length - 1));
            //R = System.out.println(reccursiveSearch(arr, 0, arr.length-1))
        } else {
            //sum(arr) % 2 != 0
            System.out.println(iterativeSearch(arr));
            //R = System.out.println(iterativeSearch(arr))
        }
        //R = k
    }
}
