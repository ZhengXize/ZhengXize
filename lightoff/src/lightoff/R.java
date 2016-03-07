package lightoff;


public class R {
	/*
 	public static int lightCondition[][] = {
		{1,0,0,1,1},
		{1,0,0,1,1},
		{1,0,0,1,1},
		{1,0,0,1,1},
	};
	 */
	public static int lightCondition[][] = {
		{1,1,1,1},
		{1,1,1,1},
		{1,1,1,1},
		{1,1,1,1},
	};
	
	//灯面板有 n x m 个灯
	public static int n = lightCondition.length;//行
	public static int m = lightCondition[0].length;//列
			
	public static int rect_Height = 30;//形成的矩阵的高
	public static int rect_Width = 30;//形成的矩阵的宽
	public static int[][] lightswitch=new int[n][m];//哪些开关按过
	public static int frame_Height = 240+ n * rect_Height;//窗口的高度
	public static int frame_Width = 240 + m * rect_Width;//窗口的宽度
	public static Gauss g ;//高斯消元算法类
	public static LightPanel lP = new LightPanel();//n x m 灯面板
	public static ButtonPanel bP = new ButtonPanel();//按钮面板
	public static SurfaceView sv;//关灯游戏界面
	public static Demo d;//演示界面
	public static double g_sleeptime = 0.3;//生成开关影响表的暂停时间
	public static double l_sleeptime = 0.1;//转化成上三角矩阵的暂停时间
	public static double r_sleeptime = 0.5;//结果分析的暂停时间
	public static boolean demo = true;//是否需要演示。如果不需要请把这里设为false

	//矩阵面板左上角顶点的坐标
	public static int rect_x = 50;
	public static int rect_y = 50;
	/**
	 * 重绘 n x m 面板
	 * @param _n 行
	 * @param _m 列
	 */
	public static void rewriteLightCondition(int _n,int _m){
		n=_n;
		m=_m;
		if(n>21){
			rect_Height = rect_Width = 20;
		}
		else{
			rect_Height = rect_Width = 30;
		}
		frame_Height = 240+ n * rect_Height;//窗口的高度
		frame_Width = 240 + m * rect_Width;
		lightCondition=new int[n][m];
		lightswitch=new int[n][m];
		
		int i,j;
		for(i=-1;++i<n;){
			for(j=-1;++j<m;){
				lightCondition[i][j]=1;//(int)(Math.random()*100)%2;
			}
		}
		sv.setSize();
		
	}
	/**
	 * 暂停一段时间
	 * @param i 单位秒
	 */
	public static void sleep(double i){
		try {
			Thread.sleep((int)(i*(double)1000));
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		};
	}
}
