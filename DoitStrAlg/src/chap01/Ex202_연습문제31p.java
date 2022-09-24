package chap01;

import java.util.Scanner;

public class Ex202_연습문제31p {

	public static void main(String[] args) {
		// 정수 a, b를 포함하여 그 사이의 모든 정수의 합을 구하여 반환하는 메서드 작성하기
		// a와 b의 대소 관계에 상관 없이
		Scanner sc = new Scanner(System.in);
		System.out.print("a : ");
		int a = sc.nextInt();
		System.out.print("b : ");
		int b = sc.nextInt();

		// 방법1 : 정석대로
		System.out.println(sumof1(a, b));
		
		// 방법2 : 가우스덧셈 사용
		
		
		
	}
	
	static int sumof1(int a, int b) {
		int sum = 0;
		if (a>b) {
			for (int i=b;i<=a;i++) {
				sum+=i;
			}
		} else if (a<b) {
			for (int i=a;i<=b;i++) {
				sum+=i;
			}
		} else sum=a+b;
		return sum;
		
		// Q. if문으로 조건 판별하고 각각 안에서 for문 돌리는 것과
		//    조건문으로 대소관계에 맞게 변수 바꾼 뒤 for문 돌리는 것
		// 둘 중 뭐가 더 효율적일까?
		// 코드로 적는 측면에서는 1번이 비슷한 코드를 2번 적는 거니까 비효율적으로 보이긴 하는데
		// 어차피 실행은 if/else에서 분별해서 한 번 for문 돌리는 거니까 컴 입장에서는 안 비효율적일 것 같고
		// 2는 눈으로 볼때는 깔끔한데 변수 대입을 한 번 더 함으로써 작업이 한 단계 더 늘어난 느낌이라
		// 컴 입장에서는 얘가 비효율적이지 않을까 생각하게 됨
		
	}
	
}
