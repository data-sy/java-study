package chap02;

import java.util.Random;
import java.util.Scanner;

public class Ex105_난수로배열 {

	public static void main(String[] args) {
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("사람 수 : ");
		int n = sc.nextInt();
		
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
