package com.iao.users.service.employeeService;

import com.iao.users.model.entity.Employee;

import java.util.List;

public interface IEmployeeSrvice {
    public Employee saveEmployee(Employee employee);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(int id);
    public void deleteEmployeeById(int id);
    public void updateEmployee(Employee employee);

    public List<Employee> getEmployeesByTrainingId(int trainingId);
}
