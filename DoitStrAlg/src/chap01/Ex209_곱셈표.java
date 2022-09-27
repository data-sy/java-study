package chap01;

public class Ex209_곱셈표 {

	public static void main(String[] args) {
		
		// 곱셈표
		for (int i=1; i<=9; i++) {
			for (int j=1; j<=9; j++) {
				System.out.printf("%3d", i*j);
			}
			System.out.println();
		}
		// %3d는 결과물을 3자리로 출력하라는 말
		
	}

}
