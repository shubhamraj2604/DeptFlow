package com.example.DeptFlow.Messaging.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DeptFlow.Messaging.producer.RabbitMqProducer;
import com.example.DeptFlow.Models.ImportJob;
import com.example.DeptFlow.Models.ImportJobError;
import com.example.DeptFlow.Service.FileStorageService;
import com.example.DeptFlow.Service.ImportJobRepoService;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController 
@RequestMapping ("/api/import")
public class MessageController {
    private final RabbitMqProducer rabbitMqProducer;
    private final FileStorageService fileStorageService;
    private final ImportJobRepoService importJobRepoService;
    public MessageController(RabbitMqProducer rabbitMqProducer , FileStorageService fileStorageService , ImportJobRepoService importJobRepoService){
        this.rabbitMqProducer = rabbitMqProducer;
        this.fileStorageService = fileStorageService;
        this.importJobRepoService = importJobRepoService;
    }
    
    @GetMapping
    public ResponseEntity<List<ImportJob>> getMethodName() {
        List<ImportJob> listOfImportJobs = importJobRepoService.getAllImportJobs();
        return ResponseEntity.ok(listOfImportJobs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ImportJob> getId(@PathVariable  Long id) {
        ImportJob importJob = importJobRepoService.getImportJobById(id);
        if (importJob != null) {
            return ResponseEntity.ok(importJob);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/error")
    public ResponseEntity<List<ImportJobError>> getErrorById(@PathVariable Long id) {
        List<ImportJobError> errors = importJobRepoService.getImportErrorById(id);
        if (errors != null && !errors.isEmpty()) {
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam ("file") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file);
        rabbitMqProducer.sendMessage(fileName);
        return ResponseEntity.accepted().body("File Uploaded");
    }

}
