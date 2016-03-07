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
		///////////  1. ��ȡֵ��text: �ı�; fontface: ����; fonttype: ��������; fontsize: �����С  ///////////////////
		//////////            Box_x,Box_y: ��ť���ϽǶ�������λ��                                                                                          ///////////////////
		this.text = text;
		this.fontface = fontface;
		this.fonttype = fonttype;
		this.fontsize = fontsize;
		this.Box_x = Box_x;
		this.Box_y = Box_y;
		////////////// 2. ��������ĸ߶ȺͿ�ȣ��������ӵĸ߶ȺͿ��   ///////////////////////
		Font f = new Font(fontface, fonttype, fontsize);
		FontMetrics fm = new JLabel().getFontMetrics(f);
		Box_Height = fm.getHeight() ;
		Box_Width = fm.stringWidth(text) + 20;
	}
	/**
	 * ��ȡ�ı����½Ƕ��������x
	 * @return �ı����½Ƕ��������x
	 */
	public int getText_x(){
		return Box_x + 10;
	}
	/**
	 * ��ȡ�ı����½Ƕ��������y
	 * @return �ı����½Ƕ��������y
	 */
	public int getText_y(){
		return Box_y + Box_Height - 5;
	}
	/**
	 * ��ȡ���paint�����е�Graphics���������ӻ���ȥ
	 * @param g ĳ�����Graphics
	 */
	public void draw(Graphics g){
		g.setColor(new Color(0,0,0));
		g.fillRect(Box_x,Box_y,Box_Width,Box_Height);
		g.setFont(new Font(fontface,fonttype,fontsize));
		g.setColor(new Color(255,255,255));
		g.drawString(text, getText_x(), getText_y());
	}
	/**
	 * �������Ƿ񱻰���
	 * @param x ��굯���x����
	 * @param y ���̸���y����
	 * @return �������ͣ��Ƿ���
	 */
	public boolean isClicked(int x,int y){
		return x >= Box_x && y >= Box_y && x <= Box_x+Box_Width && y <= Box_y + Box_Height;
	}
}
