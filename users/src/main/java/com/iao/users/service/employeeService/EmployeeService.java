package com.iao.users.service.employeeService;

import com.iao.users.model.entity.Employee;
import com.iao.users.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeSrvice{

    @Autowired
    private IEmployeeRepository repo;
    @Override
    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        repo.delete(getEmployeeById(id));
    }

    @Override
    public void updateEmployee(Employee employee) {
        repo.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByTrainingId(int trainingId) {
        return repo.findByTrainingNameJPQL(trainingId);
    }
}
