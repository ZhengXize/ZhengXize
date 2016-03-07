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
	 * 记录交换的两行
	 */
	public void swap_rd(int r1,int r2){
		sp.swap[0] = r1;
		sp.swap[1] = r2;
	}
	/*
	 * 记录消元的两行
	 */
	public void add_rd(int r1,int r2){
		sp.add[0] = r1;
		sp.add[1] = r2;
	}
	/*
	 * 记录灯的列数
	 */
	public void col_rd(int col){
		sp.col = col;
	}
	/*
	 * 重绘ShowPlay面板
	 */
	public void rd(){
		sp.repaint();
	}
	/*
	 * 下一步骤
	 */
	public void step_next(int s){
		sp.step = s;
	}
	/*
	 * 设置标题
	 */
	public void settitle(String title){
		sp.title = title;
	}
	/*
	 * 设置行
	 */
	public void setrow(int i){
		sp.row = i;
	}
	/*
	 * 设置三个值a,b,c
	 */
	public void setabc(int a,int b,int c){
		sp.a = a;
		sp.b = b;
		sp.c = c;
	}
	/*
	 * 建立影响表的时候记录当前完成的列数
	 */
	public void setcol(int y){
		sp.y = y;
	}
	public void setfVisible(boolean v){
		sp.f.setVisible(v);
	}
}

class ShowPlay extends JPanel{
	int num = R.n * R.m;//正方形边长
	public int rect_Width = 120;//矩阵的宽度
	public int rect_Height = num * 30+10;//矩阵的高度
	public static int title_Height = 30;//标题的高度
	String title = "";//标题
	int[] swap = new int[2];//记录交换的两行
	int[] add = new int[2];//记录消元的两行
	int row;//灯的行数
	int col;//灯的列数
	int a;//结果分析时候的a值(对角线值)
	int b;//结果分析时候的b值(sum%2)
	int c;//结果分析时候的c值(常数列)
	int y;//建立开关影响表的时候，建立了多少列
	int step = -1;//当前是第几步骤，调用不同的绘制方法
	String fontface = "Georgia";//字体
	int fonttype = Font.PLAIN;//字体字形是常规
	int fontsize = 20;//字体大小
	String gauss1 = "开关影响表，根据开关的影响情况建立表。";
	Frame f = new Frame();
	public ShowPlay(){
		this.setBackground(new Color(255,255,255));
	}
	public void paintGauss(Graphics g){
		int i,j;
		////////////   说明   ///////////////////////////
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.drawString("例如3x3的灯面板", 1, title_Height+20);
		g.drawString("按下第一行",1 , title_Height+30+121);
		g.drawString("第一列的开关",1,title_Height+50+121);
		g.drawString("按下第二行",1 , title_Height+60+242);
		g.drawString("第二列的开关",1,title_Height+80+242);
		g.drawString("总共能建9个表", 1, title_Height+85+220+134);
		g.drawString("(行x列)", 1, title_Height+85+220+134+20);
		g.drawString("然后竖向输出", 1, title_Height+85+220+134+40);
		try {
			g.drawImage(ImageIO.read(new File(new File("").getAbsoluteFile().getAbsoluteFile()+"\\Image\\table1.bmp")), 1,title_Height+25,100,110,this);
			g.drawImage(ImageIO.read(new File(new File("").getAbsoluteFile().getAbsoluteFile()+"\\Image\\table2.bmp")), 1,title_Height+65+110,100,110,this);
			g.drawImage(ImageIO.read(new File(new File("").getAbsoluteFile().getAbsoluteFile()+"\\Image\\table3.bmp")), 1,title_Height+85+220,100,110,this);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		/////////  1.画最左边的那个长矩阵   //////////////////////
		g.drawRect(0, title_Height, rect_Width, rect_Height);
		g.setFont(new Font(fontface,fonttype,fontsize));//设置字体
		g.setColor(new Color(0xB9,0xC0,0xB6));//设置颜色
		/////////////    根据列数画开关影响表    /////////////
		for(j=-1;++j<y;){
		    for(i=-1;++i<num;){
	            g.drawString(String.valueOf(R.g.gauss[i][j])+" "
   		                   , rect_Width + 10 + j * 30
   		                   , title_Height + 30 + i * 30);
		    }
       }
		/////////////    设置字体和颜色    ////////////////////
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
		g.setFont(new Font("微软雅黑",Font.BOLD,15));
		g.drawString("线性代数的知识：", 1, title_Height+20);
		g.drawString("把该表化成上三", 1, title_Height+40);
		g.drawString("角形矩阵,采用", 1, title_Height+60);
		g.drawString("高斯消元的方式", 1, title_Height+80);
		g.drawString("用的是异或运算", 1, title_Height+120);
		g.drawString("在setting里面", 1, title_Height+220);
		g.drawString("可以调整演示速度", 1, title_Height+240);
		g.setColor(new Color(255,0,0));
		g.drawString("红色表示交换", 1, title_Height+140);
		g.setColor(new Color(0,0,255));
		g.drawString("蓝色表示消元", 1, title_Height+160);
		g.setColor(new Color(0,255,0));
		g.drawString("绿色表示当前", 1, title_Height+180);
		g.drawString("访问的列", 1, title_Height+200);
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
		////////////////   输出 a b c ///////////////////////
		g.setColor(new Color(255,0,0));
		g.drawString(String.valueOf(a),5 , title_Height + 30 + row * 30);
		g.setColor(new Color(0,0,255));
		g.drawString(String.valueOf(b),35 , title_Height + 30 + row * 30);
		g.setColor(new Color(0,255,255));
		g.drawString(String.valueOf(c),65 , title_Height + 30 + row * 30);
		////////////////   输出答案      ///////////////////////
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
		///////////   绘制标题     //////////////////////
		g.setFont(new Font("微软雅黑",Font.BOLD,20));
		g.drawString(title,10,25);
	//	System.out.println(title);
		///////////   当前是第几步骤，调用不同的绘制函数   //////
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
			g.setFont(new Font("微软雅黑",Font.BOLD,20));
			g.setColor(new Color(0,0,0));
			g.drawString("对值进行分析(X表示0和1都可以)", 2, 20);
			g.drawString("0 0 0 => X", 2, 40);
			g.drawString("0 0 1 => 无解", 2, 60);
			g.drawString("0 1 0 => 无解", 2, 80);
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
