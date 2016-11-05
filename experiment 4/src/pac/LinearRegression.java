package pac;

import java.io.*;
import java.util.Scanner;

public class LinearRegression {
	private String fileName;
	private int n = 0;
	private double[] x = new double [65535];
	private double[] y = new double [65535];
	private double averageX = 0;
	private double averageY = 0;
	private double A = 0;
	private double B = 0;
	
	public LinearRegression(String fileName) {
		this.fileName = fileName;
		getInput();
	}
	public void getInput(){
		try {
			FileInputStream in = new FileInputStream(new File(fileName)); 
            Scanner s = new Scanner(in);
 
            while(s.hasNextDouble()){
            	s.nextDouble();
            	x[n]=s.nextDouble();
            	y[n++]=s.nextDouble();
            }
            System.out.println("读取样本"+n+"个");
            s.close();
		}catch (Exception e) {
			System.out.println("出现异常!");
			e.printStackTrace();
		}
	}
	public void calc(){
		double totalX =0;
		double totalY =0;
		double totalXY =0;
		double totalXX =0;
		for(int i=0;i<n;i++){
			totalX += x[i];
			totalY += y[i];
			totalXY += x[i]*y[i];
			totalXX += x[i]*x[i];
		}
		averageX = totalX / n;
		averageY = totalY / n;
		A = (totalXY - n*averageX*averageY) / (totalXX - n*averageX*averageX);
		B = averageY - averageX*A;
		System.out.println("计算已完成");
	}
	public void show(){
		System.out.println("f(x) = A * X + B 中 : ");
		System.out.println("A = "+A);
		System.out.println("B = "+B);
	}
	public void run() {
		calc();
		show();
	}
}
