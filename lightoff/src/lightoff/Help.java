package lightoff;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Help extends JFrame{
	pptPanel ppt = new pptPanel();
	public Help(){
		this.add(ppt);
		this.setSize(900,721);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
}

class pptPanel extends JPanel implements MouseListener {
	private ArrayList<Image> imgName = new ArrayList<Image>();
	private int totalnum = 0;
	private int n = 0;
	public pptPanel(){
		this.addMouseListener(this);
		File f = new File("");
		String path = f.getAbsolutePath()+"\\PPT\\幻灯片";
		int num = 1;
//		System.out.println(path+String.valueOf(num)+".JPG");
		f = new File(path+String.valueOf(num++)+".BMP");
		Image i;
		while(f.exists()){
			try {
				i = ImageIO.read(f);
				imgName.add(i);
//				System.out.println(path+String.valueOf(num)+".JPG");
				f = new File(path+String.valueOf(num++)+".BMP");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		totalnum = num - 2;
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(imgName.get(n%totalnum),0,0,this.getWidth(),this.getHeight(),this);
		g.setFont(new Font("Georgia",Font.PLAIN,20));
		g.drawString(String.valueOf(n%totalnum+1)+"\\"+String.valueOf(totalnum), this.getWidth()-70, this.getHeight()-10);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	//	System.out.println(e.getButton());
		if(e.getButton()==e.BUTTON1){
			n++;
			this.repaint();
		}
		else if(e.getButton()==e.BUTTON3){
			n--;
			if(n==-1){
				n = totalnum-1;
			}
			this.repaint();
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
