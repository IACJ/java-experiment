package pac;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("������������K");
		Scanner input = new Scanner(System.in);
		int k = input.nextInt();
		input.close();
		System.out.println("��һ�����ݣ�");
		K_Means temp1 = new K_Means(k,"./KMeans_Set.txt");
		temp1.run();
		System.out.println();
		System.out.println("�ڶ������ݣ�");
		K_Means temp2 = new K_Means(k,"./KMeans_Set2.txt");
		temp2.run();	
	}
}
