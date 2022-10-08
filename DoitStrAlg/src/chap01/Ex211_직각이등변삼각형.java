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

        // 내 풀이 : 0부터 돌리기 , 약간 식이 덜 직관적이야
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++)
                System.out.print('*');
            System.out.println();
        }
        System.out.println();

        // 책 풀이 : 관계를 더 깔끔하게 이해할 수 있어
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print('*');
            System.out.println();
        }
        
	}

}
