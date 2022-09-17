import java.util.Scanner;

public class Exam0513 {

	public static void main(String[] args) {
		
		// 변수 선언
		int a = 3;
		a = 20;
		//System.out.println(a);
		
		
		// 변수의 종류
		float num1 = (float) 3.14;
		float num2 = 3.14f;
		
		boolean isCheck = true ;

		
		// 문자와 문자열
		char c = 'c';
		// char c2 = "c";
		String d = "d";
		// String d2 = 'd';
		
		
		// 아스키코드
			// 문자에 +1 하면 아스키코드+1이 됨
		char e = 'E';
		//System.out.println(e+1);
			// 숫자를 char에 넣을 수 있어 = 아스키코드로 들어가. 출력해보면 확인 됨
		char f = 70;
		//System.out.println(f);
	
		
		// 치환문제
		int teacherAge = 20;
		int myAge = 30;
		
		int temp = teacherAge;
		teacherAge = myAge;
		myAge = temp;

		//System.out.println(teacherAge);
		
		
		// 강제 형변환
		int num3 = 128;
		byte num4 = (byte) num3;
		//System.out.println(num4);
		
		
		// 산술연산자 예제
		int num = 456;
		int result = num/100*100;
		
//		System.out.print("결과 확인 : ");
//		System.out.println(result);
//		
		
		// 한글이 안 나와
//		System.out.println("gd");
//		System.out.println("한글");
//		System.out.println('ㄱ');
//		char g = 'ㄱ';
//		System.out.println(g);		
//		
		
		
		// 스캐너 임포트
		Scanner sc = new Scanner(System.in);
		
		
//		System.out.print("숫자를 입력해주세요 : ");	
//		int input = sc.nextInt();
//		System.out.print("입력한 숫자 : " + input);
		
		
		// 예제11
		System.out.print("초 입력 : ");
		int totalSecond = sc.nextInt();
		int hour = totalSecond/3600;
		int minute = totalSecond%3600/60;
		int second = totalSecond%60;
		System.out.println(hour + "시");
		System.out.println(minute + "분");		
		System.out.println(second + "초");
		
		
		
		
		
		
		
		
		
		
	}

}
