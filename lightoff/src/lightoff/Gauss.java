package lightoff;

class BreakPoint{
	int r;//断点所在的行
	int c;//当前是0还是1
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
	 * 建立开关影响的表,做高斯消元求解
	 */
	public Gauss(){
		
	}
	public void setgauss(){
		if(R.demo){
		    R.d.step_next(0);
		    R.d.settitle("根据开关的影响建立高斯消元表 According switch set Gauss elimination table");
		}
         /////////// 1. 开关的影响：建立高斯消元表  /////////////
		int i,j,col;
		//遍历 n x m 面板
		for(i=-1;++i<R.n;){
			for(j=-1;++j<R.m;){
				col = i*R.m+j;
				ls[col] = R.lightCondition[i][j];//高斯消元表最右边的常数
				//一个开关造成的影响最多五个点，最少三个点
				//第一个点：自己
				gauss[col][col] = 1;
				//第二个点：左边的点
				if(inthePanel(i,j-1)){
					gauss[i*R.m+j-1][col] = 1;
				}
				//第三个点：下边的点
				if(inthePanel(i-1,j)){
					gauss[(i-1)*R.m+j][col] = 1;
				}
				//第四个点：上边的点
				if(inthePanel(i+1,j)){
					gauss[(i+1)*R.m+j][col] = 1;
				}
				//第五个点：右边的点
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
	 * 判断该坐标是否在灯面板内
	 * @param x 行坐标
	 * @param y 列坐标
	 * @return 布尔类型
	 */
	boolean inthePanel(int x, int y){//x是行，y是列
		return x >= 0 && y >= 0 && x < R.n && y < R.m;
	}
	/**
	 * 进行异或运算
	 * @param a 数字1
	 * @param b 数字2
	 * @return a 异或 b
	 */
	int xor(int a,int b){
		return a^b;
	}
	/**
	 * 交换两个数字
	 * @param x1 第一个数字的行坐标
	 * @param x2 第二个数字的行坐标
	 * @param y 它们有个共同的列坐标
	 */
	void swap(int x1,int x2,int y){
		gauss[x1][y]^=gauss[x2][y];
		gauss[x2][y]^=gauss[x1][y];
		gauss[x1][y]^=gauss[x2][y];
	}
	/**
	 * 交换两行
	 * @param r1 其中一行
	 * @param r2 另外一行
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
	 * r1行与r2行异或以后赋给r2行
	 * @param r1 某一行
	 * @param r2 另外一行
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
		    R.d.settitle("转化成上三角形矩阵 Transformed into an upper triangular matrix");
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
						    //////以下两行表示把过程绘制在一个面板里面/////
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
				        //////以下两行表示把过程绘制在一个面板里面/////
					    R.d.add_rd(i,j);
					    R.d.rd();
					    R.sleep(R.l_sleeptime);
                        //////////////////////////////////////
					}
				}
			}
			if(R.demo){
	     	    //////以下三行表示把过程绘制在一个面板里面/////
			    R.d.swap_rd(-1,-1);
			    R.d.add_rd(-1,-1);
			    R.d.rd();
			    R.sleep(R.l_sleeptime);
                //////////////////////////////////////
			}
		}
	}
	/**
	 * 对值进行分析(X表示0或1都可以)
	 * 0 0 0 => X
	 * 0 0 1 => 无解
	 * 0 1 0 => 无解
	 * 0 1 1 => X
	 * 1 0 0 => 0
	 * 1 0 1 => 1
	 * 1 1 0 => 1
	 * 1 1 1 => 0
	 * @param a gauss表中对角线上的值
	 * @param b gauss表中对角线 右边一行的值乘以对应的answer 之和 % 2
	 * @param c 该行上的常数列
	 * @param i 该行行数
	 * @return 分析后的值
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
				// 在 X 这里设置个断点
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
		    R.d.settitle("结果分析(与反向替换类似)Result analysis");
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
	 * 输出 灯影响 的表
	 */
	void displays(){//输出高斯消元表
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