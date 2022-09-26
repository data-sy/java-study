package chap01;

import java.util.Scanner;

public class Ex205_pm번갈아출력 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("n : ");
		int n = sc.nextInt();
		
		// 처음 딱 떠올릴 방법
		for (int i=0; i<n; i++) {
			if (i%2==0) System.out.print(" +");
			else System.out.print(" -");
		}
		System.out.println();
		// for반복 n번, 나눗셈 n번, if조건판단 n번
		// 문제점1 : 반복할 때마다 if문 실행
		// 문제점2 : 변경할 때 유연하게 대응하기 어려워
			// 만약 카운터용 변수인 i 를 1에서 n까지 증가시키는 방식으로 사용하면 for문 전체가 수정되어야 해
			// 수정1 - for의 초기화 부분) i=1; i<=n
			// 수정2 - 루프 본문) if문 안의 +, - 자리 바꾸기
		
		//그래서 +-를 세트로 묶어서 절반 개수만큼 출력하고, 만약 홀수라면 맨 마지막에 + 한 번 더
		for (int i=0; i<n/2; i++) {
			System.out.print(" + -");
		}
		if (n%2 != 0) System.out.print(" +");
		// for문 반복 n/2번, 나눗셈 2번, if판단 1번
		// 카운트용 변수도 i=1; i<=n/2 로 유연하게 대응 가능 (초기화 부분만 변경)
		
	}

}
