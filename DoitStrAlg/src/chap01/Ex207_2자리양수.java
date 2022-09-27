package chap01;

import java.util.Scanner;

public class Ex207_2자리양수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		
		System.out.println("2자리 양수 입력");
		
		do {
			System.out.print("n : " );
			n = sc.nextInt();
		} while(n<10 || n>99);
		
		System.out.println("변수 n의 값은 " + n + "이 되었습니다.");

	}

}
