package com.example.mvstudio;

public class Movies {
    String name;
    String category;
    String detail;
    private String pushId;
    public Movies(){}
    public Movies(String name, String category, String detail) {
        this.name = name;
        this.category = category;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDetail() {
        return detail;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
