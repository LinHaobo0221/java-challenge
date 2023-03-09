package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> retrieveEmployees();

    Employee getEmployee(String employeeId);

    void saveEmployee(Employee employee);

    void deleteEmployee(String employeeId);

    void updateEmployee(Employee employee);
}
