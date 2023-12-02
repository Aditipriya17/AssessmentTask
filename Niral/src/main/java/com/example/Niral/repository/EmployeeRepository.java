package com.example.Niral.repository;

import com.example.Niral.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);

    List<Employee> findAllByEmpName(String name);

    @Query("SELECT DISTINCT e.teamName FROM Employee e")
    List<String> findDistinctTeamName();

    @Transactional
    void deleteByEmpName(String empName);
}
