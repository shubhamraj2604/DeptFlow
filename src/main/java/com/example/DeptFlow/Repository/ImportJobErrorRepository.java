package com.example.DeptFlow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DeptFlow.Models.ImportJobError;
import java.util.List;

@Repository 
public interface ImportJobErrorRepository extends JpaRepository<ImportJobError , Long>{
    List<ImportJobError> findAllByImportJob_Id(Long importJobId);
}
