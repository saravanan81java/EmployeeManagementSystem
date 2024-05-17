package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

 

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping("/ping")
    public String ping() {
    	return "ping";
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) throws Exception {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/employees/department/{depId}")
    public List<Employee> getAllEmployees(@PathVariable Long depId) {
        return employeeService.getAllEmployeesByDepartmentId(depId);
    }
    
    @GetMapping("/employees/salary/{salary}/{salaryFilter}")
    public List<Employee> getEmployeesWithSalaryGreaterThanEqlSalary(@PathVariable Long salary,
    		@PathVariable Boolean salaryFilter) {
        return employeeService.getEmployeesWithSalaryGreaterThanEqlSalary(salary, salaryFilter);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        boolean deleted = false;
        deleted = employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Employee has been deleted.", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = null;
        employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(employee);
    }
    
    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) throws Exception {
        department = employeeService.createDepartments(department);
        return department;
    }
    
    @GetMapping("/departments")
    public List<Department> getAllDepartment() {
        return employeeService.getAllDepartment();
    }
}
