
public class 제5장_2 {

	public static void main(String[] args) {
		
		String gogo = "";
		
		for (int i=1; i<=9; i++) {
			
			for (int j=1; j<=9; j++) {
				gogo += i + " x " + j + " = " + (i*j) + "\n";
			}
			System.out.println(gogo);
			gogo ="";
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
