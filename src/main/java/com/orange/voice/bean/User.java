package com.orange.voice.bean;

public class User {

    private int userId;
    private String userName;
    private String passWord;
    private boolean isVerify;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }
}
