package pac;

import java.awt.event.*; 
import java.awt.*; 
 
import javax.swing.*; 
/* 
 ���������������򆢄�� 
 */ 
public class StartChessJFrame extends JFrame { 
 private ChessBoard chessBoard; 
 private JPanel toolbar; 
 private JButton startButton,backButton,exitButton; 
 
 private JMenuBar menuBar; 
 private JMenu sysMenu; 
 private JMenuItem startMenuItem,exitMenuItem,backMenuItem; 
 //���¿�ʼ���˳����ͻ���˵��� 
 public StartChessJFrame(){ 
  setTitle("������������");//���ñ��� 
  chessBoard=new ChessBoard(); 
  
  
  Container contentPane=getContentPane(); 
  contentPane.add(chessBoard); 
  chessBoard.setOpaque(true); 
  
  
  //��������Ӳ˵� 
  menuBar =new JMenuBar();//��ʼ���˵��� 
  sysMenu=new JMenu("ϵͳ");//��ʼ���˵� 
  //��ʼ���˵��� 
  startMenuItem=new JMenuItem("���¿�ʼ"); 
  exitMenuItem =new JMenuItem("�˳�"); 
  backMenuItem =new JMenuItem("����"); 
  //�������˵�����ӵ��˵��� 
  sysMenu.add(startMenuItem); 
  sysMenu.add(exitMenuItem); 
  sysMenu.add(backMenuItem); 
  //��ʼ����ť�¼��������ڲ��� 
  MyItemListener lis=new MyItemListener(); 
  //�������˵�ע�ᵽ�¼��������� 
  this.startMenuItem.addActionListener(lis); 
  backMenuItem.addActionListener(lis); 
  exitMenuItem.addActionListener(lis); 
  menuBar.add(sysMenu);//��ϵͳ�˵���ӵ��˵����� 
  setJMenuBar(menuBar);//��menuBar����Ϊ�˵��� 
  
  toolbar=new JPanel();//�������ʵ���� 
  //������ť��ʼ�� 
  startButton=new JButton("���¿�ʼ"); 
  exitButton=new JButton("�˳�"); 
  backButton=new JButton("����"); 
  //��������尴ť��FlowLayout���� 
  toolbar.setLayout(new FlowLayout(FlowLayout.LEFT)); 
  //��������ť��ӵ�������� 
  toolbar.add(startButton); 
  toolbar.add(exitButton); 
  toolbar.add(backButton); 
  //��������ťע������¼� 
  startButton.addActionListener(lis); 
  exitButton.addActionListener(lis); 
  backButton.addActionListener(lis); 
  //��������岼�ֵ����桱�Ϸ���Ҳ�����·� 
  add(toolbar,BorderLayout.SOUTH); 
  add(chessBoard);//����������ӵ������� 
  //���ý���ر��¼� 
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  //setSize(800,800); 
  pack();//����Ӧ��С 
  
 } 
 
 private class MyItemListener implements ActionListener{ 
  public void actionPerformed(ActionEvent e){ 
   Object obj=e.getSource();//����¼�Դ 
   if(obj==StartChessJFrame.this.startMenuItem||obj==startButton){ 
    //���¿�ʼ 
    //JFiveFrame.this�ڲ��������ⲿ�� 
    System.out.println("���¿�ʼ"); 
    chessBoard.restartGame(); 
   } 
   else if (obj==exitMenuItem||obj==exitButton) 
    System.exit(0); 
   else if (obj==backMenuItem||obj==backButton){ 
    System.out.println("����..."); 
    chessBoard.goback(); 
   } 
  } 
 } 
 
 
 
 public static void main(String[] args){ 
  StartChessJFrame f=new StartChessJFrame();//��������� 
  f.setVisible(true);//��ʾ����� 
  
 } 
} 