package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DepartmentEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
	
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM DepartmentEntity e WHERE e.departmentName = :departmentName")
    boolean existsByDepartmentName(@Param("departmentName") String departmentName);
	
	Optional<DepartmentEntity> findByDepartmentName(String departmentName);

}
