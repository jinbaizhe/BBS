package com.parker.bbs.util;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Pager {
    private int pageListSize=3;
    private int currentPage;
    private int showItemsPerPageNum;
    private int totalPageNum;
    private int totalItemNum;
    private int beginIndex;
    public Pager(int currentPage,int showItemsPerPageNum,int totalItemNum)
    {

        try {
            Properties properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("setting.properties");
            properties.load(inputStream);
            String temp = properties.getProperty("pageListSize");
            pageListSize = Integer.valueOf(temp);
        } catch (FileNotFoundException e) {
            System.out.println("找不到setting.properties文件，pageListSize变量使用默认值3");
        }catch (IOException e){
            System.out.println("加载setting.properties文件失败");
        }

        this.currentPage=currentPage;
        this.showItemsPerPageNum=showItemsPerPageNum;
        this.totalItemNum=totalItemNum;
        if(totalItemNum==0) {
            this.totalPageNum = 1;
        }
        else {
            this.totalPageNum = totalItemNum / showItemsPerPageNum
                    + (totalItemNum % showItemsPerPageNum == 0 ? 0 : 1);
        }
        beginIndex=(currentPage-1)*showItemsPerPageNum+1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getShowItemsPerPageNum() {
        return showItemsPerPageNum;
    }

    public void setShowItemsPerPageNum(int showItemsPerPageNum) {
        this.showItemsPerPageNum = showItemsPerPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getTotalItemNum() {
        return totalItemNum;
    }

    public void setTotalItemNum(int totalItemNum) {
        this.totalItemNum = totalItemNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public boolean isFirstPage()
    {
        if(currentPage==1)
            return true;
        return false;
    }

    public boolean isLastPage()
    {
        if(currentPage==totalPageNum)
            return true;
        return false;
    }

    public List<String> getPageList()
    {
        List<String> list=new ArrayList();
        int start_pos=1,end_pos=totalPageNum;
        if(totalPageNum > pageListSize)
        {
            start_pos = currentPage-pageListSize/2;
            end_pos = currentPage+pageListSize/2;
        }
        if(start_pos<1)
        {
            start_pos = 1;
        }
        if(end_pos>totalPageNum) {
            end_pos = totalPageNum;
        }
        if(start_pos>1)
        {
            list.add("...");
        }
        for(int i=start_pos;i<=end_pos;i++)
        {
            list.add(String.valueOf(i));
        }
        if(end_pos<totalPageNum) {
            list.add("...");
        }
        return list;
    }

}
