package jp.co.axa.apidemo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmployeePayload {
    @NotBlank(message = "Name may not be blank")
    private String name;

    @NotBlank(message = "salary may not be blank")
    private String salary;

    @NotBlank(message = "department may not be blank")
    private String department;
}
