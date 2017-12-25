package org.trams.hello.bean.web.counselor;

/**
 * Created by bryanlee on 4/11/17.
 */
public class CounselingSessionDto {

    private String  key;
    private int     time;
    private int     money;
    private int     reservation;
    private int     counseled;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getCounseled() {
        return counseled;
    }

    public void setCounseled(int counseled) {
        this.counseled = counseled;
    }
}
