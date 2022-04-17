package com.libraries.libraries.service.impl;

import com.libraries.libraries.data.Employee;
import com.libraries.libraries.exceptions.EmployeeExistsException;
import com.libraries.libraries.exceptions.EmployeeInvalidNameException;
import com.libraries.libraries.exceptions.EmployeeNotFoundException;
import com.libraries.libraries.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        String key = getKey(firstName, lastName);
        validateNames(firstName, lastName);

        Employee addEmployee = new Employee(firstName, lastName, salary, department);
        StringUtils.capitalize(firstName);
        StringUtils.capitalize(lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeExistsException("Employee already added");
        }

        employees.put(key, addEmployee);
        return addEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employees.get(key);
    }

    public Employee findEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employees.get(key);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public void validateNames(String... names) {
        for (String name : names) {
            if (!isAlpha(name)) {
                throw new EmployeeInvalidNameException(name);
            }
        }
    }


    @Override
    public String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }


}
