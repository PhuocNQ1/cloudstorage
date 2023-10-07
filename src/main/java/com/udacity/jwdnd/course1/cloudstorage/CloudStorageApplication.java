package com.udacity.jwdnd.course1.cloudstorage;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

@SpringBootApplication
public class CloudStorageApplication {

   @Autowired
    private static EncryptionService encryptionService = new EncryptionService();
    
    public static void main(String[] args) {
        SpringApplication.run(CloudStorageApplication.class, args);
        
        
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        
        
        
        String dataToEncrypt = "Hello, world!";
        String encryptionKey = "MyEncryptionKey123"; // Replace with your encryption key.


        String encryptedValue = encryptionService.encryptValue(dataToEncrypt, encodedSalt);

        System.out.println("Original Data: " + dataToEncrypt);
        System.out.println("Encrypted Data (Base64): " + encryptedValue);
        
        String pwDecrypt = encryptionService.decryptValue(encryptedValue, encodedSalt);
        System.out.print(pwDecrypt);
        
    }
    
    

}
