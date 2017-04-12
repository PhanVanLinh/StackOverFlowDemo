package example.toong.recyclerviewmutilpleitemtypesupportshowhideitemtype;

import java.util.Scanner;
public class Main {
    static int findMaxPosition(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        int maxPosition = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[maxPosition] < arr[i]) {
                maxPosition = i;
            }
        }
        return maxPosition;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int maxValuePosition = findMaxPosition(a);

        for (int i = 0; i < m; i++) {
            a[maxValuePosition] *= k;
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = result | a[i];
        }

        System.out.println(result);

        //System.out.println(""+n+" "+m+" "+k);
    }
}
