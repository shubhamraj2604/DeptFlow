package com.example.DeptFlow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DeptFlow.Models.ImportJob;

@Repository 
public interface ImportRepository extends JpaRepository<ImportJob , Long>{
    
}
