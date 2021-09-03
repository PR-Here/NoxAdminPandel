package com.pankaj.adminnox.ModeActivity;

public class Result_Model {

    public String player;
    public String mode;
    public String date;
    public String entryfee;

    public Result_Model() {

    }
    public Result_Model(String player, String mode, String date, String entryfee) {
        this.player = player;
        this.mode = mode;
        this.date = date;
        this.entryfee = entryfee;
    }
    public void setPlayer(String player) {
        this.player = player;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEntryfee(String entryfee) {
        this.entryfee = entryfee;
    }





    public String getPlayer() {
        return player;
    }

    public String getMode() {
        return mode;
    }

    public String getDate() {
        return date;
    }

    public String getEntryfee() {
        return entryfee;
    }


}
