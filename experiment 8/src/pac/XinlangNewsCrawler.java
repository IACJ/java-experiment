package pac;

import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class XinlangNewsCrawler {
	private void printInetAddressInfo(){
		InetAddress inetAddress;
		try {
			System.out.println("Ŀ��InetAddress : ");
			inetAddress = InetAddress.getByName("news.sina.com.cn");
			System.out.println("HostName : "+inetAddress.getHostName());
			System.out.println("Address : "+inetAddress.getHostAddress());
			
			System.out.println();
			System.out.println("����InetAddress : ");
			inetAddress = InetAddress.getLocalHost();
			System.out.println("HostName : "+inetAddress.getHostName());
			System.out.println("Address : "+inetAddress.getHostAddress());	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private String DownloadHtmlStr(){
		StringBuffer html = new StringBuffer();
		try {
			URL xinlangnews = new URL("http://news.sina.com.cn/");
			System.out.println();
			System.out.println("������ȡ http://news.sina.com.cn/ ");
			//��ȡ ��Դ���ֽ�������
			InputStream is = xinlangnews.openStream();
			//���ֽ������� ת��Ϊ �ַ�������
			InputStreamReader isr =new InputStreamReader(is,"utf-8");
			//Ϊ�ַ���������ӻ���
			BufferedReader br = new BufferedReader(isr);
			String data = br.readLine();
			int n=1;
			while (data!=null){
				data = br.readLine();
				html.append(data+"\n");
				n++;
			}
			System.out.println("�Ѷ�ȡ"+n+"��HTML����");
			br.close();
			isr.close();
			is.close();	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html.toString();
	}
	
	private void soupParseAndPrint(String htmlStr) {
		System.out.println();
		System.out.println("������: ");
		System.out.println("-------------------");
		Document doc = Jsoup.parse(htmlStr);
		Element title = doc.getElementsByTag("head").first().getElementsByTag("title").first();
		System.out.println("���� : "+title.html());
		
		Element YaoWenBlock = doc.getElementById("blk_yw_01");
		YaoWenBlock = YaoWenBlock.getElementById("syncad_1"); 
		System.out.println("Ҫ�� : ");
		for (Element item : YaoWenBlock.getElementsByTag("h1")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		Element JiaoDianTuBlock = doc.getElementById("Blk01_Focus_Cont");
		System.out.println("����ͼ : ");
		for (Element item : JiaoDianTuBlock.getElementsByTag("div")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		System.out.println("�������� : ");
		Element GuoNeiNewsBlock = doc.getElementById("blk_new_gnxw");
		for (Element item : GuoNeiNewsBlock.getElementsByTag("li")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
		System.out.println("�������� : ");
		Element GuoJiNewsBlock = doc.getElementById("blk_gjxw_01");
		for (Element item : GuoJiNewsBlock.getElementsByTag("li")){
			System.out.println("  - "+item.getElementsByTag("a").first().html());
		}
		
	}
	
	public void run(){
		printInetAddressInfo();
		String htmlStr =DownloadHtmlStr();
		soupParseAndPrint(htmlStr);
	}
}
