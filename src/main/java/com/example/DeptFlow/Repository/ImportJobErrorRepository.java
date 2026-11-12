package com.example.DeptFlow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DeptFlow.Models.ImportJobError;

public interface ImportJobErrorRepository extends JpaRepository<ImportJobError , Long>{
    
}
