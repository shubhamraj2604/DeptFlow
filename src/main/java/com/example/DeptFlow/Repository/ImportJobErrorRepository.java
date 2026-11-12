package com.example.DeptFlow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DeptFlow.Models.ImportJobError;
@Repository 
public interface ImportJobErrorRepository extends JpaRepository<ImportJobError , Long>{
    
}
