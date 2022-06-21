package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Student;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {

    // Whatever we require from the front-end
    // Front-end validations

    @NotBlank
    private String name;

    @Positive
    private int age;

    @NotBlank
    @Email
    private String email;

    private String phoneNumber;

    public Student to() {
        return Student.builder()
                .age(this.age)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .build();
    }

}
