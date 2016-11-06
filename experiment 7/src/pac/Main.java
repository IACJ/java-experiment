package pac;
import java.math.BigInteger;
import java.util.Scanner;
import pac.Student.*;

/**
 * @author ACJ
 * 学生信息管理系统
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("尝试连接数据库中...");
		Student temp = new Student();
		System.out.println("连接数据库成功!");
		System.out.println();
		System.out.println("欢迎来到学生信息管理系统!");
		System.out.println("操作说明:");
		System.out.println("0 :查询全表");
		System.out.println("1  学号  姓名 年龄 :增加一条记录");
		System.out.println("2  学号  :删除一条记录");
		System.out.println("3  学号  :查询一条记录");
		System.out.println("i 文件名 :导出到excel表格");
		System.out.println("w :导出到excel表格");
		System.out.println("q :退出");
		System.out.println("wq :导出到excel表格 并 退出 ");
		System.out.println();
		while (true){
			try{
				Scanner sc = new Scanner(System.in);
				String ods = sc.next().trim();
				if (ods.equals("wq")){
					//彩蛋 : wq保存退出
					TestDbToExcel toExl = temp.new TestDbToExcel();
					toExl.run();
					System.out.println("程序已退出");
					sc.close();
					System.exit(0);	
					continue;
				}
				if (ods.length() != 1){
					System.out.println("输入格式错误，请重新输入");
					continue;
				}
				char od = ods.charAt(0);
				switch (od){
				case 'q':
					System.out.println("程序已退出");
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
					System.out.println("输入格式错误，请重新输入");
				}
			}catch (Exception e){
				System.out.println("输入格式错误，请重新输入");
			}
		}
	}
}
