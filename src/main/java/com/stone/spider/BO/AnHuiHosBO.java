package com.stone.spider.BO;

public class AnHuiHosBO {
    private String keShi;

    private String catogeryList;

    private String flag;

    private String expertList;


    public String getKeShi() {
        return keShi;
    }

    public void setKeShi(String keShi) {
        this.keShi = keShi;
    }

    public String getCatogeryList() {
        return catogeryList;
    }

    public void setCatogeryList(String catogeryList) {
        this.catogeryList = catogeryList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getExpertList() {
        return expertList;
    }

    public void setExpertList(String expertList) {
        this.expertList = expertList;
    }

    @Override
    public String toString() {
        return keShi + "," + catogeryList + "," + flag ;
    }
}
