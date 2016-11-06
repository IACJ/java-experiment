package pac;
import java.math.BigInteger;
import java.util.Scanner;
import pac.Student.*;

/**
 * @author ACJ
 * ѧ����Ϣ����ϵͳ
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("�����������ݿ���...");
		Student temp = new Student();
		System.out.println("�������ݿ�ɹ�!");
		System.out.println();
		System.out.println("��ӭ����ѧ����Ϣ����ϵͳ!");
		System.out.println("����˵��:");
		System.out.println("0 :��ѯȫ��");
		System.out.println("1  ѧ��  ���� ���� :����һ����¼");
		System.out.println("2  ѧ��  :ɾ��һ����¼");
		System.out.println("3  ѧ��  :��ѯһ����¼");
		System.out.println("i �ļ��� :������excel���");
		System.out.println("w :������excel���");
		System.out.println("q :�˳�");
		System.out.println("wq :������excel��� �� �˳� ");
		System.out.println();
		while (true){
			try{
				Scanner sc = new Scanner(System.in);
				String ods = sc.next().trim();
				if (ods.equals("wq")){
					//�ʵ� : wq�����˳�
					TestDbToExcel toExl = temp.new TestDbToExcel();
					toExl.run();
					System.out.println("�������˳�");
					sc.close();
					System.exit(0);	
					continue;
				}
				if (ods.length() != 1){
					System.out.println("�����ʽ��������������");
					continue;
				}
				char od = ods.charAt(0);
				switch (od){
				case 'q':
					System.out.println("�������˳�");
					sc.close();
					System.exit(0);				
					break;
				case '0':
					temp.show();
					break;
				case '1':
					temp.insert(new BigInteger( sc.next().trim() ),
							sc.next().trim(),
							Integer.parseInt( sc.next().trim() ) );
					break;
				case '2':
					temp.delete(new BigInteger( sc.next().trim() ) );
					break;
				case '3':
					temp.select(new BigInteger( sc.next().trim() ) );
					break;
				case 'w':
					TestDbToExcel MysqltoExl = temp.new TestDbToExcel();
					MysqltoExl.run();
					break;
				case 'i':
					TestExcelToDb ExlToMysql = temp.new TestExcelToDb();
					ExlToMysql.run( sc.next().trim() );
					break;
				default:
					System.out.println("�����ʽ��������������");
				}
			}catch (Exception e){
				System.out.println("�����ʽ��������������");
			}
		}
	}
}
