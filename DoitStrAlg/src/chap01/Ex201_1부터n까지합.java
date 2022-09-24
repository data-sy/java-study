package chap01;

import java.util.Scanner;

public class Ex201_1부터n까지합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1부터 n까지의 합");
		System.out.print("n값 : ");
		int n = sc.nextInt();
		int sum = 0;
		
		// while문
		int i = 1;
		while (i<=n) {
			sum+=i;
			i++;
		}
		System.out.println(String.format("(while) 1부터 %d까지 합은 %d입니다.", n, sum));
		// 최종 i값이 n+1
		System.out.println("(while) 최종 i : " + i);


		// for문
		sum = 0;
		for (i = 1; i<=n; i++) {
			sum+=i;
		}
		System.out.println(String.format("(for) 1부터 %d까지 합은 %d입니다.", n, sum));
		
		
		// 가우스 덧셈 31p
		sum = n*(n+1)/2;
		System.out.println(String.format("(가우스덧셈) 1부터 %d까지 합은 %d입니다.", n, sum));
		

	}

}
