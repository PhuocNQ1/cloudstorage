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

    private String fileNotification;

    /**
     * Checks if a given name already exists within a list of FileModel objects.
     *
     * @param list
     * @param name
     * @return
     */
    public boolean isDuplicateName(List<FileModel> list, String name) {
        return list.stream().anyMatch(f -> f.getFileName().equals(name));
    }

    /**
     * Checks if a given file name has a valid file format.
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

    /**
     * Maps the data from a MultipartFile object to a FileModel object.
     * @param file
     * @param fileModel
     */
    public void mapMultipartFile(MultipartFile file, FileModel fileModel) {
        if(file == null){
            throw new FileException("Error: File is null, Please try again!");
        }
        fileModel.setFileName(file.getOriginalFilename());
        fileModel.setContenType(file.getContentType());
        fileModel.setFileSize(Long.toString(file.getSize()));
    }

    /**
     * Retrieves a list of all file records from the database.
     * @return
     */
    @Override
    public List<FileModel> getAll() {
        return fileMapper.getAll();
    }

    /**
     * Retrieves a file record from the database based on the provided ID.
     * @param id
     * @return
     */
    @Override
    public FileModel get(int id) {
        return fileMapper.get(id);
    }

    /**
     * Adds a new file record to the database with the provided FileModel.
     * @param t
     * @throws FileException
     */
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

    /**
     * Updates a file record in the database with the provided FileModel.
     * @param t
     */
    @Override
    public void update(FileModel t) {
        fileMapper.update(t);
    }

    /**
     * Deletes a record from the database based on the provided ID.
     * @param id
     */
    @Override
    public void deleteByID(int id) {
        fileMapper.deleteByID(id);
    }

    /**
     * Retrieves a list of file records associated with a specific user ID from the database.
     * @param id
     * @return
     */
    @Override
    public List<FileModel> getAllByUserId(int id) {
        return fileMapper.getAllByUserId(id);
    }

    public String getFileNotification() {
        return fileNotification;
    }

    public void setFileNotification(String fileNotification) {
        this.fileNotification = fileNotification;
    }

    public FileMapper getFileMapper() {
        return fileMapper;
    }

    /**
     * Retrieves the resource directory path where files are stored.
     * @return
     */
    public String getResourceDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        return String.valueOf(Paths.get(currentDirectory, "src", "main", "resources", "image")).concat(File.separator);
    }
}
