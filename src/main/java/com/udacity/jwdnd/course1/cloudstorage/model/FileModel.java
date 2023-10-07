package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileModel {
    private int fileId;
    private String fileName;
    private String contenType;
    private String fileSize;

    public FileModel() {
    }

    public FileModel(int fileId, String fileName, String contenType, String fileSize) {
        super();
        this.fileId = fileId;
        this.fileName = fileName;
        this.contenType = contenType;
        this.fileSize = fileSize;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContenType() {
        return contenType;
    }

    public void setContenType(String contenType) {
        this.contenType = contenType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

}
