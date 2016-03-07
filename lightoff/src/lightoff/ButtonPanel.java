package lightoff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel implements MouseListener{
	BoxText bt = new BoxText("next","华文琥珀",Font.PLAIN,20,0,0);
	BoxText bt1 = new BoxText("reset","华文琥珀",Font.PLAIN,20,bt.Box_Width+10,0);
	BoxText bt2 = new BoxText("get","华文琥珀",Font.PLAIN,20,bt.Box_Width+bt1.Box_Width+20,0);
	BoxText bt3 = new BoxText("oppo","华文琥珀",Font.PLAIN,20,bt.Box_Width+bt1.Box_Width+bt2.Box_Width+30,0);
	boolean next = true;
	public ButtonPanel(){
		this.setBackground(new Color(255,255,255));
		this.addMouseListener(this);
	}
	public void paint(Graphics g){
		super.paint(g);
		bt.draw(g);
		bt1.draw(g);
		bt2.draw(g);
		bt3.draw(g);
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
		int x = e.getX();
		int y = e.getY();
		if(e.getButton()==e.BUTTON1){
		    /**
		     * 如果点击到了"next"按钮
		     */
		    if(next && R.g!=null && R.g.exist && bt.isClicked(x, y)){
			    if(R.g!=null && R.g.breakpoint_next()){
			    	if(R.demo && !R.d.isShowing()){
			    		R.d.setVisible(true);
			    	}
			    	next = false;
			    	Thread t = new Thread(new Runnable(){
			    		public void run(){
			               if(R.g.Solve()){
				              R.g.displayanswer();
				              R.lP.n++;
				              R.lP.redraw();
			               }
			    		}
			    	});
			    	t.start();
			    }
			}
		    /**
		     * 按到"reset"按钮
		     */
		    if(R.g!= null && bt1.isClicked(x, y)){
		    	R.rewriteLightCondition(R.n, R.m);
		    	R.g.exist = false;
		    	R.lP.redraw();
		    	R.lP.n = 0;
		    	R.sv.setSize();
		    }
		    /**
		     * 按到"get"按钮
		     */
		    if(bt2.isClicked(x, y)){
		    	if(R.demo){
		    	    R.d = new Demo();
		    	    R.d.setVisible(true);
		    	}
		    	Thread t = new Thread(new Runnable(){
		    		public void run(){
		    	        R.g = new Gauss();
		    	        R.g.setgauss();
		    			R.g.ga();
		    			if(R.g.Solve()){
		    				
		    				R.g.displayanswer();  
		    			}
		    			else{
		    				JOptionPane.showMessageDialog(R.sv,"该问题无解","错误",JOptionPane.ERROR_MESSAGE);
		    			}
		    			R.lP.redraw();
		    			R.lP.n = 0;
		    		}
		    	});
		        t.start();
		    }
		    /**
		     * 按到"OPPO"按钮
		     */
		    if(bt3.isClicked(x, y)){
		    	int i,j;
		    	for(i=-1;++i<R.n;){
		    		for(j=-1;++j<R.m;){
		    			R.lightCondition[i][j]^=1;
		    		}
		    	}
		    	R.lP.redraw();
		    }
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
