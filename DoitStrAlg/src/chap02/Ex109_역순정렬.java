package chap02;

import java.util.Arrays;
import java.util.Scanner;

public class Ex109_역순정렬 {

    static void swap(int[] a, int idx1, int idx2) {
        int temp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = temp;
    }

    static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++)
            swap(a, i, a.length - i - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("배열 길이: ");
        int n = sc.nextInt(); 

        int[] x = new int[n]; 

        for (int i = 0; i < n; i++) {
            System.out.print("x[" + i + "] : ");
            x[i] = sc.nextInt();
        }

        reverse(x);
        // reverse, swap 이 2회에 걸쳐 차례로 배열 참조

        System.out.println("요소를 역순으로 정렬");
        System.out.println("x = " + Arrays.toString(x));
    }

}
