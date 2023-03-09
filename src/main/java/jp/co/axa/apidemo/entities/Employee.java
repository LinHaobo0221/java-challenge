package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Getter
    @Setter
    @Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Name may not be blank")
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @NotBlank(message = "Name may not be blank")
    @Column(name="EMPLOYEE_SALARY")
    private String salary;

    @Getter
    @Setter
    @NotBlank(message = "department may not be blank")
    @Column(name="DEPARTMENT")
    private String department;

}
