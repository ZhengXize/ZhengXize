package lightoff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo extends JFrame{
	int num = R.n * R.m;
	public boolean running = false;
	public boolean noanswer = false;
	private ShowPlay sp = new ShowPlay();
	public Demo(){
		this.setTitle("Demo");
		this.setSize(120 + (num * num + 1) * 2 + 150,
				     num * 30+ 50 + ShowPlay.title_Height);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		sp.setBounds(0,0,this.getWidth(),this.getHeight());
		this.add(sp);
	}
	/*
	 * ��¼����������
	 */
	public void swap_rd(int r1,int r2){
		sp.swap[0] = r1;
		sp.swap[1] = r2;
	}
	/*
	 * ��¼��Ԫ������
	 */
	public void add_rd(int r1,int r2){
		sp.add[0] = r1;
		sp.add[1] = r2;
	}
	/*
	 * ��¼�Ƶ�����
	 */
	public void col_rd(int col){
		sp.col = col;
	}
	/*
	 * �ػ�ShowPlay���
	 */
	public void rd(){
		sp.repaint();
	}
	/*
	 * ��һ����
	 */
	public void step_next(int s){
		sp.step = s;
	}
	/*
	 * ���ñ���
	 */
	public void settitle(String title){
		sp.title = title;
	}
	/*
	 * ������
	 */
	public void setrow(int i){
		sp.row = i;
	}
	/*
	 * ��������ֵa,b,c
	 */
	public void setabc(int a,int b,int c){
		sp.a = a;
		sp.b = b;
		sp.c = c;
	}
	/*
	 * ����Ӱ����ʱ���¼��ǰ��ɵ�����
	 */
	public void setcol(int y){
		sp.y = y;
	}
	public void setfVisible(boolean v){
		sp.f.setVisible(v);
	}
}

