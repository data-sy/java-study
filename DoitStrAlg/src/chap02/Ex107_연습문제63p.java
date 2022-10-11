package chap02;

import java.util.Random;


public class Ex107_연습문제63p {

	public static void main(String[] args) {
		Random r = new Random();
		
		// 사람 수 : 1~10 난수 생성
		int n = 1 + r.nextInt(9);
		System.out.println("사람 수 " + n + "명");
		
		int[] height = new int[n];
		
		for (int i=0; i<n; i++) {
			height[i] = 100 + r.nextInt(90);
			System.out.println("height["+i+"] : " + height[i]);
		}
		
		System.out.println("제일 큰 사람의 키는 "+maxOf(height)+"입니다.");		

	}
		static int maxOf(int[] a){
			int max = a[0];
			for (int i=1;i<a.length; i++) {
				if (a[i]>max) max = a[i];
			}
			return max;
	}

}
