package com.example.DeptFlow.Controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.DeptFlow.Models.Employee;
import com.example.DeptFlow.Repository.EmployeeRepository;
import com.example.DeptFlow.Service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;



@RestController 
@RequestMapping("/api/Employee")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    // add employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        if(savedEmployee == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(savedEmployee);
    }
    
    @GetMapping()
    public Iterable<Employee> getEmployee() {
        return employeeService.getAllEmployee();
    }
    
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
      Employee updateEmployee = employeeService.updateEmployeeById(id, employee);
      if(updateEmployee == null){
        return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok("Updated successfully");
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateEmployee(@PathVariable Long id){
        Employee employee = employeeService.deactivateEmployeeById(id);
        if(employee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok("Successfully deactivated employee");
    }
}


