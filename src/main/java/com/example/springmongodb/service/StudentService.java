package com.example.springmongodb.service;


import com.example.springmongodb.model.Student;
import com.example.springmongodb.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }
}
