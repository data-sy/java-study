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

		// 방법1 : a>b / a<=b 조건에 따라 for문
		System.out.println(sumof1(a, b));

		// 방법2 : 크기 비교해서 a<=b 꼴이 되도록 재할당 후 for문
		// 답은 이거 사용. 그리고 재할당말고 새 변수 min, max 사용
		System.out.println(sumof2(a, b));

		// 방법2 : 가우스덧셈 사용
		System.out.println(sumof3(a, b));

	}

	static int sumof1(int a, int b) {
		int sum = 0;
		if (a > b) {
			for (int i = b; i <= a; i++) {
				sum += i;
			}
		} else {
			for (int i = a; i <= b; i++) {
				sum += i;
			}
		}
		return sum;
	}

	static int sumof2(int a, int b) {
		int sum = 0;
		int temp;
		if (a > b) {
			temp = b;
			b = a;
			a = temp;
		}
		for (int i = a; i <= b; i++) {
				sum += i;
		}
		return sum;
	}

	// Q. if문으로 조건 판별하고 각각 안에서 for문 돌리는 것과
	// 조건문으로 대소관계에 맞게 변수 바꾼 뒤 for문 돌리는 것
	// 둘 중 뭐가 더 효율적일까?
		// 코드로 적는 측면에서는 1번이 비슷한 코드를 2번 적는 거니까 비효율적으로 보이긴 하는데
		// 어차피 실행은 if/else에서 분별해서 한 번 for문 돌리는 거니까 컴 입장에서는 안 비효율적일 것 같고
		// 2는 눈으로 볼때는 깔끔한데 변수 temp를 한 번 더 선언함으로써 작업이 한 단계 더 늘어난 느낌이라
		// 컴 입장에서는 얘가 비효율적이지 않을까 생각하게 됨

	static int sumof3(int a, int b) {
		int temp;
		if (a > b) {
			temp = b;
			b = a;
			a = temp;
		}
		return (b*b+b-a*a+a)/2;
	}

}