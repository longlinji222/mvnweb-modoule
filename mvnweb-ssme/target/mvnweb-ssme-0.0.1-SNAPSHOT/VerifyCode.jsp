<%@ page language="java" import="java.util.*" pageEncoding="GB18030" contentType="image/jpeg" import="java.awt.*,
java.awt.image.*,java.util.*,javax.imageio.*" %>
<%!
Color getRandColor(int fc,int bc){//������Χ��������ɫ
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
        }
%>
<%
//����ҳ�治����
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

// ���ڴ��д���ͼ��
int width=80, height=30;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

// ��ȡͼ��������
Graphics g = image.getGraphics();

//���������
Random random = new Random();

// �趨����ɫ
g.setColor(getRandColor(200,250));
g.fillRect(0, 0, width, height);

//�趨����(��ʹ�����������ǿ����)

g.setFont(new Font("Love Letters",Font.BOLD,22));





// �������60�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
for (int i=0;i<60;i++)
{
	g.setColor(getRandColor(80,160));
    int x = random.nextInt(width);
    int y = random.nextInt(height);
    int xl = random.nextInt(18);
    int yl = random.nextInt(18);
    g.drawLine(x,y,x+xl,y+yl);
}



// ȡ�����������֤��(4λ����)
String sRand="";
for (int i=0;i<4;i++){
    String rand=String.valueOf(random.nextInt(10));
    sRand+=rand;
    // ����֤����ʾ��ͼ����
    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
    g.drawString(rand,14*i+6,21);
}

// ����֤�����SESSION
session.setAttribute("rand",sRand);

// ͼ����Ч
g.dispose();

// ���ͼ��ҳ��
ImageIO.write(image, "JPEG", response.getOutputStream());
// �������������out����
out.clear();
out = pageContext.pushBody();

%>