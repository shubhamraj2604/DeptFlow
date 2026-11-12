package com.example.DeptFlow.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.example.DeptFlow.Models.Department;
import com.example.DeptFlow.Models.Employee;
import com.example.DeptFlow.Models.ImportJob;
import com.example.DeptFlow.Models.ImportJobError;

@Service
public class ImportService {
    private final ImportJobRepoService importJobRepoService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final ImportJobErrorService importJobErrorService;

    public ImportService(EmployeeService employeeService, DepartmentService departmentService,
            ImportJobRepoService importJobRepoService, ImportJobErrorService importJobErrorService) {
        this.importJobErrorService = importJobErrorService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.importJobRepoService = importJobRepoService;
    }

    public void importEmployees(String fileName) throws IOException {
        Path filePath = Paths.get("uploads").resolve(fileName);
        try (Reader reader = Files.newBufferedReader(filePath)) {
            CSVParser csvParser = CSVFormat.DEFAULT.builder().setHeader()
                    .setSkipHeaderRecord(true)
                    .get()
                    .parse(reader);

            // save importJob
            ImportJob importJob = new ImportJob();
            importJob.setFileName(fileName);
            importJob.setFilePath(filePath.toString());
            importJob.setStatus("PROCESSING");
            importJob.setStarted_at(LocalDateTime.now());
            List<CSVRecord> records = csvParser.getRecords();
            importJob.setTotal_rows(records.size());
            importJobRepoService.saveImportData(importJob);

            // traverse through the csv file
            List<Employee> employees = new ArrayList<>();
            for (CSVRecord record : records) {
                String deptCode = record.get("department_code");
                Department department = departmentService.deptPresent(deptCode);



                if (department == null) {
                    importJobErrorService.saveError(
                            importJob,
                            record.getRecordNumber(),
                            record.toString(),
                            "Department with code " + deptCode + " does not exist.");
                    System.out.println("Department with code " + deptCode + " does not exist.");
                    importJob.setFailure_count(importJob.getFailure_count() + 1);
                    // throw new IllegalArgumentException("Department with code " + deptCode + " does not exist.");
                    continue;
                }


                String firstName = record.get("first_name");
                String lastName = record.get("last_name");
                String email = record.get("email");
                if (employeeService.validEmail(email)) {
                    importJobErrorService.saveError(
                            importJob,
                            record.getRecordNumber(),
                            record.toString(),
                            "Employee with email " + email + " already exists.");
                    importJob.setFailure_count(importJob.getFailure_count() + 1);
                    System.out.println("Employee with email " + email + " already exists. Skipping this record.");
                    continue; // Skip this record and move to the next one
                }



                String phone = record.get("phone");
                if (phone == null || phone.length() != 10 || employeeService.validPhoneNumber(phone)) {
                    importJobErrorService.saveError(
                            importJob,
                            record.getRecordNumber(),
                            record.toString(),
                            "Phone number " + phone + " is not valid.");
                    importJob.setFailure_count(importJob.getFailure_count() + 1);
                    System.out.println("Phone number " + phone + " is not valid. Skipping this record.");
                    continue; // Skip this record and move to the next one
                }



                String designation = record.get("designation");
                String date = record.get("date_of_joining");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate localDate = LocalDate.parse(date, formatter);

                LocalDateTime joiningDate = localDate.atStartOfDay();
                Employee employee = new Employee();
                employee.setDepartment(department);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setEmail(email);
                employee.setPhone(phone);
                employee.setDesignation(designation);
                employee.setDate_of_joining(joiningDate);
                employees.add(employee);
                // importJobErrorService.save(importJobError);
                importJob.setSuccess_count(importJob.getSuccess_count() + 1);
            }


            employeeService.saveAll(employees);
            importJob.setStatus("COMPLETED");
            importJob.setProcessed_rows(importJob.getSuccess_count() + importJob.getFailure_count());
            importJob.setError_message(importJob.getFailure_count() > 0 ? "Some records failed to import. Please check the error log." : null);
            importJob.setCompleted_at(LocalDateTime.now());
            importJobRepoService.saveImportData(importJob);
        }
    }
}
