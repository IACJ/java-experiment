import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calculator {
	public static void main(String[] args) {
		SimpleCalculator sc = new SimpleCalculator();

	}
}

class SimpleCalculator extends JFrame{
	GridLayout gl1,gl2,gl3;
	JPanel resultPanel,controlPanel,statisticsPanel,computationPanel;
	JTextField tf1;
	TextField tf2;
	Button[] btn = new Button[27];
	String[] btnCaption = {"Backspace","CE","C","MC","MR","MS","M+",
			"7","8","9","/","sqrt","4","5","6","*","%","1","2","3",
			"-","1/x","0","+/-",".","+","="};
	StringBuffer str;
	double x, y;
	int z; //z表示点击的运算符, 0表示+, 1表示-, 2表示*, 3表示/
	static double m;//记忆的数字
	
	public SimpleCalculator(){
		super("计算器");
		setLayout(null);
		setResizable(false);
		
		//实例化所有按钮,设置前景色并注册监听器
		for (int i = 0; i < 27; i++){
			btn[i] = new Button(btnCaption[i]);
			btn[i].setFont(new Font("", Font.PLAIN, 12));
			btn[i].setForeground(Color.red);
			btn[i].addActionListener(new Bt());
		}
		
		//创建结果面板,添加显示屏等
		resultPanel = new JPanel();
		resultPanel.setBounds(10, 10, 300, 40);
		tf1 = new JTextField(27); //显示屏
		tf1.setHorizontalAlignment(JTextField.RIGHT);
		tf1.setEnabled(false);
		tf1.setText("0");
		resultPanel.add(tf1);
		getContentPane().add(resultPanel);
		
		//创建控制键面板，添加记忆框及3个控制键
		controlPanel = new JPanel();
		controlPanel.setBounds(10, 50, 300, 25);
		gl1 = new GridLayout(1,4,10,0);
		controlPanel.setLayout(gl1);
		tf2 = new TextField(8);
		tf2.setEditable(false);
		controlPanel.add(tf2);
		for (int i=0; i<3; i++){
			controlPanel.add(btn[i]);
		}
		getContentPane().add(controlPanel);
		
		//添加统计面板以及4个按键;
		statisticsPanel = new JPanel();
		statisticsPanel.setBounds(10, 90, 40, 150);
		gl2 = new GridLayout(4,1,0,15);
		statisticsPanel.setLayout(gl2);
		for (int i =3; i<7; i++){
			statisticsPanel.add(btn[i]);
		}
		getContentPane().add(statisticsPanel);
		
		//添加计算面板以及数字、运算符按键
		computationPanel = new JPanel();
		computationPanel.setBounds(60, 90, 250, 150);
		gl3 = new GridLayout(4,5,10,15);
		computationPanel.setLayout(gl3);
		for (int i = 7; i<27; i++){
			computationPanel.add(btn[i]);
		}
		getContentPane().add(computationPanel);
		
		str = new StringBuffer();
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent el){
				System.exit(0);
			}
		});
		setBackground(Color.lightGray);
		setBounds(400,200,400,400);
		setVisible(true);
	}
	
	//构造监听器
	class Bt implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e2) {
			try{
				
				if (e2.getSource() == btn[1]){ //"CE"健
					tf1.setText("0");
					str.setLength(0);
				}else if (e2.getSource() == btn[2]){//"C"
					tf1.setText("0");
					str.setLength(0);
				}else if (e2.getSource() == btn[23]){//"+/-"
					x = Double.parseDouble(tf1.getText().trim());
					tf1.setText("" + (-x));
				}else if (e2.getSource() == btn[25]){ //"+"
					x = Double.parseDouble(tf1.getText().trim());
					str.setLength(0);
					y = 0d;
					z = 0;
				}else if (e2.getSource() == btn[20]){ //"-"
					x = Double.parseDouble(tf1.getText().trim());
					str.setLength(0);
					y = 0d;
					z = 1;
				}else if (e2.getSource() == btn[15]){//"*"
					x = Double.parseDouble(tf1.getText().trim());
					str.setLength(0);
					y = 0d;
					z = 2;
				} else if (e2.getSource() == btn[10]){//"/"
					x = Double.parseDouble(tf1.getText().trim());
					str.setLength(0);
					y = 0d;
					z = 3;
				} else if (e2.getSource() == btn[26]){
					str.setLength(0);
					switch (z){
					case 0:
						tf1.setText(""+(x+y));
						break;
					case 1:
						tf1.setText(""+(x-y));
						break;
					case 2:
						tf1.setText(""+(x*y));
						break;
					case 3:
						tf1.setText(""+(x/y));
						break;
					}
				}else if (e2.getSource() == btn[24]){//"."
					if (tf1.getText().trim().indexOf('.') != -1){ //已含小数点
						//傻了
					}else{
						if (tf1.getText().trim().equals("0")){
							str.setLength(0);
							tf1.setText(str.append("0"+e2.getActionCommand()).toString());
						}else if(tf1.getText().trim().equals("")){
							//又傻了
						}else{
							tf1.setText(str.append(e2.getActionCommand()).toString());
						}
					}
					y = 0d;
				}else if (e2.getSource() == btn){//"sqrt"
					x =Double.parseDouble(tf1.getText().trim());
					tf1.setText("数据格式异常");
					if (x<0){
						tf1.setText("负数没有平方根");
					}else{
						tf1.setText(""+Math.sqrt(x));
					}
					str.setLength(0);
					y = 0d;
				}else if (e2.getSource() == btn[16]){//"%"
					x = Double.parseDouble(tf1.getText().trim());
					tf1.setText(""+(0.01 * x));
					str.setLength(0);
					y = 0d;
				}else if (e2.getSource() == btn[21]){//"1/x"{
					x = Double.parseDouble(tf1.getText().trim());
					if (x==0){
						tf1.setText("除数不能为0");
					} else {
						tf1.setText(""+(1/x));
					}
					str.setLength(0);
					y = 0d;
				}else if (e2.getSource() == btn[3]){//"MC"清除内存
					m = 0d;
					tf2.setText("");
					str.setLength(0);
				}else if(e2.getSource() == btn[4]){//"MR" 重新调用存储的数据
					if (tf2.getText().trim() != ""){
						tf1.setText(""+m);
					}
				}else if (e2.getSource() == btn[5]){//"MS"存储显示的数据
					m = Double.parseDouble(tf1.getText().trim());
					tf2.setText("M");
					tf1.setText("0");
					str.setLength(0);
				}else if (e2.getSource() == btn[6]){//"M+"将显示的数字与已经存储的相加，要查看新的数字点MR
					m += Double.parseDouble(tf1.getText().trim());
				}else {
					if (e2.getSource() == btn[22]){//如果显示屏显示0,不操作
						if (tf1.getText().trim().equals("0")){
							//不操作
						}else{
							tf1.setText(str.append(e2.getActionCommand()).toString());
							y = Double.parseDouble(tf1.getText().trim());
						}
					}else if(e2.getSource() == btn[0]){//"BackSpace"
						if (!tf1.getText().trim().equals("0")){
							if (str.length() != 1){//可能抛出字符串越界异常
								tf1.setText(str.delete(str.length()-1, str.length()).toString());
							}else{
								tf1.setText("0");
								str.setLength(0);
							}
						}
						y = Double.parseDouble(tf1.getText().trim());
					}else {//其他数字键
						tf1.setText(str.append(e2.getActionCommand()).toString());
						y = Double.parseDouble(tf1.getText().trim());
					}
				}
			}catch (NumberFormatException e){
				tf1.setText("数字格式异常");
			}catch (StringIndexOutOfBoundsException e){
				tf1.setText("字符串索引越界");
			}	
		}
	}
}



