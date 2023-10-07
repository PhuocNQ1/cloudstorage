package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileException;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import com.udacity.jwdnd.course1.cloudstorage.services.file_service.FileService;

@Controller
@RequestMapping("/file")
public class FileController {

    private static final String urlDirServer = "D:\\UDACITY\\nd035-c1-spring-boot-basics-project-starter-master\\nd035-c1-spring-boot-basics-project-starter-master\\starter\\cloudstorage\\src\\main\\resources\\image"
            + File.separator;

    private static int idTemp = 0;

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
        fileModel.setFileId(idTemp);
        fileModel.setFileName(file.getOriginalFilename());
        fileModel.setContenType(file.getContentType());
        fileModel.setFileSize(Long.toString(file.getSize()));

        // emulator id generator
        idTemp++;

        String filePath = urlDirServer + fileModel.getFileName();

        try {
            file.transferTo(new File(filePath));
            fileService.add(fileModel);
            fileService.setMsgError(null);
        } catch (FileException e) {
            fileService.setMsgError(e.getMessage());
        } catch (Exception e) {
            fileService.setMsgError("Server_ERR");
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
        Path filePath = Paths.get(urlDirServer).resolve(fileName);
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
        int idz = Integer.valueOf(id);

        FileModel fm = fileService.get(idz);

        String filePath = urlDirServer + fm.getFileName();

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            fileService.deleteByID(idz);
        }
        return "redirect:/home";
    }
}
