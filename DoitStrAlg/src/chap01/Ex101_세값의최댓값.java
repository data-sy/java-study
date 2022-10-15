package chap01;

import java.util.Scanner;

public class Ex101_세값의최댓값 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("a의 값 : ");
		int a = sc.nextInt();
		System.out.print("b의 값 : ");
		int b = sc.nextInt();
		System.out.print("c의 값 : ");
		int c = sc.nextInt();
		
		int max = a;
		if (b>max) {
			max = b;
		}
		if (c>max) {
			max = c;
		}
		
		System.out.println(String.format("최댓값은 %d 입니다.", max));

	}

}
