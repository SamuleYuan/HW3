package com.example.demo.service;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Shuai Yuan
 */
@Service
public class EmployeeService {

    private final String url = "http://dummy.restapiexample.com/api/v1/employees";

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Employee> getAllEmployees(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, Response.class).getBody().getData();
    }

    //getForObject is easier to use
    public List<List<Employee>> getAllEmployeesGroupByAge(){
        Map<Integer, List<Employee>> employeeMapByAge = getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getEmployee_age));
        List<List<Employee>> result = new ArrayList<>();
        for(int age: employeeMapByAge.keySet()){
            result.add(employeeMapByAge.get(age));
        }
        return result;
    }

    public List<Employee> getEmployeesByAge(int age){
        return getAllEmployees().stream().filter(employee -> employee.getEmployee_age() == age).collect(Collectors.toList());
    }
}
