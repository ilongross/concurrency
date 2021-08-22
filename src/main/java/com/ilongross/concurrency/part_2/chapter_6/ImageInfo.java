package com.ilongross.concurrency.part_2.chapter_6;

import java.util.Date;

public class ImageInfo {

    private int size = 12345;
    private String rype = "JPEG";
    private Date date = new Date();
    private ImageData imageData;

    public ImageInfo() {
        this.imageData = new ImageData(size);
    }

    public ImageData downloadImage() {
        return imageData;
    }


    @Override
    public String toString() {
        return "ImageInfo{" +
                "size=" + size +
                ", rype='" + rype + '\'' +
                ", date=" + date +
                '}';
    }
}
