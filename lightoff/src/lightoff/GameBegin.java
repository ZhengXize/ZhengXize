package lightoff;

import java.lang.reflect.InvocationTargetException;

public class GameBegin {
	public static void main(String[] args){
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable(){
				public void run(){
				     R.sv = new SurfaceView();
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
