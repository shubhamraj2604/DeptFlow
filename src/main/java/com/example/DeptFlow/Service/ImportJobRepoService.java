package com.example.DeptFlow.Service;

import org.springframework.stereotype.Service;

import com.example.DeptFlow.Models.ImportJob;
import com.example.DeptFlow.Repository.ImportRepository;

@Service 
public class ImportJobRepoService {
    private final ImportRepository importRepository;

    public ImportJobRepoService(ImportRepository importRepository){
        this.importRepository = importRepository;
    }


    public ImportJob saveImportData(ImportJob importJob){
       return importRepository.save(importJob);
    }
}
