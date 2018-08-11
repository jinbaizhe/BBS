package com.parker.bbs.util;

import java.awt.*;
import java.awt.image.BufferedImage;


public class VerifyCode {
    private int width,height,num;
    private Font font;
    private String code;
    private final String charOfCode="ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private BufferedImage image;
    public VerifyCode(int width,int height,int num)
    {
        this.width=width;
        this.height=height;
        this.num=num;
        this.font=new Font("微软雅黑", Font.PLAIN, 16);
        change();
    }

    public void change()
    {
        code="";
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(new Color(0,0,153));
        for(int i=1;i<=num;i++)
        {
            char c = (charOfCode.charAt((int)(Math.random()*charOfCode.length())));
            code+=c;
            int w=width/(num+2)*i;
            int h=(int)(height/2+height/3*Math.random());
            graphics.drawString(c+"",w,h);
        }
        graphics.dispose();
    }
    public String getCode()
    {
        return code;
    }
    public BufferedImage getImage()
    {
        return image;
    }
}
