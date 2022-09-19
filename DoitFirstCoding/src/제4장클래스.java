import ABC_company.참치선물세트;
import ABC_company.특별세트;

public class 제4장클래스 {

	public static void main(String[] args) {
		
		참치선물세트 참01호 = new 참치선물세트(1, 2, 3);
		
		// 클래스에서 변수는 private으로 만들고 대신 게터 세터를 이용했기 때문에
		// 그냥 가져올 수 없고 get 사용해야 함
//		System.out.println(참01호.일반);
//		System.out.println(참01호.야채);
//		System.out.println(참01호.고추);
		System.out.println(참01호.get일반());
		System.out.println(참01호.get야채());
		System.out.println(참01호.get고추());
		
		int 총개수 = 참01호.총합();
		System.out.println(총개수);
		
		
		특별세트 특01호 = new 특별세트(4, 5, 6);
		
		// 오버라이드
		int 특총개수 = 특01호.총합();
		System.out.println("특01호 총합 개수 : " + 특총개수);

	}

}
