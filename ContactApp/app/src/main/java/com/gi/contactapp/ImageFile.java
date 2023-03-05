package com.gi.contactapp;

public class ImageFile {
    String name;
    long filesize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    @Override
    public String toString() {
        return "ImageFile{" +
                "name='" + name + '\'' +
                ", filesize=" + filesize +
                '}';
    }

    public ImageFile(String name, long filesize) {
        this.name = name;
        this.filesize = filesize;
    }
}
