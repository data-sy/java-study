package chap02;

import java.util.Arrays;

public class Ex110_연습문제68p {

	public static void main(String[] args) {
		// Q2
					// 입력받는 부분 생략
		int[] arr = { 2, 5, 1, 3, 9, 6, 7 };
		reverse2(arr);
		System.out.println("역순 정렬을 마쳤습니다.");

		// Q3
		System.out.println("요소의 합 : " + sumOf(arr));

		// Q4
		int[] a = new int[arr.length];
		copy(a, arr);
		System.out.println("복사된 배열 : " + Arrays.toString(a));
		// 문제의 의도는 배열 a, b를 길이와 요소 직접 입력 받은 뒤 copy
		int[] a2 = {1, 2, 3, 4}; // 얘도 그냥 임의로 길이 짧은 걸로 테스트하자
		copy2(a2, arr);
		System.out.println("복사된 배열 : " + Arrays.toString(a2));
		
		// Q5
		int[] c = new int[arr.length];
		rcopy(c, arr);
		System.out.println("역순으로 복사된 배열 : " + Arrays.toString(c));

	}

	// Q2
	private static void reverse2(int[] arr) {
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length / 2; i++) {
			System.out.println("a[" + i + "]와 a[" + (arr.length - 1 - i) + "]을 교환합니다.");
			swap(arr, i, arr.length - 1 - i);
			System.out.println(Arrays.toString(arr));
		}
	}

	private static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

	// Q3
	private static int sumOf(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}

	// Q4
	private static int[] copy(int[] a, int[] b) {
		for (int i = 0; i < b.length; i++) {
			a[i]=b[i];
		}
		return a;
	}
	// 책 풀이 : 배열 중 짧은 길이를 선택 	
	private static int[] copy2(int[] a, int[] b) {
		int num = a.length <= b.length ? a.length : b.length;
		for (int i = 0; i < num; i++) {
			a[i]=b[i];
		}
		return a;
	}

	private static int[] rcopy(int[] c, int[] b) {
		for (int i = 0; i < b.length; i++) {
			c[i]=b[b.length-1-i];
		}
		return c;
	}
}
