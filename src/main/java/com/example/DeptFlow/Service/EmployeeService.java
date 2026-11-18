package com.example.DeptFlow.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.DeptFlow.Models.Employee;
import com.example.DeptFlow.Repository.EmployeeRepository;
import com.example.DeptFlow.Repository.DepartmentRepository;
import com.example.DeptFlow.Models.Department;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;


    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository ) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    public Iterable<Employee> findbyEmpId(Long id) {
        return employeeRepository.findByDepartmentId(id);
    }

    public Iterable<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    // update employee
    public Employee updateEmployeeById(Long id, Employee employee) {
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isEmpty()) {
            return null;
        }
        
        Employee newEmployee = existing.get();
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setPhone(employee.getPhone());
        newEmployee.setDesignation(employee.getDesignation());
        newEmployee.setStatus(employee.getStatus());
        newEmployee.setDeactivated_at(employee.getDeactivated_at());
        newEmployee.setDate_of_joining(employee.getDate_of_joining());
        if(employee.getDepartment() != null) {
           Long deptId = employee.getDepartment().getId();
           Department department = departmentRepository.findById(deptId).orElse(null);
           if(department == null)return null;
           newEmployee.setDepartment(department);
        }
        return employeeRepository.save(newEmployee);
    }


    public Employee deactivateEmployeeById(Long id){
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isEmpty()){
            return null;
        }
        Employee newEmp = emp.get();
        newEmp.setStatus("INACTIVATE");
        newEmp.setDeactivated_at(LocalDateTime.now());
        return employeeRepository.save(newEmp);
    }


    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }


    public Boolean validEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }


    public Boolean validPhoneNumber(String phone) {
        List<Employee> employees = employeeRepository.findAll();
        for(Employee emp : employees){
            if(emp.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }
}
