package pac;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		
		//�������ɶ�ά��
		System.out.println("��ʼ���ɶ�ά��");
		String contents = "�ȸ�";      //����
		String FileName = "./output.png";						//�ļ���
        EncodeImgZxing.writeToFile(contents, "png", new File(FileName));  //���ɶ�ά��
        System.out.println("��λ��������Ϊ : "+FileName);
        
        //���Խ�����ά��
        System.out.println(); 
        System.out.println("��ά�����ݽ������Ϊ : "); 
        String content = DecodeImgZxing.decodeImg(new File("./output.png"));
		System.out.println(content);	
		
		System.out.println(); 
        System.out.println("���Խ���");
	}
}



