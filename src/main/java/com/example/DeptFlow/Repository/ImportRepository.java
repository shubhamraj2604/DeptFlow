package com.example.DeptFlow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DeptFlow.Models.ImportJob;

public interface ImportRepository extends JpaRepository<ImportJob , Long>{
    
}
