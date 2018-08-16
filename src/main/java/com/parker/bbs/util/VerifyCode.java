package com.parker.bbs.util;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;


public class VerifyCode implements Serializable {
    private int width,height,num;
    private Font font;
    private String code;
    private final String charOfCode="ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private byte[] imageByteArray;
    public VerifyCode(int width,int height,int num)
    {
        this.width=width;
        this.height=height;
        this.num=num;
        this.font=new Font("微软雅黑", Font.PLAIN, 16);
        change();
    }

    public VerifyCode()
    {
        this(100, 30, 5);
    }

    public void change()
    {
        code="";
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
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
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imageOutputStream = null;
            imageOutputStream = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image,"jpg",imageOutputStream);
            imageByteArray = bs.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getCode()
    {
        return code;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

}
