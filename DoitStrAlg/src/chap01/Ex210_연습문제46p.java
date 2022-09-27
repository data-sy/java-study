package chap01;

public class Ex210_연습문제46p {

	public static void main(String[] args) {
		
		// Q11
		System.out.print("   |");
		for (int i=1; i<=9; i++) {
			System.out.printf("%3d", i);
		}
		System.out.print("\n---+");
		System.out.println("---".repeat(9));
		for (int i=1; i<=9; i++) {
			System.out.printf("%3d|", i);
			for (int j=1; j<=9; j++) {
				System.out.printf("%3d", i*j);
			}
			System.out.println();
		}	
		// System.out.print(i+'|'); 문자열말고 문자타입으로 했더니
		// 아스키코드로 읽나봐 125 ~ 133으로 나옴
		
		
	}

}
