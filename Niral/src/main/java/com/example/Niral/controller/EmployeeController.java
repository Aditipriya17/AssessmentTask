package com.example.Niral.controller;

import com.example.Niral.entity.Employee;
import com.example.Niral.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/getAll")
    public List<Employee> getAllemployes()
    {
        List<Employee> findAll = employeeService.findAll();
        return findAll;
    }

    @GetMapping("/getBySalaryRange")
    public List<Employee> getEmployeesBySalaryRange(@RequestParam("minSalary") double minSalary,
                                                    @RequestParam("maxSalary") double maxSalary) {
        List<Employee> employeesInSalaryRange = employeeService.findBySalaryRange(minSalary, maxSalary);
        return employeesInSalaryRange;
    }

    @GetMapping("/getByName/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return employeeService.findAllByName(name);
    }

    @GetMapping("/getDistinctTeamNames")
    public List<String> getDistinctTeamNames() {
        return employeeService.findDistinctTeamName();
    }

    @PutMapping("/updateRole/{employeeId}")
    public ResponseEntity<Employee> updateRole(@PathVariable int employeeId,
                                               @RequestParam String newRole) {
        Employee updatedEmployee = employeeService.updateRole(employeeId, newRole);

        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateSalary/{employeeId}")
    public ResponseEntity<Employee> updateSalary(@PathVariable int employeeId,
                                                 @RequestParam double newSalary) {
        Employee updatedEmployee = employeeService.updateSalary(employeeId, newSalary);

        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTeamName/{employeeId}")
    public ResponseEntity<Employee> updateTeamName(@PathVariable int employeeId,
                                                   @RequestParam String newTeamName) {
        Employee updatedEmployee = employeeService.updateTeamName(employeeId, newTeamName);

        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteByEmpName/{empName}")
    public ResponseEntity<Void> deleteEmployeeByEmpName(@PathVariable String empName) {
        employeeService.deleteByEmpName(empName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllEmployees() {
        employeeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
