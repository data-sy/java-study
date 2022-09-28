package chap01;

import java.util.Scanner;

public class Ex212_연습문제49p {

	public static void main(String[] args) {
		
		//Q.14 직각이등변삼각형 출력을 메서드로
		
		Scanner sc = new Scanner(System.in);
        int n;

        do {
            System.out.print("몇 단 삼각형?: ");
            n = sc.nextInt();
        } while (n <= 0);
        
        triangleLB(n);
        System.out.println();
        triangleLU(n);
        System.out.println();
        triangleRU(n);
        System.out.println();
        triangleRB(n);


	}
	// 왼쪽 아래가 직각
	static void triangleLB(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print('*');
            System.out.println();
        }		
	}
	// 왼쪽 위가 직각
	static void triangleLU(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i+1 ; j++)
                System.out.print('*');
            System.out.println();
        }
	}
	// 오른쪽 위가 직각
	static void triangleRU(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i-1; j++)
            	System.out.print(" ");
            for (int j = 1; j <= n-i+1 ; j++)
            	System.out.print('*');
            System.out.println();
        }		
	}
	// 오른쪽 아래가 직각
	static void triangleRB(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i; j++)
            	System.out.print(" ");
            for (int j = 1; j <= i ; j++)
            	System.out.print('*');
            System.out.println();
        }		
	}
}
