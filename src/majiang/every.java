package majiang;
import javax.swing.ImageIcon;
public class every {
	private int number;// 麻将的数值
    private int color;// 麻将的花色，1 代表筒，2 代表条， 3 代表 万
    private boolean If_Touched;
    private boolean If_Konged;
    private ImageIcon img;
    every(int n, int c, boolean If_Touched, boolean If_Konged)
    {
    	setNumber(n);
    	setColor(c);
    	setIf_Touched(If_Touched);
    	setIf_Konged(If_Konged);
    }
    every()
    {
    	number=0;
    	color=1000;
    	If_Touched=false;
    	If_Konged=false;
    	img= new ImageIcon("picture\\1tiao.jpg");
    }
    every (every e)
    {
    	setNumber(e.getNumber());
    	setColor(e.getColor());
    	img=e.getImg();
    }
    public void setImage(String a)
    {
    	img=new ImageIcon(a);
    }
    public ImageIcon getImg(){ return img;}
    public void setNumber(int n){number=n;}
    public  void setColor(int a) { color=a;}
    /**
     * 设置碰
     * @param a
     */
    public void setIf_Touched(boolean a) {If_Touched=a;}
    /**
     * 设置杠
     * @param a
     */
    public void setIf_Konged(boolean a) {If_Konged=a;}
    /**
     * 获取麻将数值
     * @return
     */
    public int getNumber() {return number;}
    /**
     * 获取麻将花色：1-筒；2-条；3-萬
     * @return
     */
    public int getColor() {return color;}
    /**
     * 碰
     * @return
     */
    public boolean getIf_Touched() {return If_Touched;}
    /**
     * 杠
     * @return
     */
    public boolean getIf_Konged() {return If_Konged;}
}
