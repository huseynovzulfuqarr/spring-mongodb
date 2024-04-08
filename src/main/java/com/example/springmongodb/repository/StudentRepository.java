package com.example.springmongodb.repository;

import com.example.springmongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface StudentRepository extends MongoRepository<Student,String> {
}
