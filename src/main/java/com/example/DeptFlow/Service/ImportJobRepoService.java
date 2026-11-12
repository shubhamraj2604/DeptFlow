package com.example.DeptFlow.Service;

import org.springframework.stereotype.Service;
import java.util.*;
import com.example.DeptFlow.Models.ImportJob;
import com.example.DeptFlow.Models.ImportJobError;
import com.example.DeptFlow.Repository.ImportRepository;
import com.example.DeptFlow.Service.ImportJobErrorService;
@Service 
public class ImportJobRepoService {
    private final ImportRepository importRepository;
    private final ImportJobErrorService importJobErrorService;
    public ImportJobRepoService(ImportRepository importRepository, ImportJobErrorService importJobErrorService  ){
        this.importRepository = importRepository;
        this.importJobErrorService = importJobErrorService;
    }


    public ImportJob saveImportData(ImportJob importJob){
       return importRepository.save(importJob);
    }

    public ImportJob getImportJobById(Long id){
        return importRepository.findById(id).orElse(null);
    }

    public List<ImportJob> getAllImportJobs(){
        return importRepository.findAll();
    }

    public List<ImportJobError> getImportErrorById(Long id){
        return importJobErrorService.getErrorById(id);
    }
}
