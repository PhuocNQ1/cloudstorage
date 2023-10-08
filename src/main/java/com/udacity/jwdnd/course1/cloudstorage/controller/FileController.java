package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileException;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.services.file_service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * Upload file and save file to server directory
     *
     * @param file
     * @return
     * @throws FileException
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file) {
        FileModel fileModel = new FileModel();
        try {
            fileService.mapMultipartFile(file, fileModel);
            String filePath = fileService.getResourceDirectory() + fileModel.getFileName();
            file.transferTo(new File(filePath));
            fileService.add(fileModel);
            fileService.setMsgError(null);
        } catch (FileException e) {
            fileService.setMsgError(e.getMessage());
        } catch (Exception e) {
            fileService.setMsgError("Error: An unexpected error has occurred during the insertion process.");
        }

        return "redirect:/home";
    }

    /**
     * Download file by @param fileName
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable String fileName) throws IOException {
        String resourceDirectory = fileService.getResourceDirectory();
        Path filePath = Paths.get(resourceDirectory).resolve(fileName);
        FileSystemResource resource = new FileSystemResource(filePath.toFile());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    /**
     * Delete file by @param id
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable String id) {
        FileModel fm = fileService.get(Integer.valueOf(id));
        String resourceDirectory = fileService.getResourceDirectory();
        String filePath = resourceDirectory + fm.getFileName();

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            fileService.deleteByID(Integer.valueOf(id));
        }
        return "redirect:/home";
    }
}
