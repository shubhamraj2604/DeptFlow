package com.example.DeptFlow.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DeptFlow.Models.ImportJob;
import com.example.DeptFlow.Models.ImportJobError;
import com.example.DeptFlow.Repository.ImportJobErrorRepository;

@Service 
public class ImportJobErrorService {
    private final ImportJobErrorRepository importJobErrorRepository;

    public ImportJobErrorService(ImportJobErrorRepository importJobErrorRepository){
        this.importJobErrorRepository = importJobErrorRepository;
    }

    
    public ImportJobError save(ImportJobError importJobError){
        return importJobErrorRepository.save(importJobError);
    }

    public ImportJobError saveError(
        ImportJob importJob,
        Long lineNo,
        String rawLine,
        String reason) {

    ImportJobError error = new ImportJobError();
    error.setImportJob(importJob);
    error.setLineNo(lineNo);
    error.setRawLine(rawLine);
    error.setReason(reason);
    return importJobErrorRepository.save(error);
}

    public List<ImportJobError> getErrorById(Long id){
        return importJobErrorRepository.findAllByImportJob_Id(id);
    }

}
