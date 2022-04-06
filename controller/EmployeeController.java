package com.example.demo.controller;

import com.example.demo.pojo.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shuai Yuan
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees_age")
    private ResponseEntity<List<Employee>> getEmployeesByAge(@RequestParam(value="age", required = false, defaultValue = "-1") int age){
        return new ResponseEntity<>(employeeService.getEmployeesByAge(age), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<List<Employee>>> getAllEmployeesGroupByAge(){
        return new ResponseEntity<>(employeeService.getAllEmployeesGroupByAge(), HttpStatus.OK);
    }
}
