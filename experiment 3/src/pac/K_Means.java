package pac;
import java.io.*;
import java.util.Scanner;

public class K_Means {
	private String fileName ="";
	private int k =0;
	private int n = 0;
	private point[] arr;
	private int[] marks;
	private point[] centers;
	
	class point{
		public double x;
		public double y;
		
		public point(double x,double y){
			this.x = x;
			this.y = y;
		}
		
		public void show(){
			System.out.println("["+x+","+y+"]");
		}
	}
	
	public K_Means(int k,String fileName){
		this.k = k;
		this.fileName = fileName;
		arr = new point[65535];
		marks = new int [65535];
		centers = new point [k];
		getInput();
	}
	
	public void getInput(){
		try {
			FileInputStream in = new FileInputStream(new File(fileName)); 
            Scanner s = new Scanner(in); 
            while(s.hasNextDouble()){
            	arr[n++] = new point(s.nextDouble(),s.nextDouble());  
            }
            System.out.println("��ȡ����"+n);
            s.close();
		} catch (FileNotFoundException e) {
			System.out.println("�����쳣!");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("�����쳣!");
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
	}
	
	public double distance(point a,point b){
		return Math.sqrt( (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y) );
	}
	
	
	public void run(){
		for (int i=0;i<k;i++){
			centers[i] = new point(Math.random()*10-5,Math.random()*10-5);
		}
		
		int count = 0;
		do{
			count++;
			//��һ�����ط��������
			for (int i=0;i<n;i++){
				marks[i] = 0;
				for (int j=1;j<k;j++){
					if (distance(arr[i],centers[marks[i]]) > distance(arr[i],centers[j])){
						marks[i] = j;
					}
				}
			}
			//�ڶ���,��������
			point[] new_centers = new point[k];
			for (int i=0;i<k;i++){
				double totalX=0;
				double totalY=0;
				double num=0;
				for (int j=0;j<n;j++){
					if(marks[j] == i){
						totalX += arr[j].x;
						totalY += arr[j].y;
						num++;
					}
				
				}
				new_centers[i] = new point(totalX / num, totalY / num);
			}
			//���������˳�ѭ������:�ز������仯��ﵽ��������
			boolean changed = false;
			for (int i=0;i<k;i++){
				if(new_centers[i].x != centers[i].x || new_centers[i].y != centers[i].y){
					changed = true;
					centers[i].x = new_centers[i].x;
					centers[i].y = new_centers[i].y;
				}
			}
			if (count >= 1000 || changed == false){
				break;
			}
		}while(true);
		System.out.println("���������ѭ��������" + count);
		System.out.println(k + "���ص��������£�");
		for(int i = 0;i<k;i++){
			centers[i].show();
		}
	}
}
