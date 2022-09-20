

public class 제5장_1 {

	public static void main(String[] args) {
		
		// 배열
		int score[] = new int [5];
		
		score[0] = 55;
		score[1] = 97;
		score[2] = 28;
		score[3] = 80;
		score[4] = 67;
		
		for(int i=0; i<5; i++) {
			System.out.println(score[i]);
		}
		
		// 랜덤
		double ran = Math.random();
		double ran100 = ran*100;
		System.out.println(ran100);

		int integer = (int) ran100;
		System.out.println(integer);
		int remainder = integer%4;
		System.out.println(remainder);
		
		if (remainder==0) {
			System.out.println("위");
		} else if (remainder==1) {
			System.out.println("아래");
		} else if (remainder==2) {
			System.out.println("왼쪽");
		} else if (remainder==3) {
			System.out.println("오른쪽");
		}
		
		// 예외처리
//		String 숫자같은문자 = "12345";
//		int 정수숫자 = Integer.parseInt(숫자같은문자);
		
		try {
			String 숫자같은문자 = "홍길동";
			int 정수숫자 = Integer.parseInt(숫자같은문자);
		} catch(Exception e){
			System.out.println("오류발생");
			System.out.println("오류 메세지 : " + e);
		}

		
	}

}
