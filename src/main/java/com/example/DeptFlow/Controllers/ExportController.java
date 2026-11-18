package com.example.DeptFlow.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DeptFlow.Service.ExportService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;

@RestController 
@RequestMapping("/api/export")
public class ExportController {
    ExportService exportService;
    public ExportController(ExportService exportService){
        this.exportService = exportService;
    }


    @GetMapping
    public void export(HttpServletResponse response) {
        
    response.setContentType("text/csv");
    response.setHeader(
        "Content-Disposition",
        "attachment; filename=employees.csv"
    );
        try {
            exportService.exportData(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
