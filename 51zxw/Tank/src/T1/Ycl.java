package T1;
import java.util.*;
import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

class Tank
{
	int x=0, y=0;
	int fangxiang=0;
	int sudu=5;
	int color;
	boolean shengming=true;
		
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSudu() {
		return sudu;
	}
	public void setSudu(int sudu) {
		this.sudu = sudu;
	}
	public int getFangxiang() {
		return fangxiang;
	}
	public void setFangxiang(int fangxiang) {
		this.fangxiang = fangxiang;
	}	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}

class DiTank extends Tank implements Runnable
{
	int sudu=1;	
	int time=0;
	int fangxiang;
	Vector<Zidan> dzd=new Vector<Zidan>();	
	Vector<DiTank> dtk=new Vector<DiTank>();
	
	public DiTank(int x, int y)
	{
		super(x, y);
	}
	public void dtkxl(Vector<DiTank> dxl)
	{
		this.dtk=dxl;
	}
	
	public boolean huxiangpengzhuang()
	{
		boolean b=false;
		switch(this.fangxiang)
		{
		case 0:
			
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				
				if(dt!=this)
				{
					if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x>=dt.x && this.x<=dt.x+20 && this.y>=dt.y && this.y<=dt.y+30)
						{
							return true;
						}
						if(this.x+20>=dt.x && this.x+20<=dt.x+20 && this.y>=dt.y && this.y<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x>=dt.x && this.x<=dt.x+30 && this.y>=dt.y && this.y<=dt.y+20)
						{
							return true;
						}
						if(this.x+20>=dt.x && this.x+20<=dt.x+30 && this.y>=dt.y && this.y<=dt.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
				    if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x>=dt.x&&this.x<=dt.x+20&&this.y>=dt.y&&this.y<=dt.y+30)
						{
							return true;
						}
						//下一点
						if(this.x>=dt.x&&this.x<=dt.x+20&&this.y+20>=dt.y&&this.y+20<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x>=dt.x&&this.x<=dt.x+30&&this.y>=dt.y&&this.y<=dt.y+20)
						{
							return true;
						}
						if(this.x>=dt.x&&this.x<=dt.x+30&&this.y+20>=dt.y&&this.y+20<=dt.y+20)
						{
							return true;
						}
					}
				}
			}
			
			
			break;
		case 2:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
					if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x>=dt.x&&this.x<=dt.x+20&&this.y+30>=dt.y&&this.y+30<=dt.y+30)
						{
							return true;
						}
						if(this.x+20>=dt.x&&this.x+20<=dt.x+20&&this.y+30>=dt.y&&this.y+30<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x>=dt.x&&this.x<=dt.x+30&&this.y+30>=dt.y&&this.y+30<=dt.y+20)
						{
							return true;
						}
						
						if(this.x+20>=dt.x&&this.x+20<=dt.x+30&&this.y+30>=dt.y&&this.y+30<=dt.y+20)
						{
							return true;
						}
					}
				}
			}
			break;
		case 3:
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt!=this)
				{
					if(dt.fangxiang==0||dt.fangxiang==2)
					{
						if(this.x+30>=dt.x && this.x+30<=dt.x+20 && this.y>=dt.y && this.y<=dt.y+30)
						{
							return true;
						}
						//下点 
						if(this.x+30>=dt.x && this.x+30<=dt.x+20 && this.y+20>=dt.y && this.y+20<=dt.y+30)
						{
							return true;
						}
					}
					if(dt.fangxiang==3||dt.fangxiang==1)
					{
						if(this.x+30>=dt.x && this.x+30<=dt.x+30 && this.y>=dt.y && this.y<=dt.y+20)
						{
							return true;
						}
						if(this.x+30>=dt.x && this.x+30<=dt.x+30 && this.y+20>=dt.y && this.y+20<=dt.y+20)
						{
							return true;
						}
					}
				}
			}
			
			break;
		}		
		return b;
	}
	public void run() 
	{
		while(true)
		{
		   switch(this.fangxiang)
		   {
		    case 0:
		    	for(int i=0;i<30;i++)
		    	{
		    		if(y>0 && !huxiangpengzhuang())
		    			{y-=sudu;}	
		    		try {
		                Thread.sleep(50);
		            } catch (Exception e){}
		    	}		    	
				 break;
		    case 1:
		    	for(int i=0;i<30;i++)
		    	{
		    		if(x>0 && !huxiangpengzhuang())
		    			{x-=sudu;}	
		    		try {
		                Thread.sleep(50);
		            } catch (Exception e){}
		    	}			    	
		    	break;
		    case 2:
		    	for(int i=0;i<30;i++)
		    	{
		    		if(y<250 && !huxiangpengzhuang())
		    			{y+=sudu;}	
		    		try {
		                Thread.sleep(50);
		            } catch (Exception e){}
		    	}			    	
				 break;
		    case 3:
		    	for(int i=0;i<30;i++)
		    	{
		    		if(x<360 && !huxiangpengzhuang())
		    			{x+=sudu;}	
		    		try {
		                Thread.sleep(50);
		            } catch (Exception e){}
		    	}		    	
				 break;					 
	       }  
		   	this.fangxiang=(int)(Math.random()*4);
		   				
			if(this.shengming==false)
			{
				break;
			}
            this.time++;
			
			if(time%2==0)
			{
				if(shengming)
				{
					if(dzd.size()<5)
					{
						Zidan zd=null;
						
						switch(fangxiang)
						{
						case 0:
							zd=new Zidan(x+10,y,0);
							dzd.add(zd);
							break;
						case 1:
							zd=new Zidan(x,y+10,1);
							dzd.add(zd);
							break;
						case 2:
							zd=new Zidan(x+10,y+30,2);
							dzd.add(zd);
							break;
						case 3:
							zd=new Zidan(x+30,y+10,3);
							dzd.add(zd);
							break;
						}
						Thread t5=new Thread(zd);
						t5.start();
				   }
				}
			}
			
		} 
	}
}
class MyTank extends Tank
{
	Vector<Zidan>aa=new Vector<Zidan>();
	Zidan zd=null;
		
