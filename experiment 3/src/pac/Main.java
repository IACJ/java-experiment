package pac;
import java.util.Scanner;
/**
 * @author ACJ
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("请输入正整数K");
		Scanner input = new Scanner(System.in);
		int k = input.nextInt();
		input.close();
		
		K_Means temp1 = new K_Means(k,"./KMeans_Set.txt");
		temp1.run();	
	}
}
