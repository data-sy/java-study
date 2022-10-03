package chap02;

import java.util.Scanner;

public class Ex103_배열요소최댓값메서드로 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 키의 최댓값 구하기
		System.out.print("사람 수 : ");
		int n = sc.nextInt();
		
		int[] height = new int[n];
		
		for (int i=0; i<n; i++) {
			System.out.print("height["+i+"] : ");
			height[i] = sc.nextInt();
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
