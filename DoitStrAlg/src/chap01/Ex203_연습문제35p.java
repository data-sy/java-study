package chap01;

import java.util.Scanner;

public class Ex203_연습문제35p {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("a : ");
		int a = sc.nextInt();
		System.out.print("b : ");
		int b = sc.nextInt();
		
		while (a>=b) {
			System.out.println("a보다 큰 값을 입력하세요!");
			System.out.print("b : ");
			b = sc.nextInt();
		}
		
		System.out.println("b-a는 " + (b-a) +"입니다.");
		// b-a 예 괄호 안 치니까 빨간줄
		// 괄호 없으면 왼-오 순서대로 진행 문자열의 + 가 되어서 b-a 숫자 계산에서 오류 나는 것
				
		// do-while을 배운 뒤 나온 연습문제라서 do while 시도하려 했지마
		// a보다 큰 값 입력하라는 문장은 조건을 체크한 뒤 나와야 하는 거라서 넣을 자리 못 찾음
		// 지금 식이 최선이지 않을까?
		// 답에서도 그냥 while문 사용
		// 대신, while (true)로 들어가서 조건식에 맞으면 break를 (syso a보다 큰~ 문장보다 앞에) 줘서
		// sc. 한 큐에 해결!
		
		// 답안
		int c;
		while (true) {
			System.out.print("c : ");
			c = sc.nextInt();
			if (c>a) break;
			System.out.println("a보다 큰 값을 입력하세요!");		
		}
		System.out.println("c-a는 " + (c-a) +"입니다.");
		// while은
		// 루프가 멈추기를 원하는 조건(p)가 있을 때
		// ~p를 while의 조건식으로 사용하기 보다는
		// 우선 true로 루프 들어오고 p를 만족하면 break 하는 방식으로 많이 사용하는 듯!
		
		
		// 자릿수 출력하는 프로그램
		System.out.print("양의 정수를 입력하세요 : ");
		int n = sc.nextInt();
		int i = 0;
		while(n>=1) {
			n /= 10;
			i++;
		}
		System.out.println("그 수는 " + i + "자리입니다.");
		
		
		// 답안
		// 문제의 의도는 양의 정수를 입력하지 않으면 재입력하는 시스템 + 경고문 없이 => do while 사용
		// 자릿수 부분은 맞으니까 [양의정수를 입력하지 않으면 재입력하는 프로그램을 do while로 만들기]만 해보자.
		do {
		System.out.print("입력하세요 : ");
		n = sc.nextInt();
		}
		while (n<=0);
		
		
		
		
	}

}
