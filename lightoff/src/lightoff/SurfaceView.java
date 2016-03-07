package lightoff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SurfaceView extends JFrame implements ActionListener{
	Frame_Setting fs = new Frame_Setting();
	Help h = new Help();
	public SurfaceView(){
		//////////  1.设置窗口大小                   /////////
	//	System.out.println(R.frame_Width+" "+R.frame_Height);
		this.setBounds(300,0,R.frame_Width,R.frame_Height);
		this.setTitle("LightOFF Game");
		//////////  2.设置按下X键后关闭窗口   /////////
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(true);
		//////////  3.添加菜单组件                //////////
		JMenuBar JMB = new JMenuBar();
		this.setJMenuBar(JMB);
		JMenu JM_File = new JMenu("File");
		JMenu JM_Help = new JMenu("Help");
		JMB.add(JM_File);
		JMB.add(JM_Help);
		JMenuItem JI_set = new JMenuItem("setting");
		JMenuItem JI_help = new JMenuItem("help"); 
		JM_File.add(JI_set);
		JM_Help.add(JI_help);
		JI_set.addActionListener(this);
		JI_help.addActionListener(this);
		JMenuItem JI_Demo = new JMenuItem("");
		if(R.demo){
			JI_Demo.setText("Demo √");
		}
		else{
			JI_Demo.setText("Demo");
		}
		JI_Demo.addActionListener(this);
		JM_File.add(JI_Demo);
		//////////  4.添加按钮面板和灯面板  //////////
		
		R.bP.setBounds(0,0,R.frame_Width,R.bP.bt.Box_Height+10);
		this.add(R.bP);
		R.lP.setBounds(0,R.bP.getHeight(),R.frame_Width,(int)((double)R.frame_Height*0.9));
		this.add(R.lP);
		setVisible(true);
	}
	public void setSize(){
		this.setSize(R.frame_Width,R.frame_Height);
		R.bP.setSize(R.frame_Width,R.bP.bt.Box_Height+10);
		R.lP.setSize(R.frame_Width,(int)((double)R.frame_Height*0.9));
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("setting")){
			fs.setVisible(true);
		}
		else if(e.getActionCommand().equals("Demo √")){
			R.demo = false;
			((JMenuItem)e.getSource()).setText("Demo");
		}
		else if(e.getActionCommand().equals("Demo")){
			R.demo = true;
			((JMenuItem)e.getSource()).setText("Demo √");
		}
		else if(e.getActionCommand().equals("help")){
			h.setVisible(true);
		}
	}
}

class Frame_Setting extends JFrame implements ActionListener{
	String[] rowandcol ={"1","2","3","4","5","6","7","8","9","10",
			     "11","12","13","14","15","16","17","18","19","20",
			     "21","22","23","24","25","26","27","28","29","30",
			     "31","32"};
	String[] pausetime = {"0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0"};
	JComboBox jCB;
	JComboBox jCB1;
	JComboBox jCB2;
	public Frame_Setting(){
		int i;
		this.setTitle("Setting");
		this.setBounds(0,0,200,190);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		//////////// 1. 设置行与列    //////////
		//行和列的标签
		JLabel label_row = new JLabel("行：");
		label_row.setBounds(20,30,30,30);
		JLabel label_col = new JLabel("列：");
		label_col.setBounds(20,60,30,30);
		JLabel label_pt = new JLabel("暂停时间:");
		label_pt.setBounds(0,85,90,30);
		this.add(label_row);
		this.add(label_col);
		this.add(label_pt);
		//行和列的下拉列表
		jCB = new JComboBox();
		jCB1 = new JComboBox();
		for(i=-1;++i<rowandcol.length;){
			jCB.addItem(rowandcol[i]);
			jCB1.addItem(rowandcol[i]);
		}
		jCB2 = new JComboBox();
		for(i=-1;++i<pausetime.length;){
			jCB2.addItem(pausetime[i]);
		}
		jCB.setBounds(60,30,100,20);
		jCB1.setBounds(60,60,100,20);
		jCB2.setBounds(60,90,100,20);

		this.add(jCB);
		this.add(jCB1);
		this.add(jCB2);
		//按钮
		JButton b = new JButton("确定");
		b.setBounds(60,120,60,40);
		b.addActionListener(this);
		this.add(b);
		jCB.setSelectedIndex(3);
		jCB1.setSelectedIndex(3);
		jCB2.setSelectedIndex(2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("确定")){
			R.rewriteLightCondition(Integer.parseInt(jCB.getSelectedItem().toString()),
					                Integer.parseInt(jCB1.getSelectedItem().toString()));
			R.g_sleeptime = R.l_sleeptime = R.r_sleeptime = Double.parseDouble(jCB2.getSelectedItem().toString());
			this.setVisible(false);
		}
	}
}
