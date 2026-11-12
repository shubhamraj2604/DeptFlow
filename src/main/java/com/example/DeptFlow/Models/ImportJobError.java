package com.example.DeptFlow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
public class ImportJobError{
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "import_job_id")
    private ImportJob importJob;

    public Long getImportId(){
        return importJob.getId();
    }
    Long lineNo;
    String rawLine;
    String reason;
}
