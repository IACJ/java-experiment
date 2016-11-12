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
	 * ���캯��:
	 * ����MySql��������;
	 * ����MySQL;
	 * ��student�������򽡱�;
	 * �趨utf8;
	 */
	public Student(){
		try{   
			//����MySql��������   
			Class.forName("com.mysql.jdbc.Driver") ; 
			String url = "jdbc:mysql://localhost:3306/java_exp7?useUnicode=true&characterEncoding=utf8" ;     
			this.conn =DriverManager.getConnection(url , "root" , "" ) ;   
			this.stmt=conn.createStatement();
			
			// �������ڣ������ñ�
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
			System.out.println("���ݿ�����ʧ�ܣ�");   
			System.out.println("�������ݿ��Ƿ��ڿ���״̬��������ȷ");   
			System.exit(1);
		}catch(ClassNotFoundException e){   
			System.out.println("�Ҳ������������� ����������ʧ�ܣ�");   
			e.printStackTrace() ;   
		}   
	}
	

	/** ɾ��һ������
	 * @param id ѧ��
	 * @return true�ɹ� �� falseʧ��
	 */
	public boolean delete(BigInteger id){	
		try {
			String sql = "SELECT * FROM `student` WHERE `id` ="+ id +";";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()){
				System.out.println("ɾ��ʧ��!:�����ڸ�ѧ��");
				return false;
			}	
			sql = "DELETE FROM `student` WHERE `id` = "+id+";";
			int result = stmt.executeUpdate(sql);
			System.out.println("ɾ����"+result+"������");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** ��ѯһ������
	 * @param id ѧ��
	 * @return true�ɹ� �� falseʧ��
	 */
	public boolean select(BigInteger id){	
		try {
			String sql = "SELECT * FROM `student` WHERE `id` ="+ id +";";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()){
				System.out.println("��ѯʧ��!:�����ڸ�ѧ��");
				return false;
			}	
			System.out.println("��ѯ����ѧ��:"+rs.getString("id")+'\t'+rs.getString("name")+'\t'+rs.getString("age"));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ����һ������
	 * @param id ѧ��
	 * @param name ����
	 * @param age ����
	 * @return true�ɹ� �� falseʧ��
	 */
	public boolean insert(BigInteger id,String name,int age){	
		try {
			String sql = "SELECT * FROM `student` WHERE `id` ="+ id +";";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
				System.out.println("����ʧ��!:��ѧ���ѱ�ʹ�ã������ѧ��");
				return false;
			}	
			sql = "INSERT INTO `student` (`id`, `name`, `age`) VALUES"+
					"("+id+", \""+name+"\", "+age+");";
			stmt.executeUpdate(sql);
			System.out.println("���ӳɹ�");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ��ȫ���ӡ����
	 */
	public void show(){
		try {
			System.out.println("��ѯȫ��:");
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
			System.out.println("��"+n+"��");
			System.out.println("----------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �ڲ��ࣺ���뵽Excel
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
	            // ������д���Excel������
	        	String fileName = "./student.xls";
	        	File file=new File(fileName);
	        	if (!file.exists()) {
	        		file.createNewFile();
	        	}
	        	//��fileNameΪ�ļ���������һ��Workbook
	        	wwb = Workbook.createWorkbook(file);

	        	// ����������
	        	WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
	               
	        	//��ѯ���ݿ������е�����
	        	List<StuEntity> list= this.getAllByDb();
	        	//Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
	        	Label labelId= new Label(0, 0, "ѧ��(id)");//��ʾ��
	        	Label labelName= new Label(1, 0, "����(name)");
	        	Label labelAge= new Label(2, 0, "����(age)");          
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
	              //д���ĵ�
	               wwb.write();
	              // �ر�Excel����������
	               wwb.close();      
	               System.out.println("���뵽Excel�ɹ�,����Ϊstudent.xls");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	}
	
	/**
	 * �ڲ���  ��Excel �������ݿ�
	 *
	 */
	public class TestExcelToDb {
		
	    public boolean getAllByExcel(String file){
	        try {
	            System.out.println("��ȡExcel��...");
	            Workbook rwb=Workbook.getWorkbook(new File(file));
	            Sheet rs=rwb.getSheet("Test Shee 1");//����rwb.getSheet(0)
	            int clos=rs.getColumns();//�õ����е���
	            int rows=rs.getRows();//�õ����е���
	            
	            System.out.println(clos+" rows:"+rows);
	            for (int i = 1; i < rows; i++) {
	                for (int j = 0; j < clos; j++) {
	                    //��һ�����������ڶ���������
	                    String id=rs.getCell(j++, i).getContents();//Ĭ������߱��Ҳ��һ�� ���������j++
	                    String name=rs.getCell(j++, i).getContents();
	                    String age=rs.getCell(j++, i).getContents();
	                    
	                    System.out.print("��ȡ���� : "+id+"\t"+name+"\t"+age+'\t');
	                    insert(new BigInteger( id.trim() ),
	                    		name.trim(),
								Integer.parseInt( age.trim() ));
	                }
	            }
	            System.out.println("��ȡ��Excel���");
	            return true;
	        } catch (Exception e) {
	            System.out.println("��ȡ��Excel��ʽ����ȷ! ��ȡ�ж�!");
	            //e.printStackTrace();
	            return false;
	        } 
	    }
		
	    public void run(String fileName) {
	        getAllByExcel(fileName);
	    }
	}
}
