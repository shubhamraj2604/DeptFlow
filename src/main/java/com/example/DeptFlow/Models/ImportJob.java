package com.example.DeptFlow.Models;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity 
@Getter 
@Setter 
public class ImportJob {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    String fileName;
    String filePath;
    String status;
    int total_rows;
    int processed_rows;
    int success_count;
    int failure_count;
    String error_message;
    LocalDateTime started_at;
    LocalDateTime completed_at;
}
