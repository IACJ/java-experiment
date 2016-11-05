package pac;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test {

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setTitle("使用网格布局管理器");
		
		GridLayout gridlayout = new GridLayout(2,2);
		frm.setLayout(gridlayout);
		
		JLabel label = new JLabel("课程;");
		frm.getContentPane().add(label);
		
		JCheckBox mathsButton = new JCheckBox("高等数学草草草草草草草草草");
		mathsButton.setSelected(true);
		frm.getContentPane().add(mathsButton);
		
		JCheckBox englishButton = new JCheckBox("英语");
		frm.getContentPane().add(englishButton);
		
		frm.getContentPane().add(new JLabel("")); 
		
		JCheckBox introductionButton = new JCheckBox("计算机导论 ");
		frm.getContentPane().add(introductionButton);
		
		JCheckBox programmingButton = new JCheckBox("计算机程序设计c ");
		frm.getContentPane().add(programmingButton);
		 
		frm.setBounds(450, 230, 400, 100);
		frm.setVisible(true);
	}

}
