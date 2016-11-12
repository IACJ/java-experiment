package pac;
public class Main {

	public static void main(String[] args) {
		Sushu temp = new Sushu();

		System.out.println("运用筛法求素数:");
		int maxSushu =temp.shaiFa(10000000);
		
		System.out.println();
		System.out.println("验算答案: ");
		System.out.println(maxSushu+"是不是素数? "+temp.isSushu(maxSushu));
	}
}
