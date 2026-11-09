package com.example.DeptFlow.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DeptFlow.Models.Department;
import com.example.DeptFlow.Models.Employee;
import com.example.DeptFlow.Service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController 
@RequestMapping ("/api/department")
public class DepartmentController {
    private DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public Iterable<Department> getDepartment(){
        return departmentService.getAllDepartments();
    }   

    @PostMapping
    public String saveDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
        return "Department saved successfully";

    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
    Department dept = departmentService.getDepartmentWithId(id);

      if(dept == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.ok(dept);
    }

    @GetMapping("{id}/employees")
    public ResponseEntity<Iterable<Employee>> getEmployeesByDepartmentId(@PathVariable Long id) {
        Iterable<Employee> employees = departmentService.getEmployeeWithId(id);
        if (employees == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("{id}/employees")
    public ResponseEntity<Employee> postEmployeesByDepartmentId(@PathVariable Long id, @RequestBody Employee employee) {
        Employee employees = departmentService.saveEmployeeWithID(id, employee);
        if (employees == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employees);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Department entity) {
       Department dept =  departmentService.updateDepartment(id, entity);
       if(dept ==  null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
        return ResponseEntity.ok(dept).ok("Department updated successfully");
    }

    
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id){
        Department dept = departmentService.deleteDepartment(id);
        if(dept == null){
            return "Department Not Found";
        }
        return "Department deleted successfully";
    }
}
