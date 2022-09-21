import java.util.Arrays;
import java.util.Random;

public class Exam0608 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 실습 2-1
		Random r = new Random();
		
		int[] arr = new int[10];
		int max = 0;
		
		for (int i=0; i<arr.length; i++) {
			arr[i] = r.nextInt(10)+1;
			if (arr[i]>max) {
				max = arr[i];
			}
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(String.format("가장 큰 값은 %d입니다.", max));
		

	}

}
