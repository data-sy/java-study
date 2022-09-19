package ABC_company;

public class 특별세트 extends 참치선물세트{
	// 일반, 햄, 카놀라유로 이루어진 특별세트 만들기
	
	private int 햄;
	private int 카놀라유;

	public int get햄() {
		return 햄;
	}

	public void set햄(int 햄) {
		this.햄 = 햄;
	}

	public int get카놀라유() {
		return 카놀라유;
	}

	public void set카놀라유(int 카놀라유) {
		this.카놀라유 = 카놀라유;
	}

	public 특별세트(int 일반, int 햄, int 카놀라유) {
		super(일반, 0, 0);
		this.햄 = 햄;
		this.카놀라유 = 카놀라유;
	}
	
	public int 총합() {
		int 일반총합 = super.총합();
		int 전체개수;
		전체개수 = 일반총합 + this.햄 + this.카놀라유;
		return 전체개수;
	}


}
