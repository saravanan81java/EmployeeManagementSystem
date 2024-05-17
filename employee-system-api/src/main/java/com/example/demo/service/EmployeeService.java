package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;

@Service
public interface EmployeeService {
    Employee createEmployee(Employee employee) throws Exception;

    List<Employee> getAllEmployees();
    
    boolean deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);
    
    Department createDepartments(Department department) throws Exception;

	List<Employee> getAllEmployeesByDepartmentId(Long depId);

	List<Employee> getEmployeesWithSalaryGreaterThanEqlSalary(Long salary, Boolean flag);

	List<Department> getAllDepartment();
}
