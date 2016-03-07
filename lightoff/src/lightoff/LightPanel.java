package lightoff;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class LightPanel extends JPanel implements MouseListener{
	private int light_x;//鼠标点击到的灯_列
	private int light_y;//速表点击到的灯_行
	public int n = 0;//第几个答案
	public LightPanel(){
		//this.setSize(R.frame_Width,(int)((double)R.frame_Height*0.8));
		this.setBackground(new Color(255,255,255));
		this.addMouseListener(this);
	}
	public void redraw(){
		this.repaint();
	}
	public void paint(Graphics g) {  
		super.paint(g); 
		int i,j;
		int num =(R.g!=null && R.g.exist)?(int)Math.pow(2,R.g.n):1;
		g.setColor(new Color(0,0,0));
		g.setFont(new Font("Georgia",Font.PLAIN,20));
		g.drawString(String.valueOf((n)%(num)+1)+"\\"+String.valueOf(num),  R.rect_x, R.rect_y-3);
		for(i=-1;++i<R.n;){//行
			for(j=-1;++j<R.m;){//列
				((Graphics2D)g).setStroke(new   BasicStroke(1.0f));
				///////   1.    绘制边框线   ///////////
				g.setColor(Color.white);
				g.drawRect(R.rect_x + j * R.rect_Width,
						   R.rect_y + i * R.rect_Height, 
						   R.rect_Width,
						   R.rect_Height);
			    ///////    2.    填充矩形    ///////////
				if(R.lightCondition[i][j]==0){
			         g.setColor(new Color(0x32,0x32,0x32));
				}
				else if(R.lightCondition[i][j]==1){
					g.setColor(new Color(0,0xC8,00));
				}
			    g.fillRect(R.rect_x+j*R.rect_Width+1, 
			    		R.rect_y+i*R.rect_Height+1, 
			    		   R.rect_Width-1,
			    		   R.rect_Height-1);
			    ////////// 3.  看哪些开关被按下，按下的开关画圆 ////////
			    if(R.lightswitch[i][j]==1){
			    	((Graphics2D)g).setStroke(new   BasicStroke(1.5f));
			    	g.setColor(new Color(255,255,255));
			    	int oval_x = (R.rect_x+j*R.rect_Width)+(int)((double)R.rect_Width*0.4);//行
			    	int oval_y = (R.rect_y+i*R.rect_Height)+(int)((double)R.rect_Height*0.4);//列
			    	g.drawOval(oval_x,oval_y ,(int)((double)R.rect_Height*0.2) ,(int)((double)R.rect_Width*0.2 ));
			    }
			}
       }
  }
	boolean isLight(double x,double y){
		double i = (y-R.rect_x)/R.rect_Height;//行
		double j = (x-R.rect_y)/R.rect_Width;//列
		if( i>=0 && i< R.n && j>=0 && j< R.m){
			this.light_x = (int)i;
			this.light_y = (int)j;
			return true;
		}
		return false;
	}
	
	void isinLightPanel(int i,int j){
		if(i>=0 && i<R.n && j>=0 && j<R.m){
			R.lightCondition[i][j] = R.lightCondition[i][j] ^ 1;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		int x = e.getX();
		int y = e.getY();
		int i = 0,j = 0;
		if(e.getButton()==e.BUTTON1 && isLight(x,y)){
			//开关被按下
			//开关被按下
			R.lightswitch[light_x][light_y] = R.lightswitch[light_x][light_y] ^ 1;
			//第一个点，自身
			R.lightCondition[light_x][light_y] = R.lightCondition[light_x][light_y] ^ 1;
			//第二个点，上方的点
			isinLightPanel(light_x,light_y-1);
			//第三个点，左边的点
			isinLightPanel(light_x-1,light_y);
			//第四个点，下边的点
			isinLightPanel(light_x,light_y+1);
			//第五个点，右边的点
			isinLightPanel(light_x+1,light_y);
			redraw();
		}
		else if(e.getButton()==e.BUTTON3 && isLight(x,y)){
			R.lightCondition[light_x][light_y] = R.lightCondition[light_x][light_y] ^ 1;
			redraw();
		}
		
		
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
