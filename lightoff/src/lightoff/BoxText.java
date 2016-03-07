package lightoff;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JLabel;

public class BoxText{
	String text = "";
	String fontface = "";
	int fonttype = 0;
	int fontsize = 0;
	int Box_x = 0;
	int Box_y = 0;
	int Box_Height = 0;
	int Box_Width = 0;
	public BoxText(String text,String fontface,int fonttype,int fontsize,int Box_x,int Box_y){
		///////////  1. 获取值：text: 文本; fontface: 字体; fonttype: 字体类型; fontsize: 字体大小  ///////////////////
		//////////            Box_x,Box_y: 按钮左上角顶点所在位置                                                                                          ///////////////////
		this.text = text;
		this.fontface = fontface;
		this.fonttype = fonttype;
		this.fontsize = fontsize;
		this.Box_x = Box_x;
		this.Box_y = Box_y;
		////////////// 2. 根据字体的高度和宽度，设置箱子的高度和宽度   ///////////////////////
		Font f = new Font(fontface, fonttype, fontsize);
		FontMetrics fm = new JLabel().getFontMetrics(f);
		Box_Height = fm.getHeight() ;
		Box_Width = fm.stringWidth(text) + 20;
	}
	/**
	 * 获取文本左下角顶点的坐标x
	 * @return 文本左下角顶点的坐标x
	 */
	public int getText_x(){
		return Box_x + 10;
	}
	/**
	 * 获取文本左下角顶点的坐标y
	 * @return 文本左下角顶点的坐标y
	 */
	public int getText_y(){
		return Box_y + Box_Height - 5;
	}
	/**
	 * 获取面板paint函数中的Graphics来将该箱子画上去
	 * @param g 某画板的Graphics
	 */
	public void draw(Graphics g){
		g.setColor(new Color(0,0,0));
		g.fillRect(Box_x,Box_y,Box_Width,Box_Height);
		g.setFont(new Font(fontface,fonttype,fontsize));
		g.setColor(new Color(255,255,255));
		g.drawString(text, getText_x(), getText_y());
	}
	/**
	 * 看箱子是否被按下
	 * @param x 鼠标弹起的x坐标
	 * @param y 鼠标谈起的y坐标
	 * @return 布尔类型：是否按下
	 */
	public boolean isClicked(int x,int y){
		return x >= Box_x && y >= Box_y && x <= Box_x+Box_Width && y <= Box_y + Box_Height;
	}
}
