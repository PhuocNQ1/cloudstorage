package com.udacity.jwdnd.course1.cloudstorage.services.file_service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileException;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.repository.FileRepository;
import com.udacity.jwdnd.course1.cloudstorage.services.generic_service.T_Service;

@Service
public class FileService extends T_Service<FileModel> {

    @Autowired
    private FileRepository fileRepository;

    private String msgError;

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    /**
     * Check duplicate name
     * 
     * @param list
     * @param name
     * @return
     */
    public boolean isDuplicateName(List<FileModel> list, String name) {
        return list.stream().anyMatch(f -> f.getFileName().equals(name));
    }

    /**
     * Check valid file format
     * 
     * @param name
     * @return
     */
    public boolean isValidFileFormat(String name) {
        String allowedFormatsRegex = ".*\\.(pdf|jpg|jpeg|png)$";
        Pattern pattern = Pattern.compile(allowedFormatsRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    @Override
    public List<FileModel> getAll() {
        return fileRepository.getFileRepoTemp();
    }

    @Override
    public FileModel get(int id) {
        return fileRepository.findFile(id);
    }

    @Override
    public void add(FileModel t) throws FileException {
        String fileName = t.getFileName();

        if (isDuplicateName(getAll(), fileName)) {
            throw new FileException("File name is duplicate");
        }
        if (!isValidFileFormat(fileName)) {
            throw new FileException("Invalid File Format");
        }

        fileRepository.setFileRepoTemp(t);

    }

    @Override
    public void update(FileModel t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteByID(int id) {
        fileRepository.deleteFile(id);

    }

    @Override
    public void delete(FileModel t) {
        // TODO Auto-generated method stub

    }
}
