package Chapter1;

public class Ex04_Median {

	public static void main(String[] args) {
		
		
		// 20p 결정 트리 순서랑 일치하게 적음
		// 결과는 b b c a a / b b a a c / c b b 나와야 해
		//   즉, 2 2 2 3 2 / 3 3 2 2 2 / 2 3 2
		System.out.print(med3(3, 2, 1) +" ");
		System.out.print(med3(3, 2, 2) +" ");
		System.out.print(med3(3, 1, 2) +" ");
		System.out.print(med3(3, 2, 3) +" ");
		System.out.print(med3(2, 1, 3) +" / ");
		System.out.print(med3(3, 3, 2) +" ");
		System.out.print(med3(3, 3, 3) +" "); 
		System.out.print(med3(2, 2, 3) +" ");
		System.out.print(med3(2, 3, 1) +" ");
		System.out.print(med3(2, 3, 2) +" / ");
		System.out.print(med3(1, 3, 2) +" ");
		System.out.print(med3(2, 3, 3) +" ");
		System.out.print(med3(1, 2, 3) +" ");
		System.out.println();
		System.out.print(med3Book(3, 2, 1) +" ");
		System.out.print(med3Book(3, 2, 2) +" ");
		System.out.print(med3Book(3, 1, 2) +" ");
		System.out.print(med3Book(3, 2, 3) +" ");
		System.out.print(med3Book(2, 1, 3) +" / ");
		System.out.print(med3Book(3, 3, 2) +" ");
		System.out.print(med3Book(3, 3, 3) +" "); 
		System.out.print(med3Book(2, 2, 3) +" ");
		System.out.print(med3Book(2, 3, 1) +" ");
		System.out.print(med3Book(2, 3, 2) +" / ");
		System.out.print(med3Book(1, 3, 2) +" ");
		System.out.print(med3Book(2, 3, 3) +" ");
		System.out.print(med3Book(1, 2, 3) +" ");
	}

	// 내 풀이
	// 
	static int med3(int a, int b, int c) {

		int med = b;

		if (a > b) {
			if (b < c) med = a >= c ? c : a;
		} else if (a < b) {
			if (b >= c) med = a >= c ? a : c;
		} else {
			if (b < c) med = a;
		}

		return med;
	}	

	// 책 풀이
	static int med3Book(int a, int b, int c) {
		
		if (a>=b)
			if (b>=c)
				return b;
			else if (a<=c)
				return a;
			else
				return c;
		else if (a>c)
			return a;
		else if (b>c)
			return c;
		else
			return b;

	}
}
