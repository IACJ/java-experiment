package pac;

public class Main {
	public static void main(String[] args){
		
		System.out.println("��һ�����ݣ�");
		LinearRegression temp1 = new LinearRegression("./LR_ex0.txt");
		temp1.run();
		System.out.println();
		System.out.println("�ڶ������ݣ�");
		LinearRegression temp2 = new LinearRegression("./LR_ex1.txt");
		temp2.run();
	}
}
