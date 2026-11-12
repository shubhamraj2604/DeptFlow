package com.example.DeptFlow.Messaging.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DeptFlow.Messaging.producer.RabbitMqProducer;
import com.example.DeptFlow.Service.FileStorageService;
import com.example.DeptFlow.Service.ImportService;

import java.io.IOException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping ("/api/import")
public class MessageController {
    private final RabbitMqProducer rabbitMqProducer;
    private final FileStorageService fileStorageService;
    public MessageController(RabbitMqProducer rabbitMqProducer , FileStorageService fileStorageService){
        this.rabbitMqProducer = rabbitMqProducer;
        this.fileStorageService = fileStorageService;
    }
    
    @GetMapping("/publish")
    public ResponseEntity<String> getMethodName(@RequestParam("messages") String param) {
        rabbitMqProducer.sendMessage(param);
        return ResponseEntity.ok("message sent to mq");
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam ("file") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file);
        rabbitMqProducer.sendMessage(fileName);
        return ResponseEntity.accepted().body("File Uploaded");
    }

}
