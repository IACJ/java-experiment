package pac;

import java.io.File;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import jxl.*;
import jxl.write.*;

public class Student {
	private Connection conn;
	private Statement stmt;
	
	/**
	 * 构造函数:
	 * 加载MySql的驱动类;
	 * 连接MySQL;
	 * 若student表不存在则健表;
	 * 设定utf8;
	 */
	public Student(){
		try{   
			//加载MySql的驱动类   
			Class.forName("com.mysql.jdbc.Driver") ; 
			String url = "jdbc:mysql://localhost:3306/java_exp7?useUnicode=true&characterEncoding=utf8" ;     
			this.conn =DriverManager.getConnection(url , "root" , "" ) ;   
			this.stmt=conn.createStatement();
			
			// 若表不存在，则建立该表
			String sql = "CREATE TABLE IF NOT EXISTS `student` ("+
					  "`id` bigint(11) NOT NULL,"+
					  "`name` varchar(10) COLLATE utf8_bin NOT NULL,"+
					  "`age` int(11) NOT NULL,"+
					  "PRIMARY KEY (`id`)"+
					") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
			stmt.executeUpdate(sql);
			sql = "SET NAMES utf8";
			stmt.executeUpdate(sql);
		}catch(SQLException se){   
			se.printStackTrace();
			System.out.println("数据库连接失败！");   
			System.out.println("请检查数据库是否处于开启状态且配置正确");   
			System.exit(1);
		}catch(ClassNotFoundException e){   
			System.out.println("找不到驱动程序类 ，加载驱动失败！");   
			e.printStackTrace() ;   
		}   
	}
	

	/** 删除一行数据
	 * @param id 学号
	 * @return true成功 或 false失败
	 */
	public boolean delete(BigInteger id){	
		try {
			String sql = "SELECT * FROM `student` WHERE `id` ="+ id +";";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()){
				System.out.println("删除失败!:不存在该学号");
				return false;
			}	
			sql = "DELETE FROM `student` WHERE `id` = "+id+";";
			int result = stmt.executeUpdate(sql);
			System.out.println("删除了"+result+"行数据");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** 查询一行数据
	 * @param id 学号
	 * @return true成功 或 false失败
	 */
	public boolean select(BigInteger id){	
		try {
			String sql = "SELECT * FROM `student` WHERE `id` ="+ id +";";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()){
				System.out.println("查询失败!:不存在该学号");
				return false;
			}	
			System.out.println("查询到该学生:"+rs.getString("id")+'\t'+rs.getString("name")+'\t'+rs.getString("age"));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 插入一行数据
	 * @param id 学号
	 * @param name 姓名
	 * @param age 年龄
	 * @return true成功 或 false失败
	 */
	public boolean insert(BigInteger id,String name,int age){	
		try {
			String sql = "SELECT * FROM `student` WHERE `id` ="+ id +";";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
				System.out.println("增加失败!:该学号已被使用，请更换学号");
				return false;
			}	
			sql = "INSERT INTO `student` (`id`, `name`, `age`) VALUES"+
					"("+id+", \""+name+"\", "+age+");";
			stmt.executeUpdate(sql);
			System.out.println("增加成功");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 将全表打印出来
	 */
	public void show(){
		try {
			System.out.println("查询全表:");
			System.out.println("----------------------------");
			String sql = "SELECT * FROM `student`;";
			ResultSet rs = stmt.executeQuery(sql);
			String id,name,age;
			int n =0;
			while(rs.next()){
				n++;
				id = rs.getString("id");
				name = rs.getString("name");
				age = rs.getString("age");
				System.out.println(id+'\t'+name+'\t'+age);
			}
			System.out.println("共"+n+"行");
			System.out.println("----------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 内部类：导入到Excel
	 *
	 */
	public class TestDbToExcel {
				
	    public List<StuEntity> getAllByDb(){
	        List<StuEntity> list=new ArrayList<StuEntity>();
	        try {
	            String sql="select * from `student`";
	            ResultSet rs= stmt.executeQuery(sql);
	            while (rs.next()) {
	                BigInteger id=(rs.getBigDecimal("id").toBigInteger());
	                String name=rs.getString("name");
	                int age=rs.getInt("age");
	                list.add(new StuEntity(id, name, age));
	            }	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
		
	    public void run() {
	        try {
	        	WritableWorkbook wwb = null;
	            // 创建可写入的Excel工作簿
	        	String fileName = "./student.xls";
	        	File file=new File(fileName);
	        	if (!file.exists()) {
	        		file.createNewFile();
	        	}
	        	//以fileName为文件名来创建一个Workbook
	        	wwb = Workbook.createWorkbook(file);

	        	// 创建工作表
	        	WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
	               
	        	//查询数据库中所有的数据
	        	List<StuEntity> list= this.getAllByDb();
	        	//要插入到的Excel表格的行号，默认从0开始
	        	Label labelId= new Label(0, 0, "学号(id)");//表示第
	        	Label labelName= new Label(1, 0, "姓名(name)");
	        	Label labelAge= new Label(2, 0, "年龄(age)");          
	        	ws.addCell(labelId);
	        	ws.addCell(labelName);
	        	ws.addCell(labelAge);
	        	
	        	for (int i = 0; i < list.size(); i++) {
	                   Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
	                   Label labelName_i= new Label(1, i+1, list.get(i).getName());
	                   Label labelAge_i= new Label(2, i+1, list.get(i).getAge()+"");
	                   ws.addCell(labelId_i);
	                   ws.addCell(labelName_i);
	                   ws.addCell(labelAge_i);
	               }    
	              //写进文档
	               wwb.write();
	              // 关闭Excel工作簿对象
	               wwb.close();      
	               System.out.println("导入到Excel成功,保存为student.xls");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	}
	
	/**
	 * 内部类  将Excel 导入数据库
	 *
	 */
	public class TestExcelToDb {
		
	    public boolean getAllByExcel(String file){
	        try {
	            System.out.println("读取Excel中...");
	            Workbook rwb=Workbook.getWorkbook(new File(file));
	            Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
	            int clos=rs.getColumns();//得到所有的列
	            int rows=rs.getRows();//得到所有的行
	            
	            System.out.println(clos+" rows:"+rows);
	            for (int i = 1; i < rows; i++) {
	                for (int j = 0; j < clos; j++) {
	                    //第一个是列数，第二个是行数
	                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
	                    String name=rs.getCell(j++, i).getContents();
	                    String age=rs.getCell(j++, i).getContents();
	                    
	                    System.out.print("读取数据 : "+id+"\t"+name+"\t"+age+'\t');
	                    insert(new BigInteger( id.trim() ),
	                    		name.trim(),
								Integer.parseInt( age.trim() ));
	                }
	            }
	            System.out.println("读取的Excel完成");
	            return true;
	        } catch (Exception e) {
	            System.out.println("读取的Excel格式不正确! 读取中断!");
	            //e.printStackTrace();
	            return false;
	        } 
	    }
		
	    public void run(String fileName) {
	        getAllByExcel(fileName);
	    }
	}
}
