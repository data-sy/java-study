package ABC_company;

public class 제5장 {

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
		
		
		
	}

}
