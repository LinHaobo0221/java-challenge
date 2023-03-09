package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.EmployeeViewModel;
import jp.co.axa.apidemo.model.EmployeePayload;

import java.util.List;

public interface EmployeeService {

    List<EmployeeViewModel> retrieveEmployees();

    EmployeeViewModel getEmployee(Long employeeId);

    void saveEmployee(EmployeePayload employee);

    void deleteEmployee(Long employeeId);

    void updateEmployee(Long id, EmployeePayload employee);
}
