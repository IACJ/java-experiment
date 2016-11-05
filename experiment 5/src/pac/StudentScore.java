package pac;

import java.io.*;
import java.util.Scanner;

public class StudentScore {
	private String fileName;
	private int n = 0;
	private int[] scores = new int[65535];
	private int lowestS=101;
	private int highestS=-1;
	private double averageS=0;
	private int S_60_69=0;
	private int S_70_79=0;
	private int S_80_89=0;
	private int S_90_100=0;
	
	public StudentScore(String fileName){
		this.fileName = fileName;
		getInput();
	}
	public void getInput(){
		try {
			FileInputStream in = new FileInputStream(new File(fileName)); 
            Scanner s = new Scanner(in);
            s.useDelimiter("\\n|\\r|;");
            while(s.hasNextInt()){
            	s.nextInt();
            	scores[n++] = s.nextInt();
            }
            System.out.println("��ȡ����"+n+"��");
            s.close();
		}catch (Exception e) {
			System.out.println("�����쳣!");
			e.printStackTrace();
		}
	}
	
	public void calc(){
		int totalS =0;
		for(int i=0;i<n;i++){
			totalS += scores[i];
			if(scores[i] > highestS){
				highestS = scores[i];
			}
			if(scores[i] < lowestS){
				lowestS = scores[i];
			}
			if (scores[i]>=60 && scores[i]<=69){
				S_60_69++;
			}else if (scores[i]>=70 && scores[i]<=79){
				S_70_79++;
			}else if (scores[i]>=80 && scores[i]<=89){
				S_80_89++;
			}else if (scores[i]>=90 && scores[i]<=100){
				S_90_100++;
			}
		}
		averageS = (double)totalS / n;
		System.out.println("���������");
	}
	
	public void writeToFile(){
        FileOutputStream fop;
		try {
			fop = new FileOutputStream(new File("output.txt"));
			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			writer.append("��߳ɼ� : "+highestS +"\r\n");
			writer.append("��ͳɼ� : "+lowestS +"\r\n");
			writer.append("ƽ���ɼ� : "+averageS +"\r\n");
			writer.append("�ɼ�ͳ�� :  \r\n");
			writer.append("60~69: "+S_60_69 +"\r\n");
			writer.append("70~79: "+S_70_79 +"\r\n");
			writer.append("80~89: "+S_80_89 +"\r\n");
			writer.append("90~100: "+S_90_100 +"\r\n");
	        writer.close();
	        System.out.println("�ļ�д��ɹ�\r\n��鿴output.txt");
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		calc();
		writeToFile();
	}
}
