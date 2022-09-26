package chap01;

import java.util.Scanner;

public class Ex206_w마다줄바꿔별찍기 {

	public static void main(String[] args) {
		// n개 출력, w마다 줄바꿈
		// 입력조건 : n 양수 아니면 재입력, w 양수 아니거나 n보다 크면 재입력
		Scanner sc = new Scanner(System.in);
		int n, w;
		do {
			System.out.print("n : ");
			n = sc.nextInt();			
		} while(n<=0);
		do {
			System.out.print("w : ");
			w = sc.nextInt();
		} while (w<=0 || w>n);
		
		
		// 우선 책 안보고 만들어보면
		// "*"*w; 쓰니까 빨간 줄  (∵)자바에서는 문자열 곱하기 안 됨. + 만 됨
		// 문자열 반복은 .repeat메서드 사용
		// 메서드 사용 안했다면 for문 안에 for문 한번 더 만들어서 "*"를 ln없이 w번 출력했을 듯
		for (int i=0; i<n/w; i++) {
			System.out.println("*".repeat(w));
		}
		System.out.println("*".repeat(n%w));
		System.out.println();
		// for반복 w/n번, 나눗셈 1번, (repeat메서드는 어떻게 세어야 할 지 모르겠음)
	
		
		// 책
		// n개 반복문으로 출력하는데 나머지가 w-1로 꽉 찰 때 줄바꿈 한 번
		for (int i=0; i<n; i++) {
			System.out.print("*");
			if (i%w==w-1) System.out.println();
		}
		if (n%w!=0) System.out.println(); //마지막 줄 중간에 끊기면 한 번 줄바꿈 하고 마무리
		// for반복 n번, if판단 n+1번 => 비효율
		
		System.out.println();
		
		
		// 개선코드
			// 내 풀이에서 마지막 줄 줄바꿈에 대한 조건문 추가
		for (int i=0; i<n/w; i++) {
			System.out.println("*".repeat(w));
		}
		int rest= n%w;
		if (rest!=0) System.out.println("*".repeat(rest));	
		// for반복 w/n번
		
		System.out.println();
		
		// test 0번 반복이 가능한가
		System.out.println("test ".repeat(1));
		System.out.println("test ".repeat(0));
		// 가능해. 하지만 빈줄 출력하고 한 줄 줄바꿈 하기 때문에 (커서 2번 엔터)
		// 아얘 출력하지 않는 것과는 다른 결과야. (커서 1번 엔터)
		// 그래서 개선코드에서 처럼 if (rest!=0) 조건 필요해

	}

}
