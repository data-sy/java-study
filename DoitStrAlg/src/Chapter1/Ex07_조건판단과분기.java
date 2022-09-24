package Chapter1;

public class Ex07_조건판단과분기 {

	public static void main(String[] args) {
		// 실습 1-4와 1-5는 코드 길이가 같고, 프로그램의 흐름을 3개로 분기하는 것 처럼 보일거야
		// 하지만 1,2,3 이외의 값이 입력되었을 때 다르게 동작해
		// 1-4 : 1 또는 2 또는 그외의 수 : 3개로 분기
		// 1-5 : 1 또는 2 또는 3 또는 그외의 수 : 4개로 분기
			// 그외의 수 일 때는 아무것도 출력하지 않아 : else ; 가 숨어 있어

		// 실습 1-4
		int n = 1;

		if (n == 1) {
			System.out.println("A");
		} else if (n == 2) {
			System.out.println("B");
		} else {
			System.out.println("C");
		}
		
		// 실습 1-5
		int m = 2;

		if (m == 1) {
			System.out.println("A");
		} else if (m == 2) {
			System.out.println("B");
		} else if (m == 3) {
			System.out.println("C");
		} //else ; 가 숨어있어
		
	}

}
