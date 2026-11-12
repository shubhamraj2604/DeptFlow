package com.example.DeptFlow.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class FileStorageService {
    
    private final Path uploadDir = Paths.get("uploads");
    public String storeFile(MultipartFile file) throws IOException {
        
         Files.createDirectories(uploadDir);

         String filename = file.getOriginalFilename();
         Path filePath = uploadDir.resolve(filename);
        Files.copy(
                   file.getInputStream(), 
                    filePath,
                   StandardCopyOption.REPLACE_EXISTING);
       return filePath.toAbsolutePath().toString();
    }
}
