package pac;
public class Main {

	public static void main(String[] args) {
		Sushu temp = new Sushu();

		System.out.println("����ɸ��������:");
		int maxSushu =temp.shaiFa(10000000);
		
		System.out.println();
		System.out.println("�����: ");
		System.out.println(maxSushu+"�ǲ�������? "+temp.isSushu(maxSushu));
	}
}
