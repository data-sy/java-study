import java.util.Scanner;

public class 제3장조건문 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("피자가게가 열렸나요?(y/n)");
		String pizza = sc.next();
		
		System.out.println("치킨가게가 열렸나요?(y/n)");
		String chicken = sc.next();
		
		if (pizza.equals("y")){
			System.out.println("피자가게 고고");
		} else if (chicken.equals("y")) {
			System.out.println("치킨가게 고고");
		} else {
			System.out.println("편의점에서 라면 고고");
		}
		// y를 작은따옴표''안에 넣으면 char로 인식해서 이퀄스에서 다르다고 체크
			
		
		String star = "";
		for (int num=1; num<=5; num++) {
			star+="*";
		}
		System.out.println(star);

		
		String starTree ="";
		for (int i=0; i<5; i++) {
			for (int j=0; j<=i; j++) {
				starTree+="*";
			}
			if (i!=4) {
			starTree+="\n";
			}
		}
		System.out.println(starTree);
		System.out.println("줄넘김확인");
		

	}

}
