package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	@Autowired
    private EmployeeServiceImpl employeeService;
    
    @BeforeEach
    public void setUp() {
    
    }
    
    @Test
    public void testGetAllEmployees() {
 // Given
    DepartmentEntity departmentEntity = new DepartmentEntity();
	    departmentEntity.setId(1L);
	    departmentEntity.setDepartmentName("Engineering");
    EmployeeEntity employeeEntity1 = new EmployeeEntity();
	    employeeEntity1.setId(1L);
	    employeeEntity1.setEmpName("John Doe");
	    employeeEntity1.setSalary(50000.0);
	    employeeEntity1.setDepartment(departmentEntity);
    EmployeeEntity employeeEntity2 = new EmployeeEntity();
	    employeeEntity2.setId(2L);
	    employeeEntity2.setEmpName("Jane Smith");
	    employeeEntity2.setSalary(60000.0);
	    employeeEntity2.setDepartment(departmentEntity);
	    
	when(employeeRepository.findAll()).thenReturn(Arrays.asList(employeeEntity1, employeeEntity2));

    // When
    List<Employee> employees = employeeService.getAllEmployees();

    // Then
    assertEquals(2, employees.size());

    Employee employee1 = employees.get(0);
	    assertEquals(1L, employee1.getId());
	    assertEquals("John Doe", employee1.getEmpName());
	    assertEquals(50000.0, employee1.getSalary());
	    assertEquals(1L, employee1.getDepartment().getDepartmentId());
	    assertEquals("Engineering", employee1.getDepartment().getDepartmentName());

    Employee employee2 = employees.get(1);
	    assertEquals(2L, employee2.getId());
	    assertEquals("Jane Smith", employee2.getEmpName());
	    assertEquals(60000.0, employee2.getSalary());
	    assertEquals(1L, employee2.getDepartment().getDepartmentId());
	    assertEquals("Engineering", employee2.getDepartment().getDepartmentName());

    verify(employeeRepository, times(1)).findAll();
    }
}
