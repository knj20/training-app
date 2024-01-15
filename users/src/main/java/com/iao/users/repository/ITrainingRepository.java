package com.iao.users.repository;

import com.iao.users.model.entity.Employee;
import com.iao.users.model.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITrainingRepository extends JpaRepository<Training, Integer> {
}
