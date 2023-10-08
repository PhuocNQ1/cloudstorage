package com.udacity.jwdnd.course1.cloudstorage.services.file_service;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.services.generic_service.T_Service;
import com.udacity.jwdnd.course1.cloudstorage.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileService extends T_Service<FileModel> {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserService userService;

    private String msgError;

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
        String allowedFormatsRegex = ".*\\.(pdf|jpg|jpeg|png|zip)$";
        Pattern pattern = Pattern.compile(allowedFormatsRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    public void mapMultipartFile(MultipartFile file, FileModel fileModel) {
        if(file == null){
            throw new FileException("Error: File is null, Please try again!");
        }
        fileModel.setFileName(file.getOriginalFilename());
        fileModel.setContenType(file.getContentType());
        fileModel.setFileSize(Long.toString(file.getSize()));
    }

    @Override
    public List<FileModel> getAll() {
        return fileMapper.getAll();
    }

    @Override
    public FileModel get(int id) {
        return fileMapper.get(id);
    }

    @Override
    public void add(FileModel t) throws FileException {
        String fileName = t.getFileName();

        if (isDuplicateName(getAll(), fileName)) {
            throw new FileException("Error: File name is duplicate, Please try again!");
        }
        if (!isValidFileFormat(fileName)) {
            throw new FileException("Error: Invalid File Format, Please try again!");
        }
        t.setUserId(userService.getCurrentUserId());
        fileMapper.add(t);

    }

    @Override
    public void update(FileModel t) {
        fileMapper.update(t);
    }

    @Override
    public void deleteByID(int id) {
        fileMapper.deleteByID(id);
    }

    @Override
    public List<FileModel> getAllByUserId(int id) {
        return fileMapper.getAllByUserId(id);
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public FileMapper getFileMapper() {
        return fileMapper;
    }

    public String getResourceDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        return String.valueOf(Paths.get(currentDirectory, "src", "main", "resources", "image")).concat(File.separator);
    }
}
