package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repositories.StudentRepository;
import com.example.jbdl.minorproject1.security.User;
import com.example.jbdl.minorproject1.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Value("${user.authority.student}")
    private String STUDENT_AUTHORITY;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }


    public void createStudent(Student student) {
        // Before creating a student we need to create a user

        User user = student.getUser();
        user.setAuthority(STUDENT_AUTHORITY);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

}
