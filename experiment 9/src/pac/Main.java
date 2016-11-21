package pac;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		
		//测试生成二维码
		System.out.println("开始生成二维码");
		String contents = "谷歌";      //内容
		String FileName = "./output.png";						//文件名
        EncodeImgZxing.writeToFile(contents, "png", new File(FileName));  //生成二维码
        System.out.println("二位码已生成为 : "+FileName);
        
        //测试解析二维码
        System.out.println(); 
        System.out.println("二维码内容解析结果为 : "); 
        String content = DecodeImgZxing.decodeImg(new File("./output.png"));
		System.out.println(content);	
		
		System.out.println(); 
        System.out.println("测试结束");
	}
}



