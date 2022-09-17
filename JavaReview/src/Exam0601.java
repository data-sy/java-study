import java.util.Scanner;

public class Exam0601 {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		// switch문
		switch (input%2) {
		case 0 :
			System.out.println("짝수입니다!");
			break;
		case 1 :
			System.out.println("홀수입니다!");
			break;
		default :
			System.out.println("정수를 입력해주세요");		
		}
	}

}
