package example.toong.recyclerviewmutilpleitemtypesupportshowhideitemtype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Scanner in = new Scanner(System.in);
        int n = 5;
        int x = 8;

        //System.out.println(""+n+" "+x);
        int maxOfNumberHasNNumber = findMaxOfNumberHasNNumber(n);

        //System.out.println(""+maxOfNumberHasNNumber);
        int max = -1;
        for (int i = 1; i <= maxOfNumberHasNNumber; i++) {
            int result = checkCyclic(i, n, x);
            if(result > max){
                max = result;
            }
        }
        System.out.println(max);
    }

    static int findMaxOfNumberHasNNumber(int n) {
        int value = 1;
        for (int i = 0; i < n + 1; i++) {
            value *= 10;
        }
        return value - 1;
    }

    static boolean isTwoArrayContainsSameValue(int[] arr1, int arr2[]){
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    static String addPrefix0(String oldString, int length) {
        StringBuilder newString = new StringBuilder();
        int oldLength = oldString.length();
        for (int i = oldLength; i < length; i++) {
            newString.append("0");
        }
        newString.append(oldString);
        return newString.toString();
    }

    static boolean checkCyclicInBase(int[] multiplyResultInInt, int base, int numberLength){
        String numberInBase = Integer.toString(multiplyResultInInt[0], base);
        if (numberInBase.length() > numberLength) {
            return false;
        } else if (numberInBase.length() < numberLength) {
            numberInBase = addPrefix0(numberInBase, numberLength);
        }

        String[] multiplyResultInBase = new String[numberLength];
        multiplyResultInBase[0] = numberInBase;
        for (int i = 1; i < numberLength; i++) {
            String resultInBase = Integer.toString(multiplyResultInInt[i], base);
            if (resultInBase.length() < numberLength) {
                resultInBase = addPrefix0(numberInBase, numberLength);
            }
            multiplyResultInBase[i] = resultInBase;
        }

        int[] headNumberInMultiplyResult = new int[numberLength];
        for (int i = 0; i < numberLength; i++) {
            headNumberInMultiplyResult[i] = Integer.parseInt(""+multiplyResultInBase[i].charAt(0));
        }

        int[] numberInArray = new int[numberLength];
        for (int i = 0; i < numberLength; i++) {
            numberInArray[i] = Integer.parseInt("" + numberInBase.charAt(i));
        }

        if(isTwoArrayContainsSameValue(headNumberInMultiplyResult, numberInArray)){
            System.out.println("\nLinh "+numberInBase + " -- "+base);
            for (int i = 0; i < numberLength; i++) {
                System.out.print(" "+headNumberInMultiplyResult[i]);
            }
            return true;
        }
        return false;
    }

    static int checkCyclic(int number, int numberLength, int maxBase) {
        // get multiply result in integer
        int[] multiplyResultInInt = new int[numberLength];
        multiplyResultInInt[0] = number;
        for (int i = 1; i < numberLength; i++) {
            multiplyResultInInt[i] = number * (i + 1);
        }

        // start at base 2
        for (int j = maxBase-1; j >= 2; j--) {
            if (checkCyclicInBase(multiplyResultInInt, j, numberLength)) {
                return j;
            }
        }
        return -1;
    }
}
