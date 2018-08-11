package com.parker.bbs.util;

import java.util.ArrayList;
import java.util.List;

public class Pager {
    private int currentPage;
    private int showItemsPerPageNum;
    private int totalPageNum;
    private int totalItemNum;
    private int beginIndex;
    public Pager(int currentPage,int showItemsPerPageNum,int totalItemNum)
    {
        this.currentPage=currentPage;
        this.showItemsPerPageNum=showItemsPerPageNum;
        this.totalItemNum=totalItemNum;
        if(totalItemNum==0)
            this.totalPageNum=1;
        else
            this.totalPageNum=totalItemNum/showItemsPerPageNum
                    +(totalItemNum%showItemsPerPageNum==0?0:1);
        beginIndex=(currentPage-1)*showItemsPerPageNum+1;
    }

    public int getCurrentPage() {
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
/*
    public List getPageList()
    {
        //读取web.xml获取ShowFollowpostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowPageListSize=Integer.valueOf(servletContext.getInitParameter("ShowPageListSize"));

        List<String> list=new ArrayList();
        int start_pos=1,end_pos=totalPageNum;
        if(totalPageNum>ShowPageListSize)
        {
            start_pos=currentPage-ShowPageListSize/2;
            end_pos=currentPage+ShowPageListSize/2;
        }
        if(start_pos<1)
        {
            start_pos = 1;
        }
        if(end_pos>totalPageNum)
            end_pos=totalPageNum;
        if(start_pos>1)
        {
            list.add("...");
        }
        for(int i=start_pos;i<=end_pos;i++)
        {
            list.add(String.valueOf(i));
        }
        if(end_pos<totalPageNum)
            list.add("...");
        return list;
    }
    */
}
