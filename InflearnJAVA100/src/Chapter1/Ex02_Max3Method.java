package Chapter1;

public class Ex02_Max3Method {
	
	public static void main(String[] args) {
		
		System.out.println(max3(10, 2, 3));
		System.out.println(max3(1, 2, -1));
		System.out.println(max3(1, 2, 3));
		System.out.println(max3(2, 2, 1));
		
		// 세 개의 수 대소관계 => 모든 경우의 수는 총 13가지
		// 올바른 알고리즘인지 확인한다는 것은 모든 경우를 확인해보는 것
		
	}
	
	static int max3(int a, int b, int c) {
		
		int max = a;
		if (b>max) max = b;
		if (c>max) max = c;
		
		return max;
		
	}

}
