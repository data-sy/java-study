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
		
		// 순차구조, 선택구조
		
		// System.in은 키보드와 연결된 표준 입력 스트림
		
		
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
