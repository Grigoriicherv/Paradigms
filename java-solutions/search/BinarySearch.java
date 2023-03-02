package search;

import java.util.Arrays;

public class BinarySearch {
    //Pred: for all i and j: i > j -> arr[i] >= arr[j]
    //Post: R = r: arr[r] <= key && arr[r+1] > key
    private static int iterativeSearch(int key, int[] arr) {
        //Pred
        int l = -1;
        // l = -1 && Pred
        int r = arr.length;
        // r = arr.length && l = -1 && Pred -> Inv
        // Inv: r' - l' >= 1 && arr[r'] <= key && arr[l'] > key
        while (r - l > 1) {
            // Inv && r'-l' > 1
            // r' - l' > 1 && arr[r'] <= key && arr[l'] > key
            // r' - l' > 1 -> (r' - l')/2 >= 1
            if (arr[l + (r - l) / 2] <= key) {
                //arr[l'] >= arr[l' + (r'-l')/2] >= arr[r'] && arr[l + (r-l)/2] <= key
                //arr[l'] > arr[l' + (r'-l')/2] >= arr[r'] && arr[l + (r-l)/2] <= key
                r = l + (r - l) / 2;
                //arr[l'] > arr[r''] >= arr[r'] && arr[r''] <= key -> Inv
            } else {
                //arr[l'] >= arr[l' + (r'-l')/2] >= arr[r'] && !arr[l + (r-l)/2] <= key
                //arr[l'] >= arr[l' + (r'-l')/2] > arr[r'] && arr[l + (r-l)/2] > key
                l = l + (r - l) / 2;
                //arr[l'] >= arr[l''] > arr[r'] && arr[l''] > key -> Inv
            }
            //Inv
        }
        // Inv && !cond -> arr[r] <= key && arr[r+1] > key
        // arr[r] <= key && arr[r+1] > key
        return r;
        //R = r
    }

    //Pred: for all i and j: i > j -> arr[i] >= arr[j] && arr[l'] > key >= arr[r'] && r - l >= 1
    //Post: R = r: arr[r] <= key && arr[r+1] > key
    private static int reccursiveSearch(int key, int[] arr, int r, int l) {
        //Pred
        if (r - l == 1) {
            //Pred && r' - l' == 1
            //Pred && r' - l' == 1 -> arr[r + 1] > key >= arr[r]
            return r;
            //R = r
        }
        //r' - l' > 1
        if (arr[l + (r - l) / 2] <= key) {
            //r' - l' > 1 && arr[l' + (r'-l')/2] <= key
            r = l + (r - l) / 2;
            //r' - l' > 1 && arr[r''] <= key
            return reccursiveSearch(key, arr, r, l);
            // R = reccursiveSearch(key, arr, r, l)
        } else {
            //r' - l' > 1 && arr[l' + (r'-l')/2] > key
            l = l + (r - l) / 2;
            //r' - l' > 1 && arr[l''] > key
            return reccursiveSearch(key, arr, r, l);
            //R =  reccursiveSearch(key, arr, r, l)
        }
    }

    //Pred: arg.length > 0 && for all i: 0 < i < j < arg.length -> (arg[i] is int && arg[i] >= arg[j]) && arg[0] is int
    //Post: stdout: (r : arr[r] <= key && arr[r+1] > key || 0)
    public static void main(String[] arg) {
        //Pred
        int key = Integer.parseInt(arg[0]);
        //key == Integer.parseInt(arg[0])
        int[] arr = new int[arg.length - 1];
        //arr.length == arg.length-1
        if (arg.length <= 1) {
            // arg.length<=1
            System.out.println(0);
            //r = 0
        } else {
            //Pred && arg.length > 1
            int i = 0;
            // Pred && arg.length > 1 && i = 0
            //Inv: i <= args.length - 1
            while (i < arg.length - 1) {
                //i' < arg.length - 1
                arr[i] = Integer.parseInt(arg[i + 1]);
                //arr[i'] = Integer.parseInt(arg[i' + 1]);
                i++;
                //i'' == i' + 1 -> Inv

            }
            // i == arg.length - 1
            arr = Arrays.copyOf(arr, i);
            //arr.length == i2
            System.out.println(reccursiveSearch(key, arr, arr.length, -1));
            //r = reccursiveSearch(key, arr, arr.length, -1)
        }

    }
}
