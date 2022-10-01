package chap02;

public class Ex100_배열이론 {

	public static void main(String[] args) {
		
	// 51p
		// 자료구조 : 데이터 단위와 데이터 자체 사이의 물리적 또는 논리적인 관계
		//			자료를 효율적으로 사용할 수 있도록 컴퓨터에 저장하는 방법 
					// 데이터 단위 : 데이터를 구성하는 하나의 덩어리
		// 배열 array : 같은 자료형의 변수인 구성 요소 component 가 모인 것
		// 예) 여러 명의 시험 점수 관리할 때
	
	// 52p
		// 구성 요소의 자료형이 int형인 배열
		int[] a;
			// a : 배열 변수 array variable 라고 부르는 특수한 변수일 뿐
			//	   배열 그 자체는 아님

		// new를 사용하여 배열 본체를 생성한 뒤 배열 변수 a와 연결
		a = new int[5];
			// int[5] : 자료형이 int형이고 구성 요소가 5개인 배열
			// 이것을 배열 변수 a가 참조
		
		// 배열 본체 생성과 배열 변수 연결을 하나의 선언으로 수행
		int[] b = new int[10];
			// 배열 본체를 생성하는 new식을 배열 변수의 초기자initializer로 사용
		
	// 53p
		// 구성 요솟수(길이) : 변수명.length
		System.out.println(a.length);
		
	// 54p
		// a의 자료형 : in[5]
		// a[i]의 자료형 : int
		
		// 각 자료형의 기본값
			// 기본값 default value : 배열 생성시 초깃값
		// byte, short, int, long, float, double : zero 즉, 0
		// char : null
		// boolean : 거짓 즉,false
		// 참조형 : null
	
	// 57p
		// 주사traverse, 스캔
		// 배열 요소를 a[0]부터 a[n-1]까지 하나씩 차례로 조사하는 과정
		
	
	}

}