class ShowPlay extends JPanel{
	int num = R.n * R.m;//�����α߳�
	public int rect_Width = 120;//����Ŀ��
	public int rect_Height = num * 30+10;//����ĸ߶�
	public static int title_Height = 30;//����ĸ߶�
	String title = "";//����
	int[] swap = new int[2];//��¼����������
	int[] add = new int[2];//��¼��Ԫ������
	int row;//�Ƶ�����
	int col;//�Ƶ�����
	int a;//�������ʱ���aֵ(�Խ���ֵ)
	int b;//�������ʱ���bֵ(sum%2)
	int c;//�������ʱ���cֵ(������)
	int y;//��������Ӱ����ʱ�򣬽����˶�����
	int step = -1;//��ǰ�ǵڼ����裬���ò�ͬ�Ļ��Ʒ���
	String fontface = "Georgia";//����
	int fonttype = Font.PLAIN;//���������ǳ���
	int fontsize = 20;//�����С
	String gauss1 = "����Ӱ������ݿ��ص�Ӱ�����������";
	Frame f = new Frame();
	public ShowPlay(){
		this.setBackground(new Color(255,255,255));
	}
	public void paintGauss(Graphics g){
		int i,j;
		////////////   ˵��   ///////////////////////////
		g.setFont(new Font("΢���ź�",Font.BOLD,15));
		g.drawString("����3x3�ĵ����", 1, title_Height+20);
		g.drawString("���µ�һ��",1 , title_Height+30+121);
		g.drawString("��һ�еĿ���",1,title_Height+50+121);
		g.drawString("���µڶ���",1 , title_Height+60+242);
		g.drawString("�ڶ��еĿ���",1,title_Height+80+242);
		g.drawString("�ܹ��ܽ�9����", 1, title_Height+85+220+134);
		g.drawString("(��x��)", 1, title_Height+85+220+134+20);
		g.drawString("Ȼ���������", 1, title_Height+85+220+134+40);
		try {
			g.drawImage(ImageIO.read(new File(new File("").getAbsoluteFile().getAbsoluteFile()+"\\Image\\table1.bmp")), 1,title_Height+25,100,110,this);
			g.drawImage(ImageIO.read(new File(new File("").getAbsoluteFile().getAbsoluteFile()+"\\Image\\table2.bmp")), 1,title_Height+65+110,100,110,this);
			g.drawImage(ImageIO.read(new File(new File("").getAbsoluteFile().getAbsoluteFile()+"\\Image\\table3.bmp")), 1,title_Height+85+220,100,110,this);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		/////////  1.������ߵ��Ǹ�������   //////////////////////
		g.drawRect(0, title_Height, rect_Width, rect_Height);
		g.setFont(new Font(fontface,fonttype,fontsize));//��������
		g.setColor(new Color(0xB9,0xC0,0xB6));//������ɫ
		/////////////    ��������������Ӱ���    /////////////
		for(j=-1;++j<y;){
		    for(i=-1;++i<num;){
	            g.drawString(String.valueOf(R.g.gauss[i][j])+" "
   		                   , rect_Width + 10 + j * 30
   		                   , title_Height + 30 + i * 30);
		    }
       }
		/////////////    �����������ɫ    ////////////////////
	   g.setFont(new Font(fontface,Font.BOLD,fontsize));
	   g.setColor(new Color(255,0,0));
	   for(i=-1;++i<num;){
          g.drawString(String.valueOf(R.g.gauss[i][j])+" "
                     , rect_Width + 10 + j * 30
                     , title_Height + 30 + i * 30);
	   }
	}
	public void paintga(Graphics g){
		int i,j;
		g.drawRect(0, title_Height, rect_Width, rect_Height);
		g.setFont(new Font("΢���ź�",Font.BOLD,15));
		g.drawString("���Դ�����֪ʶ��", 1, title_Height+20);
		g.drawString("�Ѹñ�������", 1, title_Height+40);
		g.drawString("���ξ���,����", 1, title_Height+60);
		g.drawString("��˹��Ԫ�ķ�ʽ", 1, title_Height+80);
		g.drawString("�õ����������", 1, title_Height+120);
		g.drawString("��setting����", 1, title_Height+220);
		g.drawString("���Ե�����ʾ�ٶ�", 1, title_Height+240);
		g.setColor(new Color(255,0,0));
		g.drawString("��ɫ��ʾ����", 1, title_Height+140);
		g.setColor(new Color(0,0,255));
		g.drawString("��ɫ��ʾ��Ԫ", 1, title_Height+160);
		g.setColor(new Color(0,255,0));
		g.drawString("��ɫ��ʾ��ǰ", 1, title_Height+180);
		g.drawString("���ʵ���", 1, title_Height+200);
		for(i=-1;++i<num;){
			for(j=-1;++j<num;){
				if((i==swap[0]&&j>=col) || (i==swap[1]&&j>=col)){
					g.setFont(new Font(fontface,Font.BOLD,fontsize));
					g.setColor(new Color(255,0,0));
				}
				else if( (i == add[0]&&j>=col) || (i == add[1]&&j>=col)){
					g.setFont(new Font(fontface,Font.BOLD,fontsize));
					g.setColor(new Color(0,0,255));
				}
				else if(j == col){
					g.setFont(new Font(fontface,Font.BOLD,fontsize));
					g.setColor(new Color(0,255,0));					
				}
				else
				{
					g.setFont(new Font(fontface,fonttype,fontsize));
					g.setColor(new Color(0xB9,0xC0,0xB6));
				}
				
			    g.drawString(String.valueOf(R.g.gauss[i][j])+" "
			    		     , rect_Width + 10 + j * 30
			    		     , title_Height + 30 + i * 30);
			}
			g.setColor(new Color(0,255,255));
			g.drawString(String.valueOf(R.g.ls[i]), 
					    rect_Width + 10 + j * 30, 
					    title_Height + 30 + i * 30);
		}
	}
	public void paintSolve(Graphics g){
		int i = 0,j = 0,k;
		g.setColor(new Color(0,0,0));
		g.drawRect(0, title_Height, rect_Width, rect_Height);
		////////////////   ��� a b c ///////////////////////
		g.setColor(new Color(255,0,0));
		g.drawString(String.valueOf(a),5 , title_Height + 30 + row * 30);
		g.setColor(new Color(0,0,255));
		g.drawString(String.valueOf(b),35 , title_Height + 30 + row * 30);
		g.setColor(new Color(0,255,255));
		g.drawString(String.valueOf(c),65 , title_Height + 30 + row * 30);
		////////////////   �����      ///////////////////////
		for(k=num;--k>=row;){
			g.setColor(new Color(0,0,0));
			g.drawString(String.valueOf(R.g.answer[k]), 
						 95,
						 title_Height + 30 + k * 30);
		}
		for(i=-1;++i<num;){
			for(j=-1;++j<num;){
				if(i==row && j>=row){
					if(j==row){
						g.setFont(new Font(fontface,Font.BOLD,fontsize));
						g.setColor(new Color(255,0,0));
					}
					else{
						g.setFont(new Font(fontface,Font.BOLD,fontsize));
						g.setColor(new Color(0,0,255));
					}
				}
				else{
					g.setFont(new Font(fontface,fonttype,fontsize));
					g.setColor(new Color(0xB9,0xC0,0xB6));
				}
		        g.drawString(String.valueOf(R.g.gauss[i][j])+" ", 
				             rect_Width + 10 + j * 30, 
				             title_Height + 30 + i * 30);
	        }
			g.setColor(new Color(0,255,255));
			g.drawString(String.valueOf(R.g.ls[i]), 
					    rect_Width + 10 + j * 30, 
					    title_Height + 30 + i * 30);
		}
	}

	public void paint(Graphics g){
		super.paint(g);
		///////////   ���Ʊ���     //////////////////////
		g.setFont(new Font("΢���ź�",Font.BOLD,20));
		g.drawString(title,10,25);
	//	System.out.println(title);
		///////////   ��ǰ�ǵڼ����裬���ò�ͬ�Ļ��ƺ���   //////
		switch(step){
		   case 0:
			  paintGauss(g);
			  break;
		   case 1:
		      paintga(g);
		      break;
		   case 2:
			  paintSolve(g);
			  break;
		};
	}
}

class Frame extends JFrame{
	String path = new File("").getAbsolutePath();
	class Panel extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(new Color(255,255,255));
			g.fillRect(0,0,this.getWidth(),this.getHeight());
			g.setFont(new Font("΢���ź�",Font.BOLD,20));
			g.setColor(new Color(0,0,0));
			g.drawString("��ֵ���з���(X��ʾ0��1������)", 2, 20);
			g.drawString("0 0 0 => X", 2, 40);
			g.drawString("0 0 1 => �޽�", 2, 60);
			g.drawString("0 1 0 => �޽�", 2, 80);
			g.drawString("0 1 1 => X", 2, 100);
			g.drawString("1 0 0 => 0", 2, 120);
			g.drawString("1 0 1 => 1", 2, 140);
			g.drawString("1 1 0 => 1", 2, 160);
			g.drawString("1 1 1 => 0", 2, 180);
		}
	}
	Panel p = new Panel();
	public Frame(){
		this.setBounds(924,117,312,221);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.add(p);
	}
	
}
