package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Employee createEmployee(Employee employee) throws Exception {
    	
    	// Server end Filed validation.
    	String employeeName = employee.getEmpName();
    	String departmentName = employee.getDepartment().getDepartmentName();
    	
    	if(!employeeRepository.existsByEmployeeNameAndDepartment(employeeName, departmentName)) {
			Optional<DepartmentEntity> departmentEntity = departmentRepository.findByDepartmentName(employee.getDepartment().getDepartmentName());
			if(departmentEntity.isPresent()) {
				EmployeeEntity employeeEntity = new EmployeeEntity();
		        BeanUtils.copyProperties(employee, employeeEntity);
		        employeeEntity.setDepartment(departmentEntity.get());
		        employeeRepository.save(employeeEntity);
			}else {
				throw new Exception("Department not found !!!");
			}
    	}else {
    		throw new Exception("Employee already existest !!! -   [ "+ departmentName +" ] department!!!");
    	}
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities
                = employeeRepository.findAll();
        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> { 
                	return new Employee( 
                			emp.getId(),
                			emp.getEmpName(),
                			emp.getSalary(), 
                			new Department(emp.getDepartment().getId(), emp.getDepartment().getDepartmentName())
                        );
                	})
                .collect(Collectors.toList());
        return employees;
    }
    
    @Override
    public List<Employee> getAllEmployeesByDepartmentId(Long depId) {
        List<EmployeeEntity> employeeEntities
                = employeeRepository.getEmployeeByDepId(depId);
        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> { 
                	return new Employee( 
                			emp.getId(),
                			emp.getEmpName(),
                			emp.getSalary(), 
                			new Department(emp.getDepartment().getId(), emp.getDepartment().getDepartmentName())
                        );
                	})
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
        		.orElseThrow(() -> new EntityNotFoundException("Employee not found with Id :"+id));
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found with Id :"+id));
        Department department =new Department( employeeEntity.getDepartment().getId(), employeeEntity.getDepartment().getDepartmentName());
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        employee.setDepartment(department);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
    	DepartmentEntity departmentEntity = departmentRepository.findById(employee.getDepartment().getDepartmentId()).orElseThrow(() -> new EntityNotFoundException("Department not found with Id :"+id));
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setSalary(employee.getSalary());
        employeeEntity.setEmpName(employee.getEmpName());
        employeeEntity.setDepartment(departmentEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }
    
    @Override
    public Department createDepartments(Department department) throws Exception {
    	if(!departmentRepository.existsByDepartmentName(department.getDepartmentName())) { 
    		DepartmentEntity entities = new DepartmentEntity();
    		entities.setId(0L);
    		entities.setDepartmentName(department.getDepartmentName());
    		this.departmentRepository.save(entities);
    		department.setDepartmentId(entities.getId());
    	}else {
    		throw new Exception("Duplicate department added !!!");
    	}
    	return department;
    }

	@Override
	public List<Employee> getEmployeesWithSalaryGreaterThanEqlSalary(Long salary, Boolean flag) {
		List<EmployeeEntity> employeeEntities = null;
		if(flag)
			{ employeeEntities =  employeeRepository.getBySalaryGreaterThan(salary); } else
		 { employeeEntities =  employeeRepository.getBySalaryLessThan(salary); }
		
        List<Employee> employees = employeeEntities
        .stream()
        .map(emp -> { 
        	return new Employee( 
        			emp.getId(),
        			emp.getEmpName(),
        			emp.getSalary(), 
        			new Department(emp.getDepartment().getId(), emp.getDepartment().getDepartmentName())
                );
        	})
        .collect(Collectors.toList());
        return employees;
	}

	@Override
	public List<Department> getAllDepartment() {
		List<DepartmentEntity> listOfDepartmentEntity = departmentRepository.findAll();
		return listOfDepartmentEntity.stream().map( emp -> new Department(emp.getId(), emp.getDepartmentName())).collect(Collectors.toList());
	}
}
