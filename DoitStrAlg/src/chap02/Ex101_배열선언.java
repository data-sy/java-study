package chap2;

public class Ex201_배열선언 {

	public static void main(String[] args) {
		
		int[] a = new int[5];
		
		// int 배열은 기본값 0
		a[1]=37;
		a[2]=51;
		a[4]=a[1]*2;
		
		for (int i=0; i<a.length; i++) {
			System.out.println("a[" +i +"] = "+a[i]);
		}
		
		// 선언하기
		int[] b;
		// 참조하기
		b = new int[5];
		
		// 배열의 요솟값을 초기화하며 배열 선언	
		int[] c = {1, 2, 3, 4, 5};
		for (int i=0; i<a.length; i++) {
			System.out.println("c[" +i +"] = "+c[i]);
		}
		

	}

}
