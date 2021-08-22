package com.ilongross.concurrency.part_2.chapter_6;


import lombok.Getter;

public class ImageData {

    @Getter
    private byte[] arrBytes;

    public ImageData(int size) {
        this.arrBytes = new byte[size];
    }
}
