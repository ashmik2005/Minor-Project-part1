package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.security.User;
import lombok.*;

import javax.validation.constraints.*;

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

    @NotBlank
    @Size(min = 8,max = 14)
    private String password;

    public Student to() {
        return Student.builder()
                .age(this.age)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .user(
                        User.builder()
                                .username(this.email)
                                .password(this.password)
                                .build()
                )
                .build();
    }

}
