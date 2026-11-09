package com.example.DeptFlow.Repository;

import java.util.*;

import com.example.DeptFlow.Models.Department;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DepartmentRepository extends 
       JpaRepository<Department, Long> {
//    Map<String , Department> departmentRepo;

//    public DepartmentRepository(){
//     departmentRepo = new HashMap<>();
//    }


//    public void saveRepo(Department department){
//       departmentRepo.put(department.getCode() , department);
//    }


//    public List<Department> findAll(){
//       List<Department> result = new ArrayList<>();
//       for(Department department : departmentRepo.values()){
//         result.add(department);
//       }
//       return result;
//    }
// }
}
