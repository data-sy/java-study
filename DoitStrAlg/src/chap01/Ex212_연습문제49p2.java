package chap01;

import java.util.Scanner;

public class Ex212_연습문제49p2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n;
        do {
            System.out.print("몇 단 삼각형?: ");
            n = sc.nextInt();
        } while (n <= 0);
        
		//Q.15 n단 * 피라미드
        spira(n);
        System.out.println();
        
		//Q.16 n단 숫자 피라미드	
        npira(n);
        
	}
	// n단 * 피라미드
	static void spira(int n) {
		for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i; j++)
            	System.out.print(' ');
            for (int j = 1; j <= 2*i-1 ; j++)
            	System.out.print('*');
            // 뒷 공백은 굳이 필요 없지.
//            for (int j = 1; j <= n-i ; j++)
//            	System.out.print(' ');
            System.out.println();
        }
	}
	// n단 숫자 피라미드
	static void npira(int n) {
		for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i; j++)
            	System.out.print(' ');
            for (int j = 1; j <= 2*i-1 ; j++)
            	System.out.print(i%10);
            System.out.println();
        }
	}

}
