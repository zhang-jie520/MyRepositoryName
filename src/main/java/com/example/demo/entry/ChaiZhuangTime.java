package com.example.demo.entry;


public class ChaiZhuangTime {
    private int id;
    private String zbname;
    private String ljname;
    private String chaitime;
    private String zhuangtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZbname() {
        return zbname;
    }

    public void setZbname(String zbname) {
        this.zbname = zbname;
    }

    public String getLjname() {
        return ljname;
    }

    public void setLjname(String ljname) {
        this.ljname = ljname;
    }

    public String getChaitime() {
        return chaitime;
    }

    public void setChaitime(String chaitime) {
        this.chaitime = chaitime;
    }

    public String getZhuangtime() {
        return zhuangtime;
    }

    public void setZhuangtime(String zhuangtime) {
        this.zhuangtime = zhuangtime;
    }

    @Override
    public String toString() {
        return "ChaiZhuangTime{" +
                "id=" + id +
                ", zbname='" + zbname + '\'' +
                ", ljname='" + ljname + '\'' +
                ", chaitime='" + chaitime + '\'' +
                ", zhuangtime='" + zhuangtime + '\'' +
                '}';
    }
}
