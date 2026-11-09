package com.example.DeptFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.DeptFlow.Models.Department;
@SpringBootApplication
public class DeptFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeptFlowApplication.class, args);
		// System.out.println("hello");

		// Department department = new Department();
		// department.setId(1);
		// department.setCode("HR");
		// department.setName("Human Resources");
		// department.setDescription("Handles employee relations and recruitment.");
		// department.setHead_name("John Doe");
		// department.setLocation("Building A, Floor 2");
		// department.setContact_email("hr@company.com");

		// System.out.println("Department ID: " + department.getId());
		// System.out.println("Department Code: " + department.getCode());
		// System.out.println("Department Name: " + department.getName());
		// System.out.println("Department Description: " + department.getDescription());
		// System.out.println("Department Head: " + department.getHead_name());
		// System.out.println("Department Location: " + department.getLocation());
		// System.out.println("Department Contact Email: " + department.getContact_email());
	}

}
