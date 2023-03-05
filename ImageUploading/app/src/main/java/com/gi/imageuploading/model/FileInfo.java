package com.gi.imageuploading.model;

public class FileInfo {
    String name;
    long filesize;

    public FileInfo(String name, long filesize) {
        this.name = name;
        this.filesize = filesize;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", filesize=" + filesize +
                '}';
    }

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
}
