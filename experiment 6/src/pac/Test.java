package pac;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test {

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setTitle("ʹ�����񲼾ֹ�����");
		
		GridLayout gridlayout = new GridLayout(2,2);
		frm.setLayout(gridlayout);
		
		JLabel label = new JLabel("�γ�;");
		frm.getContentPane().add(label);
		
		JCheckBox mathsButton = new JCheckBox("�ߵ���ѧ�ݲݲݲݲݲݲݲݲ�");
		mathsButton.setSelected(true);
		frm.getContentPane().add(mathsButton);
		
		JCheckBox englishButton = new JCheckBox("Ӣ��");
		frm.getContentPane().add(englishButton);
		
		frm.getContentPane().add(new JLabel("")); 
		
		JCheckBox introductionButton = new JCheckBox("��������� ");
		frm.getContentPane().add(introductionButton);
		
		JCheckBox programmingButton = new JCheckBox("������������c ");
		frm.getContentPane().add(programmingButton);
		 
		frm.setBounds(450, 230, 400, 100);
		frm.setVisible(true);
	}

}
