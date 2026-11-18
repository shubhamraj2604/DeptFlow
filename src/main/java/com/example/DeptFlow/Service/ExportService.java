package com.example.DeptFlow.Service;

import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExportService {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public ExportService(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public void exportData(HttpServletResponse response) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(
                response.getWriter(),
                CSVFormat.DEFAULT.withHeader(
                        "department_code",
                        "department_name",
                        "id",
                        "first_name",
                        "last_name",
                        "email",
                        "phone",
                        "designation",
                        "status",
                        "date_of_joining",
                        "deactivated_at"))) {

            employeeService.getAllEmployee().forEach(employee -> {
                try {
                    printer.printRecord(
                            employee.getDepartment().getCode(),
                            employee.getDepartment().getName(),
                            employee.getId(),
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getEmail(),
                            employee.getPhone(),
                            employee.getDesignation(),
                            employee.getStatus(),
                            employee.getDate_of_joining(),
                            employee.getDeactivated_at());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
