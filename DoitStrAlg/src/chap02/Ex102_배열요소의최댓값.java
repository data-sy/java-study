package chap02;

public class Ex102_배열요소의최댓값 {

	public static void main(String[] args) {
		
		int[] a = new int[5];
		a[1]=37;
		a[2]=51;
		a[4]=a[1]*2;
		
		int max = a[0];
		for (int i=1; i<a.length; i++) {
			if (a[i]>max) max =a[i];
		}
		// 길이가 n인 배열이면, if조건 n-1회

		// TODO Auto-generated method stub

	}

}
