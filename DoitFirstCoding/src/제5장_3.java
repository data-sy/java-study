
public class 제5장_3 {

	public static void main(String[] args) {
	
		
		숫자세기 가영 = new 숫자세기("가영아");
		숫자세기 나영 = new 숫자세기("나영아");
		// 이렇게 세면 가영이 5번 나영이 5번
		가영.셈하기();
		나영.셈하기();

		// 스레드를 상속받은 클래스
		Thread 다영 = new 숫자세기_스레드("다영아");
		Thread 라영 = new 숫자세기_스레드("라영아");
		// 다영이 라영이 섞여서 실행 돼
		다영.start();
		라영.start();
		
		
	}

}
