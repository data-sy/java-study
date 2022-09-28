package chap01;

import java.util.Scanner;

public class Ex211_직각이등변삼각형 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n;


        do {
            System.out.print("몇 단 삼각형?: ");
            n = sc.nextInt();
        } while (n <= 0);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print('*');
            System.out.println();
        }

	}

}
