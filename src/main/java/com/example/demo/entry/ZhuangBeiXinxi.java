package com.example.demo.entry;


public class ZhuangBeiXinxi {
    private int id;
    private String diyname;
    private int sumlingjian;
    private String shunxuhz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiyname() {
        return diyname;
    }

    public void setDiyname(String diyname) {
        this.diyname = diyname;
    }

    public int getSumlingjian() {
        return sumlingjian;
    }

    public void setSumlingjian(int sumlingjian) {
        this.sumlingjian = sumlingjian;
    }

    public String getShunxuhz() {
        return shunxuhz;
    }

    public void setShunxuhz(String shunxuhz) {
        this.shunxuhz = shunxuhz;
    }

    @Override
    public String toString() {
        return "ZhuangBeiXinxi{" +
                "id=" + id +
                ", diyname='" + diyname + '\'' +
                ", sumlingjian=" + sumlingjian +
                ", shunxuhz='" + shunxuhz + '\'' +
                '}';
    }
}
