package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM EmployeeEntity e WHERE e.empName = :empName")
    boolean existsByEmployeeName(@Param("empName") String empName);
	
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM EmployeeEntity e WHERE e.empName = :empName and e.department.departmentName = :departmentName")
    boolean existsByEmployeeNameAndDepartment(@Param("empName") String empName, @Param("departmentName") String departmentName);
	
	@Query("SELECT e FROM EmployeeEntity e WHERE e.department.id = :depId")
	List<EmployeeEntity> getEmployeeByDepId(@Param("depId") Long depId);
	
	@Query("SELECT e FROM EmployeeEntity e WHERE e.salary >= :salary")
    List<EmployeeEntity> getBySalaryGreaterThanEqual(@Param("salary") Long salary);
	
	@Query("SELECT e FROM EmployeeEntity e WHERE e.salary > :salary")
    List<EmployeeEntity> getBySalaryGreaterThan(@Param("salary") Long salary);
	
	@Query("SELECT e FROM EmployeeEntity e WHERE e.salary < :salary")
    List<EmployeeEntity> getBySalaryLessThan(@Param("salary") Long salary);
	
}
