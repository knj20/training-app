package com.iao.users.repository;

import com.iao.users.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.training.training_id = :training_id")
    List<Employee> findByTrainingNameJPQL(@Param("training_id") int training_id);
}