	public MyTank(int x,int y)
	{
		super(x,y);		
	}
	
	public void fszd()
	{
		switch(this.fangxiang)
		{
		case 0:
			zd=new Zidan(x+10,y,0);
			aa.add(zd);
			break;
		case 1:
			zd=new Zidan(x,y+10,1);
			aa.add(zd);
			break;
		case 2:
			zd=new Zidan(x+10,y+30,2);
			aa.add(zd);
			break;
		case 3:			
			zd=new Zidan(x+30,y+10,3);
			aa.add(zd);
			break;			
		}
		Thread t=new Thread(zd);
		t.start();
	}
	public void xiangshang()
	{
		y-=sudu;
	}
	public void xiangzuo()
	{
		x-=sudu;
	}
	public void xiangxia()
	{
		y+=sudu;
	}
	public void xiangyou()
	{
		x+=sudu;
	}
}

class Zidan implements Runnable 
{
	int x;
	int y;
	int fangxiang;
	int sudu=5;
	boolean shengming=true;
	
	public Zidan(int x,int y,int fangxiang)
	{
		this.x=x;
		this.y=y;
		this.fangxiang=fangxiang;
	}
	public void run() 
	{	
		while(true)
		{
			try	{
				Thread.sleep(50);}
			catch (Exception e) {}
				
			switch(fangxiang)
			{
			case 0:
				y-=sudu;
				break;
			case 1:
				x-=sudu;
				break;
			case 2:
				y+=sudu;
				break;
			case 3:
				x+=sudu;
				break;
			}
			if(x<0||x>400||y<0||y>300)
			{
				this.shengming=false;
				break;				
			}
		}
		
	}	
}
class Baozha 
{
	int x,y;
	int shengcunqi=9;
	boolean shengming=true;
	
    public Baozha(int x,int y)
	{
	    this.x=x;
	    this.y=y;
	}
    public void scqjd()
    {
	    if(shengcunqi>0)
	    {
	    	shengcunqi--;		   
    	}
	    else
	    {
	    	this.shengming=false;
	    }	    
    }
}
class Jilu
{
	private static int dtsl=10;
	private static int mtsl=2;
	private static int sdtj=0;
	private static FileWriter fw=null;
	private static BufferedWriter bw=null;
	private static FileReader fr=null;
	private static BufferedReader br=null;
	private static Vector<DiTank> dtk=new Vector<DiTank>();
	static Vector<Weizhi> wzjh=new Vector<Weizhi>();
	
