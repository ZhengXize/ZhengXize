package lightoff;

class BreakPoint{
	int r;//�ϵ����ڵ���
	int c;//��ǰ��0����1
	public BreakPoint(){
		r = -1;
		c = -1;
	}
}

public class Gauss {
	int num = R.n * R.m;
	int[] ls = new int[num];
	int[][] gauss = new int[num][num];
	int[] answer =  new int[num];
	BreakPoint[] bp = new BreakPoint[num];
	boolean exist = false;
	int n = 0;
	/**
	 * ��������Ӱ��ı�,����˹��Ԫ���
	 */
	public Gauss(){
		
	}
	public void setgauss(){
		if(R.demo){
		    R.d.step_next(0);
		    R.d.settitle("���ݿ��ص�Ӱ�콨����˹��Ԫ�� According switch set Gauss elimination table");
		}
         /////////// 1. ���ص�Ӱ�죺������˹��Ԫ��  /////////////
		int i,j,col;
		//���� n x m ���
		for(i=-1;++i<R.n;){
			for(j=-1;++j<R.m;){
				col = i*R.m+j;
				ls[col] = R.lightCondition[i][j];//��˹��Ԫ�����ұߵĳ���
				//һ��������ɵ�Ӱ���������㣬����������
				//��һ���㣺�Լ�
				gauss[col][col] = 1;
				//�ڶ����㣺��ߵĵ�
				if(inthePanel(i,j-1)){
					gauss[i*R.m+j-1][col] = 1;
				}
				//�������㣺�±ߵĵ�
				if(inthePanel(i-1,j)){
					gauss[(i-1)*R.m+j][col] = 1;
				}
				//���ĸ��㣺�ϱߵĵ�
				if(inthePanel(i+1,j)){
					gauss[(i+1)*R.m+j][col] = 1;
				}
				//������㣺�ұߵĵ�
				if(inthePanel(i,j+1)){
					gauss[i*R.m+j+1][col] = 1;
				}
				if(R.demo){
				    R.d.setcol(col);
				    R.d.rd();
				    R.sleep(R.g_sleeptime);
				}
				
			}
		}
	}
	/**
	 * �жϸ������Ƿ��ڵ������
	 * @param x ������
	 * @param y ������
	 * @return ��������
	 */
	boolean inthePanel(int x, int y){//x���У�y����
		return x >= 0 && y >= 0 && x < R.n && y < R.m;
	}
	/**
	 * �����������
	 * @param a ����1
	 * @param b ����2
	 * @return a ��� b
	 */
	int xor(int a,int b){
		return a^b;
	}
	/**
	 * ������������
	 * @param x1 ��һ�����ֵ�������
	 * @param x2 �ڶ������ֵ�������
	 * @param y �����и���ͬ��������
	 */
	void swap(int x1,int x2,int y){
		gauss[x1][y]^=gauss[x2][y];
		gauss[x2][y]^=gauss[x1][y];
		gauss[x1][y]^=gauss[x2][y];
	}
	/**
	 * ��������
	 * @param r1 ����һ��
	 * @param r2 ����һ��
	 */
	void swap(int r1,int r2){
		int i;
		for(i=-1;++i<num;){
			swap(r1,r2,i);
		}
		ls[r1]^=ls[r2];
		ls[r2]^=ls[r1];
		ls[r1]^=ls[r2];
	}
	/**
	 * r1����r2������Ժ󸳸�r2��
	 * @param r1 ĳһ��
	 * @param r2 ����һ��
	 */
	void add(int r1,int r2){
		int i;
		for(i=-1;++i<num;){
		    	gauss[r2][i]=xor(gauss[r1][i],gauss[r2][i]);
		}
		ls[r2]=xor(ls[r1],ls[r2]);
	}
	void ga(){
		if(R.demo){
		    R.d.step_next(1);
		    R.d.settitle("ת�����������ξ��� Transformed into an upper triangular matrix");
		}
		int i,j;
		for(i=-1;++i<num;){
			if(R.demo){
			    R.d.col_rd(i);
			}
			j = i;
			if(gauss[i][i]==0){
				for(;++j<num;){
					if(gauss[j][i]==1){
						swap(i,j);
						if(R.demo){
						    //////�������б�ʾ�ѹ��̻�����һ���������/////
						    R.d.swap_rd(i,j);
						    R.d.rd();
						    R.sleep(R.l_sleeptime);
						    //////////////////////////////////////
						}
						break;
					}
				}
			}
			for(;++j<num;){
				if(gauss[j][i]==1){
					add(i,j);
					if(R.demo){
				        //////�������б�ʾ�ѹ��̻�����һ���������/////
					    R.d.add_rd(i,j);
					    R.d.rd();
					    R.sleep(R.l_sleeptime);
                        //////////////////////////////////////
					}
				}
			}
			if(R.demo){
	     	    //////�������б�ʾ�ѹ��̻�����һ���������/////
			    R.d.swap_rd(-1,-1);
			    R.d.add_rd(-1,-1);
			    R.d.rd();
			    R.sleep(R.l_sleeptime);
                //////////////////////////////////////
			}
		}
	}
	/**
	 * ��ֵ���з���(X��ʾ0��1������)
	 * 0 0 0 => X
	 * 0 0 1 => �޽�
	 * 0 1 0 => �޽�
	 * 0 1 1 => X
	 * 1 0 0 => 0
	 * 1 0 1 => 1
	 * 1 1 0 => 1
	 * 1 1 1 => 0
	 * @param a gauss���жԽ����ϵ�ֵ
	 * @param b gauss���жԽ��� �ұ�һ�е�ֵ���Զ�Ӧ��answer ֮�� % 2
	 * @param c �����ϵĳ�����
	 * @param i ��������
	 * @return �������ֵ
	 */
	int analyze(int a,int b,int c,int i){
		int col = 0;
		if(R.demo){
			R.d.setabc(a, b, c);
		}
		if(a==1){
			return Math.abs(b-c);
		}
		else if(a==0){
			if(b==c){
				// �� X �������ø��ϵ�
				col = getbp(i);
				if(col == -1){
					bp[n] = new BreakPoint();
					bp[n].r = i;
					bp[n].c = 0;
					n++;
					return 0;
				}
				return col;
				
			}
			else{
				return -1;
			}
		}
		return 0;
	}
	boolean Solve(){
		if(R.demo){
			R.d.setfVisible(true);
		    R.d.step_next(2);
		    R.d.settitle("�������(�뷴���滻����)Result analysis");
		}
		int i,j,sum;
		for(i=num;--i>=0;){
			if(R.demo){
			    R.d.setrow(i);
			}
			sum=0;
			for(j=num;--j>i;){
				sum+=gauss[i][j]*answer[j];
			}
			answer[i]=analyze(gauss[i][i],sum%2,ls[i],i);
			if(R.demo){
	     		R.d.rd();
			    R.sleep(R.r_sleeptime);
			}
			if(answer[i]==-1){
				return false;
			}
		}
		exist = true;
		R.bP.next = true;
		if(R.demo){
			R.d.setVisible(false);
		    R.d.setfVisible(false);
		}
		return true;
	}
	public boolean check(){
		int i,j,sum;
		for(i=-1;++i<num;){
			sum=0;
			for(j=-1;++j<num;){
				sum+=gauss[i][j]*answer[j];
			}
			if(sum%2!=ls[i]){
				return false;
			}
		}
		return true;
	}
	public boolean breakpoint_next(){
		if(n==0)return false;
		int i;
		for(i=n;--i>=0;){
			if(bp[i].c==0){
				bp[i].c ^= 1;
				break;
			}
			bp[i].c ^= 1;
		}
		return true;
	}
	public int getbp(int r){
		int i;
		for(i=-1;++i<n;){
			if(bp[i].r == r){
				return bp[i].c;
			}
		}
		return -1;
	}
	void displayBreakPoint(){
		int i;
		for(i=-1;++i<n;){
			System.out.print(bp[i].c+ " ");
		}
		System.out.println();
	}
	void displayanswer(){
		int i,j;
		for(i=-1;++i<R.n;){
			for(j=-1;++j<R.m;){
				R.lightswitch[i][j] = answer[i*R.m+j];
			}
		}
	}
	/**
	 * ��� ��Ӱ�� �ı�
	 */
	void displays(){//�����˹��Ԫ��
		int i,j;
		for(i=-1;++i<num;){
			for(j=-1;++j<num-1;){
				System.out.print(gauss[i][j]+" ");
			}
			System.out.println(gauss[i][j]+" "+ls[i]);
		}
		System.out.println();
	}
}