package chap01;

import java.util.Scanner;

public class Ex204_1부터n까지합2 {

	public static void main(String[] args) {
		// 조금 더 고도화
		// 1. 양의 정수 아니면 재입력 받기
		// 2. 그 값을 구하는 과정까지 출력
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1부터 n까지의 합");
		int n;
		
		// 1. 양의 정수 아니면 재입력 받기
		do {
			System.out.print("n값 : ");
			n = sc.nextInt();
		} while (n<=0);
		
		
		// 2. 그 값을 구하는 과정까지 출력

		// 시도1
		// for문 안에 if문 있으므로 n번 반복하는 동안 if판단도 n번
		int sum = 0;
		
		for (int i=1; i<=n; i++) {
			if (i<n) System.out.print(i+" + ");
			else System.out.print(i + " = ");
			sum+=i;
		}
		System.out.println(sum);
		// 그런데 'n-1번은 + 이고 마지막 한 번 ='를 위해 n번 전체를 판단하는 건 비효율적이야
		// 즉, n-1까지 for문 돌리고 마지막 하나는 따로 작성
		
		// 시도2
		int sum2 = 0;
		for (int i=1; i<n; i++) {
			System.out.print(i+" + ");
			sum2+=i;
		}
		System.out.print(n + " = ");
		sum2+=n;
		System.out.println(sum2);
		// 이러면 for반복도 n-1번으로 줄고 if판단도 0번으로 줄어
		// 다만 마지막(=) 수행 1번 하므로 중간 과정의 횟수는 상쇄
		
		// 시도3
		// 마지막 문장을 더 깔끔하게 쓸 수 있어
		// 단순 대입(=) 혹은 복합 대입(+=, /= 등) 연산자를 사용하면 대입한 뒤 왼쪽 피연산자의 형,값을 얻을 수 있음
		System.out.print(n + " = " + (sum2+=n));
		
		
	}

}
