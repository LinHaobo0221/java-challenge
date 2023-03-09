package jp.co.axa.apidemo.model;

import lombok.Getter;

public class EmployeeViewModel {

    public EmployeeViewModel(Long id, String name, String salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String salary;

    @Getter
    private String department;
}
