package com.dotv.perfume.untils;

import java.util.List;

public class PageCustom<T>{
    private int curPage;
    private int totalPage;
    private int typeFil;//Loại lọc
    List<T> data;

    public int getTypeFil() {
        return typeFil;
    }

    public void setTypeFil(int typeFil) {
        this.typeFil = typeFil;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
