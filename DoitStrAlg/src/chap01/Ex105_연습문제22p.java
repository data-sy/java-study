package chap01;

public class Ex105_연습문제22p {
	// 이 메서드가 Ex04_Median.java 의 책풀이메서드(med3Book)에 비해 효율이 떨어지는 이유를 설명하시오.
	
	// 가독성과 이해도는 제일 좋은 코드라고 생각했는데, 성능은 제일 안 좋대
	// 해설
	/*
	  처음 if 문의 판단
	　　  if ((b >= a && c<= a) || (b <= a && c >= a)에 주목합니다. 
	  여기서 b >= a 및 b <= a의 판단을 뒤집은 판단
	  (실질적으로 동일한 판단)이, 계속하여  else 이후에서
	　　 else if ((a > b && c < b) || (b <= a && c > b)로 수행됩니다.
	 즉, 처음 if가 성립하지 않는 경우, 2번째 if에서도 (실질적으로) 같은 판단을 수행하므로, 효율이 나빠집니다.
	*/
	
	static int med3(int a, int b, int c) {

		if ((b>=a && c<=a)||(b<=a && c>=a))
			return a;
		else if ((a>b && c<b)||(a<b && c>b))
			return b;
		return c;
	}	
	
	public static void main(String[] args) {
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
	}

}
