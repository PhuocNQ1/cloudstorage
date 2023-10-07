package com.udacity.jwdnd.course1.cloudstorage.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;

@Repository
public class FileRepository {

    private List<FileModel> fileRepoTemp = new ArrayList<>();

    public List<FileModel> getFileRepoTemp() {
        return fileRepoTemp;
    }

    public void setFileRepoTemp(FileModel fileRepoTemp) {
        this.fileRepoTemp.add(fileRepoTemp);
    }

    public void deleteFile(int id) {
        this.fileRepoTemp.removeIf(t -> t.getFileId() == id);
    }
    
    public FileModel findFile(int id) {
        FileModel f = fileRepoTemp.get(id);
        return f;
    }

}
