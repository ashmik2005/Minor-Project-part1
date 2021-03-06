package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.example.jbdl.minorproject1.security.User;
import com.example.jbdl.minorproject1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        studentService.createStudent(studentCreateRequest.to());
    }

    // Admin scope
    @GetMapping("/studentById")
    public Student getStudentById(@RequestParam("id") int id) {
        return studentService.getStudentById(id);
    }

    // Student
    @GetMapping("/student")
    public Student getStudent(){
        // Here the details need to be fetched from the authentication context
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer studentId = user.getStudent().getId();
        return studentService.getStudentById(studentId);
    }

    // Admin scope
    @GetMapping("/student/all")
    public List<Student> getStudents(){
        return studentService.getAllStudents();
    }

}
