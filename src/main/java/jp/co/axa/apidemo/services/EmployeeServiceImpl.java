package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptions.RecordExistException;
import jp.co.axa.apidemo.exceptions.RecordNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(value = "employees")
    public List<Employee> retrieveEmployees() {
        logger.info("data from db.");
        return employeeRepository.findAll();
    }

    @Cacheable(value = "employee", key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        logger.info("data from db.");
        return employeeRepository.findById(employeeId).orElseThrow(RecordNotFoundException::new);
    }

    @Caching(evict = {
            @CacheEvict(value = "employee", allEntries = true),
            @CacheEvict(value = "employees", allEntries = true)})
    public void saveEmployee(Employee employee) {
        Employee existEmployee = employeeRepository.findById(employee.getId()).orElse(null);

        if (existEmployee != null) {
            throw new RecordExistException();
        }

        employeeRepository.save(employee);
    }

    @Caching(evict = {
            @CacheEvict(value = "employee", allEntries = true),
            @CacheEvict(value = "employees", allEntries = true)})
    public void updateEmployee(Employee employee) {
        getEmployee(employee.getId());

        employeeRepository.save(employee);
    }

    @Caching(evict = {
            @CacheEvict(value = "employee", allEntries = true),
            @CacheEvict(value = "employees", allEntries = true)})
    public void deleteEmployee(Long employeeId) {
        getEmployee(employeeId);

        employeeRepository.deleteById(employeeId);
    }
}
