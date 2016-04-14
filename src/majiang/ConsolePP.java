package majiang;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class ConsolePP extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8778607205802017522L;
	/**
	 * 有108麻将，every[0]没有用
	 */
	every mj[]=new every[109];// 有108麻将，every[0]没有用
	int currentmj;// 没有被摸的牌的第一张
	/**
	 * 当前玩家
	 */
	int currentPlayer;// 当前玩家
	/**
	 * 用户已经点击的按钮
	 */
	int jbDown=-1;// 用户已经点击的按钮
	/**
	 * 各位玩家打出的牌
	 */
	every nowMj=null;// 各位玩家打出来的牌
	/**
	 * 用于显示当前玩家打出来的牌
	 */
	JLabel nowMajiang= new JLabel("");// 用于显示当前玩家打出来的牌
	/**
	 * 用于显示当前玩家打出来的牌
	 */
	JLabel nowMajiang1=new JLabel("");//
	/**
	 * 用于显示当前玩家打出来的牌
	 */
	JLabel nowMajiang2=new JLabel("");//
	/**
	 * 用于显示当前玩家打出来的牌
	 */
	JLabel nowMajiang3=new JLabel("");//
	JLabel no =new JLabel("");
	JLabel winned =new JLabel("");
	/**
	 *  用于判断是否是用户可以操作
	 */
	boolean op=false;// 用于判断是否是用户可以操作
	boolean op2=false;
	/**
	 * 构造6为玩家，实际玩家只有两位，后两位用作容器来判断其他玩家牌的特征
	 */
	Player competitor[]=new Player[6];// 构造6为玩家，实际玩家只有两位，后两位用作容器来判断其他玩家牌的特征
	JPanel jp=new JPanel();// 窗口
	JPanel win=new JPanel();
	/**
	 * 创建14个人按钮，用于显示用户的牌
	 */
	JButton jb[]= new JButton[14];// 创建14个人按钮，用于显示用户的牌
	/**
	 * 碰
	 */
	JButton jbTouch = new JButton("碰");
	/**
	 * 杠
	 */
	JButton jbKong= new JButton("杠");
	/**
	 * 胡
	 */
	JButton jbWin = new JButton("胡");
	/**
	 * 过
	 */
	JButton jbPass= new JButton("过");
	/**
	 * 自摸
	 */
	JButton jbWin2=new JButton("自摸");
	/**
	 * 确定
	 */
	JButton sure= new JButton("确定");
	/**
	 * 闯将14个书签，用于显示用户碰或者杠的牌
	 */
	JLabel jl[]= new JLabel[14];// 闯将14个书签，用于显示用户碰或者杠的牌
	ImageIcon img1= new ImageIcon("picture/diaonao1.jpg");
	ImageIcon img2= new ImageIcon("picture\\diaonao2.jpg");
	ImageIcon img3= new ImageIcon("picture\\diaonao3.jpg");
	JLabel jlCom1= new JLabel(img1);
	JLabel jlCom2= new JLabel(img2);
	JLabel jlCom3= new JLabel(img3);
	CardLayout cl=new CardLayout();// 使用空白布局
	every lit= new every();
	/**
	 * 初始化
	 */
	public void initialize()// 初始化
	{
		for(int i=0; i<=108; i++)  // 初始化109张牌 
			mj[i]=new every();
		for(int i=0; i<=5; i++) // 初始化6位玩家
			competitor[i]= new Player();
		for(int i=1; i<=108; i++)// 将这109 张牌与其数值，花色和图片对应
		{
			mj[i].setNumber(i%9+1);
			if(i<=36)
				mj[i].setColor(1);
			else if(i>=37&&i<=72)
				mj[i].setColor(2);
			else
				mj[i].setColor(3);
			mj[i].setIf_Konged(false);
			mj[i].setIf_Touched(false);
			String a=new  String("picture/");
			a=a+(i%9+1);
			if(mj[i].getColor()==1)
				a=a+"tong.jpg";
			else if(mj[i].getColor()==2)
				a=a+"tiao.jpg";
			else
				a=a+"wan.jpg";
			mj[i].setImage(a);		
		}
	}
	/**
	 * 洗牌
	 */
	public void riffle()// 洗牌
	{
		for( int i=1; i<109; i++)
	     {
	          int j=((int)(Math.random()*10000))%(109-i)+i;
	          every temp;
	          temp=mj[i];
	          mj[i]=mj[j];
	          mj[j]=temp;
	     }
	}
	/**
	 * 发牌
	 */
	public void deal()// 发牌
	{
		for(int i=1; i<=13; i++)
	    {
	        competitor[0].hold[i]=mj[i];
	        competitor[1].hold[i]=mj[i+13];
	        competitor[2].hold[i]=mj[i+26];
	        competitor[3].hold[i]=mj[i+39];
	    }
	    for(int i=0; i<4; i++)
	        for(int j=14; j<=18; j++)
	        competitor[i].hold[j].setColor(1000);// 空闲牌先设为空白牌
	}
	/**
	 * 摸牌
	 * @return
	 */
	public every getMj()// 摸牌
	{
		if(currentmj==109)
			peace();// 最后一张牌，和局
		int i=currentmj;
		currentmj++;
		return mj[i];
	}
	/**
	 * 排序玩家手里的牌
	 * @param a
	 * @param i
	 */
	public void sort_mj(Player[] a, int i) // 排序玩家手里的牌
	{
		 	every  temp;
		 	int wage[]=new int[19];
		 	for(int j=1; j<=18; j++)
		 	{
		 		if(a[i].hold[j].getIf_Konged() == true || a[i].hold[j].getIf_Touched() == true)
		 		{
		 			wage[j]=1;
		 		}
		 		else
		 			wage[j]=0;
		 	}
		    for( int j=18;j>=2;j--)
		    {
		         for( int k=1; k<j; k++)
		         {
		              temp=a[i].hold[k];
		              int x=wage[k];
		              if(a[i].hold[k+1].getColor()*10 + wage[k+1]*100 + a[i].hold[k+1].getNumber() 
		            		  < wage[k]*100 + a[i].hold[k].getColor()*10 + a[i].hold[k].getNumber())
		              {
		                  a[i].hold[k]=a[i].hold[k+1];
		                  a[i].hold[k+1]=temp;
		                  wage[k]=wage[k+1];
		                  wage[k+1]=x;
		              }
		         }
		    }
	}
	public void welcome()
	{
		
	}
	
	public boolean If_double7(Player[]a, int c)// 判断是否为龙七对
	{
		for(int i=1; i<=14;i=i+2)
		{
			if(a[c].hold[i].getIf_Konged()==true||a[c].hold[i].getIf_Touched()==true)
				return false;
			if(a[c].hold[i].getColor()!=a[c].hold[i+1].getColor()||a[c].hold[i].getNumber()!=a[c].hold[i+1].getNumber())
				return false;
			
		}
		return true;
	}
	/**
	 * 判断玩家c是否可以胡牌
	 * @param a
	 * @param c
	 * @param b
	 * @return
	 */
	public boolean If_Win(Player[]a, int c, int[] b)//判断玩家c是否可以胡牌
	{
		if(If_double7(a,c)==true)
			return true;
		boolean flag=true;
		sort_mj(a,c);
	    int i=1,j=1;
	    int co=a[c].hold[1].getColor();
	    int If_jiang=0;
	    for(int j1=1;j1<=18;j1++)
	    {
	    	if(a[c].hold[j1].getIf_Konged()==true||a[c].hold[j1].getIf_Touched()==true)
	    		b[j1]=1;
	    }
	    while(i<19&&a[c].hold[i].getColor()<4&&a[c].hold[i].getIf_Konged()==false&&a[c].hold[i].getIf_Touched()==false)
	    {
	        if(a[c].hold[i].getColor()==co)
	            i++;
	        else
	        {
	            co=a[c].hold[i].getColor();
	            if(ok(j,i-1,a,c,b)==0&&jiang(j,i-1,a,c,b)==0)
	                flag=false;
	            else if(jiang(j,i-1,a,c,b)==1)
	                if(If_jiang==0)
	                    If_jiang=1;
	                else
	                    flag=false;
	            j=i;
	        }
	    }
	    if(ok(j,i-1,a,c,b)==1&&If_jiang==1&&flag==true)
	        flag= true;
	    else if(If_jiang==0&&jiang(j,i-1,a,c,b)==1&&flag==true)
	        flag= true;
	    else 
	    	flag=false;
	    return flag;
	}
	public int ok(int i, int j,Player[]a, int c,int[] b)
	{
		int x=1;
	    for(int k=i;k<=j;k++)
	        b[k]=0;
	    for(int k=i; k<=j; k++)
	    {
	    	if(a[c].hold[k].getIf_Konged()==true||a[c].hold[k].getIf_Touched()==true)
	    		b[k]=1;
	        if(b[k]==1) ;
	        else
	        {
	            if(k+2<=j&&a[c].hold[k+1].getNumber()==a[c].hold[k].getNumber()&&a[c].hold[k+2].getNumber()==a[c].hold[k].getNumber())
	            {
	                b[k]=b[k+1]=b[k+2]=1;
	            }
	            else
	            {
	                int a1=-1;
	                for(int p=k+1;p<=j;p++)
	                {
	                    if(a[c].hold[p].getNumber()==a[c].hold[k].getNumber()+1&&b[p]==0)
	                        a1=p;
	                    if(a[c].hold[p].getNumber()==a[c].hold[k].getNumber()+2&&b[p]==0&&a1!=-1)
	                    {
	                            b[k]=1;
	                            b[a1]=1;
	                            b[p]=1;
	                            a1=-2;
	                            break;
	                    }
	                }
	                if(a1!=-2)
	                    x=0;
	            }
	        }
	        if(b[k]!=1)
	            x=0;
	    }
	    return x;
	}
	public int jiang(int i, int j,Player[]a, int c,int[] b)
	{
	    for(int k=i; k<=j;k++)
	    {
	        if(a[c].hold[k].getNumber()==a[c].hold[k+1].getNumber())
	        {
	            if(ok(i,k-1,a,c,b)==1&&ok(k+2,j,a,c,b)==1)
	                return 1;
	        }
	    }
	    return 0;
	}
	public boolean If_Touch(every e,Player[]a,int c)// 判断是否可以碰牌
	{
	    for(int i=1;i<=17;i++)
	    {
	        if(a[c].hold[i].getColor()==e.getColor()&&a[c].hold[i].getNumber()==e.getNumber()&&a[c].hold[i+1].getColor()==e.getColor()&&a[c].hold[i+1].getNumber()==e.getNumber())
	            if(a[c].hold[i].getIf_Touched()==false&&a[c].hold[i+1].getIf_Touched()==false)
	            {
	            	
	                return true;
	            }
	    }
	    return false;
	}
	public int If_Kong(every e, Player[]a, int c)// 判断是否可以杠牌
	{
	    for(int i=1;i<=16;i++)
	    {
	        if(a[c].hold[i].getColor()==e.getColor()&&a[c].hold[i].getNumber()==e.getNumber()&&a[c].hold[i+1].getColor()==e.getColor()&&a[c].hold[i+1].getNumber()==e.getNumber()&&a[c].hold[i+2].getColor()==e.getColor()&&a[c].hold[i+2].getNumber()==e.getNumber())
	        {
	            if(a[c].hold[i].getIf_Touched()==true)
	                return -1;// gua feng
	            else
	                return 1; // xia yu
	        }
	    }
	    return -1;
	}
	/**
	 * 判断玩家能否暗杠，能暗杠返回杠的牌的索引
	 * @param c
	 * @return
	 */
	public int If_Kong2(Player c)
	{ 
		int sum;
	    for(int i = 1; i <= 15;i++)
	    {
	    	sum=0;
	    	if(c.hold[i].getIf_Konged() == false)
	    	{
	    		for(int j = 1; j < 4; j++)
	    		{
	    			//判断连续两个牌是否花色和数字一样
	    			if(c.hold[i].getColor() == c.hold[i+j].getColor() && c.hold[i].getNumber()==c.hold[i+j].getNumber() && c.hold[i].getColor() < 4) {
	    				sum++;
	    			}
	    		}
	    		//如果sum==3，则是可以杠
	    		if(sum == 3) {
	    			return i;
	    		}
	    	}
	    }
	    return -1;
	}
	public void piGetWin(int c,int a)// 电脑玩家赢了
	{
		if(a==c)
		winned.setText("玩家"+c+"赢了,他自摸");
		else if (a!=0)
			winned.setText("玩家"+c+"赢了,玩家"+(a+1)+"放炮");
		else
			winned.setText("玩家"+c+"赢了,您放炮");
		win.add(winned);
		win.setVisible(true);
		this.add(win);
		this.setBounds(300,300,300,300);
		jp.setVisible(false);
	}
	
	public void peace()
	{
		winned.setText("和局");
		win.add(winned);
		win.setVisible(true);
		this.add(win);
		this.setBounds(300,300,300,300);
		jp.setVisible(false);
	}
	public void playerGetWin(int a)// 玩家赢了
	{
		if(a==0)
		winned.setText("You winned! 您自摸");
		else
			winned.setText("You winned! 玩家"+(a+1)+"放炮");
		win.add(winned);
		win.setVisible(true);
		this.add(win);
		this.setBounds(300,300,300,300);
		jp.setVisible(false);
	}
	
	/**
	 * 玩家摸牌后在操作
	 * @param a
	 * @param c
	 * @param flag
	 */
	public void playerGetPlay(Player[]a, int c,boolean flag)// 玩家摸牌后在操作
	{
		op=flag;
		for(int i=0; i < 14; i++)
		{
			jb[i].setVisible(false);
			jl[i].setVisible(false);
		}
		int njb = 0;
		int njl = 0;
		for(int i = 1; i <= 18; i++)
		{
			
			 if((a[c].hold[i].getIf_Touched() == true || a[c].hold[i].getIf_Konged() == true) && a[c].hold[i].getColor() < 4)
			{
				jl[njl].setIcon(competitor[0].hold[i].getImg());
				jl[njl++].setVisible(true);
				this.show(true);
			}
			else if(a[c].hold[i].getColor() < 4)
			{
				jb[njb].setIcon(competitor[0].hold[i].getImg());
				jb[njb++].setVisible(true);
				this.show(true);
					
			}
		}
		int b[] = new int[19];
		boolean flag1=If_Win(competitor,currentPlayer,b);
		if(flag1==true&&op2==false)
		{
			jbWin2.setVisible(true);
			jbPass.setVisible(true);
			op2=true;
			this.show(true);
			return ;
		}
		
		if(If_Kong2(competitor[0])!=-1&&op2==false)
		{
				jbKong.setVisible(true);
				jbPass.setVisible(true);
				op2=true;
				this.show(true);
				return ;
		}
		op2=false;
	}
	/**
	 * 电脑摸牌后的操作
	 * @param a
	 * @param c
	 */
	public void piGetPlay(Player[]a, int c)// 电脑摸牌后的操作
	{
		sort_mj(a,c);
		int[] b= new int[19];
		if(If_Win(a,c,b)==true)
			piGetWin(c,c);
		else
		{
			for(int i=1;i<=18;i++)
				System.out.print(b[i]+" ");
			System.out.println();
			int kong= If_Kong2(a[c]);
			if(kong!=-1)// 暗杠
			{
				for(int i=0; i<=3; i++)
					a[c].hold[kong+i].setIf_Konged(true);
				a[c].hold[18]=getMj();
			    piGetPlay(a,c);
			    return;
			}
			for(int i=1; i<=18; i++)
			{
				if(b[i]==0&&i<=18&&a[c].hold[i].getColor()<=3)
				{
					if(If_same(a[c].hold[i],a[c].hold[i+1]))
					{
						b[i]--;
						if(b[i+1]==0)
							b[i+1]--;
					}
					if(a[c].hold[i].getNumber()+1==a[c].hold[i+1].getNumber()&&a[c].hold[i].getColor()==a[c].hold[i+1].getColor())
					{
						b[i]--;
						if(b[i+1]==0)
							b[i+1]--;
					}
					else if(a[c].hold[i].getNumber()+2==a[c].hold[i+1].getNumber()&&a[c].hold[i].getColor()==a[c].hold[i+1].getColor())
					{
						b[i]--;
						if(b[i+1]==0)
							b[i+1]--;
					}
				}
			}
			int temp=0,sum=-55;
			for(int i=1; i<=18; i++)
			{
				if(b[i]==0&&a[c].hold[i].getColor()<=3)
				{
					temp=i;
					break;
				}
				if(b[i]==1);
				else
				{
					if(b[i]>sum&&a[c].hold[i].getColor()<=3)
					{
						sum=b[i];
						temp=i;
					}
				}
			}
			nowMj=a[c].hold[temp];
			a[c].hold[temp]=lit;
			sort_mj(a,c);
			no.setIcon(nowMj.getImg());
			no.setVisible(true);
			if(c==1)
			{
				nowMajiang1.setIcon(nowMj.getImg());
				nowMajiang1.setVisible(true);
				//nowMajiang2.setVisible(false);
				//nowMajiang3.setVisible(false);
			}
			else if(c==2)
			{
				nowMajiang2.setIcon(nowMj.getImg());
				nowMajiang2.setVisible(true);
				//nowMajiang1.setVisible(false);
				//nowMajiang3.setVisible(false);
			}
			else if(c==3)
			{
				nowMajiang3.setIcon(nowMj.getImg());
				nowMajiang3.setVisible(true);
				//nowMajiang2.setVisible(false);
				//nowMajiang1.setVisible(false);
			}
			
		}
	}
	/**
	 * 判断两个牌是否相同
	 * @param e1
	 * @param e2
	 * @return
	 */
	public boolean If_same(every e1,every e2)
	{
		if(e1.getNumber() == e2.getNumber() && e1.getColor() == e2.getColor())
			return true;
		return false;
	}
	
	/**
	 * 开始游戏
	 */
	public void game()
	{
		initialize();
		riffle();
		deal();
		playerGetPlay(competitor,0,false);
		currentmj=53;
		currentPlayer=((int)(Math.random()*100))%4;
		for(int i=0; i<4;i++) {
			sort_mj(competitor,i);
		}
		gaming();
	}
	
	/**
	 * 游戏进行
	 */
	public void gaming()
	{
		if(nowMj == null)
		{
			competitor[currentPlayer].hold[18] = getMj();
			sort_mj(competitor,currentPlayer);
			show1();
			System.out.println();
			if(currentPlayer == 0)
			{
				playerGetPlay(competitor,0,true);
				return ;
			}
			else
			{
				piGetPlay(competitor,currentPlayer);
			}
			
		}
		if(nowMj != null && nowMj.getColor() < 4)
		{
			int addr;
			if((addr = If_GetWin(competitor,(currentPlayer+1)%4,nowMj)) != -1)
			{
				if(addr == 0)
				{
					jbWin.setVisible(true);
					jbPass.setVisible(true);
					this.show(true);
					return ;
				}
				else
				{
					for(int i1=1; i1 <= 18; i1++)
						competitor[addr].hold[i1]=competitor[5].hold[i1];
					piGetWin(addr,currentPlayer);
					return ;
				}
			}
			else if((addr = If_GetKong(competitor,(currentPlayer+1)%4, nowMj)) != -1)
			{
				if(addr==0)
				{
					jbKong.setVisible(true);
					jbTouch.setVisible(true);
					jbPass.setVisible(true);
					this.show(true);
						return ;
				}
				else
				{
					competitor[addr].hold[18]=nowMj;
					sort_mj(competitor,addr);
					int i=If_Kong2(competitor[addr]);
					for(int j=0; j<=3;j++)
						competitor[addr].hold[i+j].setIf_Konged(true);
					currentPlayer=addr;
					nowMj=null;
					gaming();
					return ;
				}
			}
			else if((addr=If_GetTouched(competitor,(currentPlayer+1)%4,nowMj))!=-1)
			{
				if(addr==0)
				{
					jbTouch.setVisible(true);
					jbPass.setVisible(true);
					this.show(true);
					return ;
				}
				else
				{
					int sum1=0;
					int sum2=0;
					int b[]= new int [19];
					sort_mj(competitor,addr);
					If_Win(competitor, addr, b);
					for(int i=1; i<=18; i++)
					{
						if(b[i]==1)
							sum1++;
					}
					for(int i1=1;i1<=18;i1++)
						competitor[5].hold[i1]=competitor[addr].hold[i1];
					competitor[5].hold[17]=nowMj;
					competitor[5].hold[18]=nowMj;
					sort_mj(competitor,5);
					int i=If_Kong2(competitor[5]);
					If_Win(competitor, 5, b);
					for(int ii=1; ii<=18; ii++)
					{
						if(b[ii]==1)
							sum2++;
					}
					if(sum1>=sum2);
					else
					{
						competitor[addr].hold[18]=nowMj;
						sort_mj(competitor,addr);
						for(int j=0; j<=2;j++)
							competitor[addr].hold[i+j].setIf_Touched(true);
						currentPlayer=addr;
						piGetPlay(competitor,addr);
						gaming();
						return ;
					}				
				}
			} 
			nowMj=null;
			currentPlayer=(currentPlayer+1)%4;
			gaming();		
		}
	}
	
	public int If_GetWin(Player[] a, int c, every nowMj)
	{
		int j=0;
		while(j <= 2) {
			for(int i = 1; i <= 18; i++)
			{
				a[5].hold[i] = a[c].hold[i];
			}
			a[5].hold[18] = nowMj;
			int b[] = new int [19];
			if(If_Win(a,5,b)) {
				return c;
			}
			c = (c+1)%4;
			j++;
		}
		return -1;
	}
	/**
	 * 对打出的牌进行判断是否可以杠，可以杠则返回杠牌的索引
	 * @param a
	 * @param c
	 * @param nowMj
	 * @return
	 */
	public int If_GetKong(Player[] a, int c, every nowMj)
	{
		int j = 0;
		int x;
		while(j <= 2)
		{
			for(int i = 1; i <= 18; i++)
			{
				a[5].hold[i] = a[c].hold[i];
			}
			if((x = If_Kong(nowMj,a,5)) != -1)
			{
				if(x == 2)
					nowMj.setIf_Touched(true);
				return c;
			}
			c=(c+1)%4;
			j++;
		}
		return -1;
	}
	/**
	 * 对打出的牌进行判断是否可以碰
	 * @param a
	 * @param c
	 * @param nowMj
	 * @return
	 */
	int If_GetTouched(Player[] a, int c, every nowMj)
	{
		int j=0;
		while(j<=2){
			for(int i=1;i<=18;i++)
				a[5].hold[i]=a[c].hold[i];
			if(If_Touch(nowMj,a,5))
				return c;
			c=(c+1)%4;
			j++;
		}
		return -1;
	}
	public ConsolePP()
	{
		this.setTitle("麻将");
		jp.setLayout(null);
		win.setLayout(null);
		win.setVisible(false);
		win.add(winned);
		winned.setBounds(20,20,200,200);
		for(int i=1; i<=14;i++)
		{
			jb[i-1]=new JButton("");
			jl[i-1]=new JLabel("");
			jl[i-1].setBounds(32+60*i,400, 60, 70);
			jb[i-1].setBounds(32+60*i,500, 60, 70);
			jb[i-1].addActionListener(this);
			jb[i-1].setVisible(true);
			jl[i-1].setVisible(true);
			jp.add(jb[i-1]);
			jp.add(jl[i-1]);
		}
		
		sure.setBounds(900, 420, 70, 40);
		sure.addActionListener(this);
		jp.add(sure);
		this.add(jp);
		//this.add(win);
		this.setBounds(40,40,1000,650);
		jbTouch.setBounds(300, 300, 50, 40);
		jbKong.setBounds(350, 300, 50, 40);
		jbWin.setBounds(400, 300, 50, 40);
		jbWin2.setBounds(400, 300, 80, 40);
		jbPass.setBounds(550,300,50,40);
		nowMajiang.setBounds(800,420,60, 70);
		nowMajiang1.setBounds(800,50,60,70);
		nowMajiang2.setBounds(550,70,60,70);
		nowMajiang3.setBounds(100,50,60,70);
		no.setBounds(500,200,60,70);
		jp.add(nowMajiang);
		jp.add(nowMajiang1);
		jp.add(nowMajiang2);
		jp.add(nowMajiang3);
		jp.add(no);
		nowMajiang.setVisible(false);
		nowMajiang1.setVisible(false);
		nowMajiang2.setVisible(false);
		nowMajiang3.setVisible(false);
		no.setVisible(false);
		jlCom1.setBounds(890, 100, 20,200);
		jlCom2.setBounds(360, 20, 200,20);
		jlCom3.setBounds(40, 100, 20,200);
		jp.add(jlCom3);
		jbTouch.setVisible(false);
		jbKong.setVisible(false);
		jbWin.setVisible(false);
		jbWin2.setVisible(false);
		jbPass.setVisible(false);
		jbTouch.addActionListener(this);
		jbKong.addActionListener(this);
		jbPass.addActionListener(this);
		jbWin.addActionListener(this);
		jbWin2.addActionListener(this);
		jp.add(jbTouch);
		jp.add(jbKong);
		jp.add(jbWin);
		jp.add(jbWin2);
		jp.add(jbPass);
		jp.add(jlCom1);
		jp.add(jlCom2);
		game();
		this.setVisible(true);
		
	}
	/**
	 * 显示玩家手上的牌
	 */
	public void show1()
	{
		for(int i=1; i<=3;i++)
		{
			for(int j=1; j<=18;j++)
			{
				if(competitor[i].hold[j].getColor()==1)
					System.out.print(competitor[i].hold[j].getNumber()+"筒  ");
				else if(competitor[i].hold[j].getColor()==2)
					System.out.print(competitor[i].hold[j].getNumber()+"条  ");
				else if(competitor[i].hold[j].getColor()==3)
					System.out.print(competitor[i].hold[j].getNumber()+"万 ");
			}
			System.out.println();
		}
		return ;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jbTouch)
		{
			jbTouch.setVisible(false);
			jbPass.setVisible(false);
			this.show(true);
			for(int i1=1;i1<=18;i1++)
				competitor[5].hold[i1]=competitor[0].hold[i1];
			competitor[5].hold[17]=nowMj;
			competitor[5].hold[18]=nowMj;
			sort_mj(competitor,5);
			int i=If_Kong2(competitor[5]);
			competitor[0].hold[18]=nowMj;
			sort_mj(competitor,0);
			for(int j=0; j<=2;j++)
					competitor[0].hold[i+j].setIf_Touched(true);
			currentPlayer=0;
			sort_mj(competitor,0);
			playerGetPlay(competitor,0,true);
		}
		
		if(e.getSource()==jbPass)
		{
			jbPass.setVisible(false);
			jbTouch.setVisible(false);
			jbKong.setVisible(false);
			jbWin.setVisible(false);
			jbWin2.setVisible(false);
			this.show(true);
			if(op2==true)
			{
				playerGetPlay(competitor,0,true);
			}
			else
			{
				nowMj=null;
				gaming();
			}	
		}
		if(e.getSource()==jbKong)
		{
			jbKong.setVisible(false);
			jbTouch.setVisible(false);
			jbPass.setVisible(false);
			this.show(true);
			for(int i1=1;i1<=18;i1++)
				competitor[5].hold[i1]=competitor[0].hold[i1];
			if(nowMj!=null)
			competitor[5].hold[18]=nowMj;
			sort_mj(competitor,5);
			int i=If_Kong2(competitor[5]);
			competitor[0].hold[18]=nowMj;
			sort_mj(competitor,0);
			for(int j=0; j<=3;j++)
					competitor[0].hold[i+j].setIf_Konged(true);
			currentPlayer=0;
			nowMj=null;
			sort_mj(competitor,0);
			gaming();
		}
		if(e.getSource()==jbWin)
		{
			jbPass.setVisible(false);
			jbTouch.setVisible(false);
			jbKong.setVisible(false);
			jbWin.setVisible(false);
			jbWin2.setVisible(false);
			playerGetWin(currentPlayer);
		}
		if(e.getSource()==jbWin2)
		{
			jbPass.setVisible(false);
			jbTouch.setVisible(false);
			jbKong.setVisible(false);
			jbWin.setVisible(false);
			jbWin2.setVisible(false);
			playerGetWin(0);
		}
		if(op==true&&e.getSource()==sure&&jbDown!=-1)
		{
			nowMj=(competitor[0].hold[jbDown+1]);
			competitor[0].hold[jbDown+1]=lit;
			jb[jbDown].setBounds(32+60*(jbDown+1),500, 60, 70);
			nowMajiang.setIcon(nowMj.getImg());
			nowMajiang.setVisible(true);
			no.setIcon(nowMj.getImg());
			no.setVisible(true);
			sort_mj(competitor,0);
			playerGetPlay(competitor,0,false);
			op=false;
			gaming();
		}
		
		for(int i=0; i<14;i++)
		{
			if(jb[i]!=null&&e.getSource()==jb[i])
			{	
				if(jbDown!=-1)
					jb[jbDown].setBounds(32+60*(jbDown+1),500, 60, 70);
				jb[i].setBounds(32+60*(i+1),490, 60, 70);
				jbDown=i;                                                                                                                                                                                                                                                                                                            
			}
		}
		this.show(true);
	}
	public static void main(String[] args) {
		ConsolePP ss= new ConsolePP();
	}
}