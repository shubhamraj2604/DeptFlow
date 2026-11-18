package com.example.DeptFlow.Service;

import com.example.DeptFlow.Models.Department;
import com.example.DeptFlow.Models.Employee;
import com.example.DeptFlow.Repository.DepartmentRepository;
import com.example.DeptFlow.Repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;

    public DepartmentService(DepartmentRepository departmentRepository , EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }

    public void saveDepartment(Department department) {
        // departmentRepository.saveRepo(department);
        departmentRepository.save(department);
    }

    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
        // return departmentRepository.findAll();
    }

    public Department getDepartmentWithId(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            return null; // or throw an exception, or return an optional, depending on your design
        }
    }

    public Department updateDepartment(Long id ,Department department){
        Optional<Department> dept = departmentRepository.findById(id);
        if(dept.isEmpty()){
            return null;
        }
        Department existingDepartment = dept.get();
        existingDepartment.setCode(department.getCode());
        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(department.getDescription());
        existingDepartment.setHead_name(department.getHead_name());
        existingDepartment.setLocation(department.getLocation());
        existingDepartment.setContact_email(department.getContact_email());
        return departmentRepository.save(existingDepartment);
    }

    public Department deleteDepartment(Long id){
        Optional<Department>dept = departmentRepository.findById(id);
        if(dept.isEmpty()){
            return null;
        }

        Department department = dept.get();
        departmentRepository.delete(department);
        return department;
    }

    public Iterable<Employee> getEmployeeWithId(Long id){
        Optional<Department> dept = departmentRepository.findById(id);
        if(dept.isEmpty()){
            return null;
        }
        return employeeService.findbyEmpId(id);
    }

    public Employee saveEmployeeWithID(Long Id , Employee employee){
        Optional<Department> department = departmentRepository.findById(Id);
        if(department.isEmpty()){
            return null;
        }
        employee.setDepartment(department.get());
        return employeeService.saveEmployee(employee);
    }


    public Department deptPresent(String dept_code){
        Department dept = departmentRepository.findByCode(dept_code);
        if(dept == null){
            return null;
        }
        return dept;
    }
}
