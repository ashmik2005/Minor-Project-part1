package com.example.jbdl.minorproject1.services;

import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

}
