package com.example.demo.entry;

public class PrimaryStageEntry {
    public int id;
    public String step;
    public int time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "PrimaryStageEntry{" +
                "id=" + id +
                ", step='" + step + '\'' +
                ", time=" + time +
                '}';
    }
}
