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
    private final Long id;

    @Getter
    private final String name;

    @Getter
    private final String salary;

    @Getter
    private final String department;

    @Override
    public String toString() {
        return "EmployeeViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
