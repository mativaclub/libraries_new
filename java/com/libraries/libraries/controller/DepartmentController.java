package com.libraries.libraries.controller;

import com.libraries.libraries.data.Employee;
import com.libraries.libraries.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee employeeWithMaxSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.employeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee employeeWithMinSalary(@RequestParam("departmentId") int departmentId) {
        return departmentService.employeeWithMinSalary(departmentId);
    }

    @GetMapping(path="/all", params = "departmentId")
    public Collection<Employee> findEmployeesByDepartment (@RequestParam("departmentId") int departmentId) {
        return departmentService.findEmployeesByDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAllEmployeesByDepartment() {
        return departmentService.findAllEmployees();
    }
}
