package chap01;

public class Ex103_연습문제19p {
	
	public static void main(String[] args) {
		
		System.out.println(max4(1, 2, 3, 4));
		System.out.println(min3(1, 2, 3));
		System.out.println(min4(2, 1, 3, 4));

		// 4개의 대소관계
		// 결정트리로 수형도 그려가며 구할 수도 있고
		// 경우의 수로 체크해보면
		// 1) 등호 없는 경우 : 4!=24
		// 2) 등호 1개인 경우 : 4C2*3! = 36
		// 3) 등호 2개인 경우 : 4C3*2! = 8
		// 4) 등호 3개인 경우 : 1
		// 총 69가지
		
	}
	
	static int max4(int a, int b, int c, int d) {
		
		int max = a;
		if (b>max) max = b;
		if (c>max) max = c;
		if (d>max) max = d;
		
		return max;
		
	}
	
	static int  min3(int a, int b, int c) {
		
		int min = a;
		if (b<min) min = b;
		if (c<min) min = c;
		
		return min;
		
	}

	static int  min4(int a, int b, int c, int d) {
		
		int min = a;
		if (b<min) min = b;
		if (c<min) min = c;
		if (d<min) min = d;
		
		return min;
		
	}

}