	public Vector<DiTank> getDtk() {
		return dtk;
	}
	public void setDtk(Vector<DiTank> dtk) {
		this.dtk = dtk;
	}
	public static Vector<Weizhi> dupan()
	{
		try {
			fr=new FileReader("e:/TKDZ/cpjl.txt");
			br=new BufferedReader(fr);
			String s="";
			s=br.readLine();
			sdtj=Integer.parseInt(s);
			while((s=br.readLine())!=null)
			{
				String[] sz=s.split(" "); 
				Weizhi wz=new Weizhi(Integer.parseInt(sz[0]),Integer.parseInt(sz[1]),Integer.parseInt(sz[2]));
				wzjh.add(wz);
			}		
		  }catch (Exception e){}
          finally{			
			     try {
				      br.close();
				      fr.close();
			      }catch (Exception e){}
		    }
		return wzjh;
	}
	public static void bcjl()
	{
        try {
        	fw=new FileWriter("e:/TKDZ/cpjl.txt");
			bw=new BufferedWriter(fw);			
			bw.write(sdtj+"\r\n");
						
		}catch (Exception e){}
        finally{
			   try {
				     bw.close();
				     fw.close();
			       }catch (Exception e){}
		}
	}	
	public void cunpan()
	{
		try {
        	fw=new FileWriter("e:/TKDZ/cpjl.txt");
			bw=new BufferedWriter(fw);			
			bw.write(sdtj+"\r\n");
			for(int i=0;i<dtk.size();i++)
			{
				DiTank dt=dtk.get(i);
				if(dt.shengming)
				{
					String zb=dt.x+" "+dt.y+" "+dt.fangxiang;
					bw.write(zb+"\r\n");
				}
			}						
		}catch (Exception e){}
        finally{
			   try {
				     bw.close();
				     fw.close();
			       }catch (Exception e){}
		}
		
	}
	public static void dqjl()
	{
		try {
			fr=new FileReader("e:/TKDZ/cpjl.txt");
			br=new BufferedReader(fr);
			String s=br.readLine();
			sdtj=Integer.parseInt(s);
		} catch (Exception e){}
          finally{			
			     try {
				      br.close();
				      fr.close();
			      }catch (Exception e){}
		    }
	}
	public static int getSdtj() {
		return sdtj;
	}
	public static void setSdtj(int sdtj) {
		Jilu.sdtj = sdtj;
	}
	public static int getDtsl() {
		return dtsl;
	}
	public static void setDtsl(int dtsl) {
		Jilu.dtsl = dtsl;
	}
	public static int getMtsl() {
		return mtsl;
	}
	public static void setMtsl(int mtsl) {
		Jilu.mtsl = mtsl;
	}
	public static void dtjs()
	{
		dtsl--;
	}
	public static void sdsl()
	{
		sdtj++;
	}
}
class Weizhi{
	int x;
	int y;
	int fangxiang;
	public Weizhi(int x,int y,int fangxiang)
	{
		this.x=x;
		this.y=y;
		this.fangxiang=fangxiang;
	}
}
class Shengyin extends Thread {

	private String wjm;
	public Shengyin(String ypwj)
	{
		 wjm=ypwj;
	}

	public void run() {

		File wjl = new File(wjm);

		AudioInputStream ypsrl = null;
		try {
			ypsrl = AudioSystem.getAudioInputStream(wjl);
		} catch (Exception e){}

		AudioFormat format = ypsrl.getFormat();
		SourceDataLine aqsj = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			aqsj = (SourceDataLine) AudioSystem.getLine(info);
			aqsj.open(format);
		} catch (Exception e){}		
		aqsj.start();
		
		int zjtj = 0;
		byte[] hczj = new byte[1024];
		try {
			while (zjtj  != -1) {
				zjtj = ypsrl.read(hczj, 0, hczj.length);
				if (zjtj  >= 0)
					aqsj.write(hczj, 0, zjtj );
			}
		} catch (Exception e){}
		finally {
			aqsj.drain();//将残留部分处理干净
			aqsj.close();
		}
	}	
}