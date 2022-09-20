
public class 숫자세기 {
	String 나의이름;

	public 숫자세기(String 나의이름) {
		super();
		this.나의이름 = 나의이름;
	}

	public String get나의이름() {
		return 나의이름;
	}

	public void set나의이름(String 나의이름) {
		this.나의이름 = 나의이름;
	}
	
	public void 셈하기() {
		for (int i=1; i<=5; i++) {
			System.out.println(나의이름+i);
		}
	}
		
}
