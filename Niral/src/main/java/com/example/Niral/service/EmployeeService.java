package com.example.Niral.service;

import com.example.Niral.entity.Employee;
import com.example.Niral.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    public EmployeeService() {
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    public List<Employee> findAllByName(String name) {
        return employeeRepository.findAllByEmpName(name);
    }

    public List<String> findDistinctTeamName() {
        return employeeRepository.findDistinctTeamName();
    }

    public Employee updateRole(int employeeId, String newRole) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setRole(newRole);
            return employeeRepository.save(employee);
        }

        return null;
    }

    public Employee updateSalary(int employeeId, double newSalary) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setSalary(newSalary);
            return employeeRepository.save(employee);
        }

        return null;
    }

    public Employee updateTeamName(int employeeId, String newTeamName) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setTeamName(newTeamName);
            return employeeRepository.save(employee);
        }

        return null;
    }

    public void deleteById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public void deleteByEmpName(String empName) {
        employeeRepository.deleteByEmpName(empName);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
