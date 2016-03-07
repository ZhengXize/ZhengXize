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
	
	//������� n x m ����
	public static int n = lightCondition.length;//��
	public static int m = lightCondition[0].length;//��
			
	public static int rect_Height = 30;//�γɵľ���ĸ�
	public static int rect_Width = 30;//�γɵľ���Ŀ�
	public static int[][] lightswitch=new int[n][m];//��Щ���ذ���
	public static int frame_Height = 240+ n * rect_Height;//���ڵĸ߶�
	public static int frame_Width = 240 + m * rect_Width;//���ڵĿ��
	public static Gauss g ;//��˹��Ԫ�㷨��
	public static LightPanel lP = new LightPanel();//n x m �����
	public static ButtonPanel bP = new ButtonPanel();//��ť���
	public static SurfaceView sv;//�ص���Ϸ����
	public static Demo d;//��ʾ����
	public static double g_sleeptime = 0.3;//���ɿ���Ӱ������ͣʱ��
	public static double l_sleeptime = 0.1;//ת���������Ǿ������ͣʱ��
	public static double r_sleeptime = 0.5;//�����������ͣʱ��
	public static boolean demo = true;//�Ƿ���Ҫ��ʾ���������Ҫ���������Ϊfalse

	//����������ϽǶ��������
	public static int rect_x = 50;
	public static int rect_y = 50;
	/**
	 * �ػ� n x m ���
	 * @param _n ��
	 * @param _m ��
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
		frame_Height = 240+ n * rect_Height;//���ڵĸ߶�
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
	 * ��ͣһ��ʱ��
	 * @param i ��λ��
	 */
	public static void sleep(double i){
		try {
			Thread.sleep((int)(i*(double)1000));
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		};
	}
}
