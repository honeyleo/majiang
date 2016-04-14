package majiang;

public class Player {
	/**
	 * 玩家手上的牌
	 */
	 public every hold[]= new every[19];
	 private String name;
	 private int money;
	 private boolean If_Winned;
	 Player(String na, int m, boolean i)
	 {
		 setName(na);
		 setMoney(m);
		 setIf_winned(i);
	 }
	 Player()
	 {
		 name="jada";
		 money=0;
		 If_Winned=false;
		 for(int i=0; i<19; i++)
			 hold[i]=new every();
	 }
	 void setName(String a) {name=a;}
	 void setMoney(int a)   {money=a;}
	 void setIf_winned(boolean a) {If_Winned=a;}
	 int getMoney() {return money;}
	 String getName() {return name;}
	 boolean getIf_Winned() {return If_Winned;}
}
