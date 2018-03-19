package com.example.pqanh.myapp2.custom;

/**
 * Created by pqanh on 15-03-18.
 */

public class Item {
    private String urlImg;
    private String page;

    @Override
    public String toString() {
        return "Item{" +
                "urlImg='" + urlImg + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
    public Item(String urlImg, String page) {
        this.urlImg = urlImg;
        this.page = page;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
