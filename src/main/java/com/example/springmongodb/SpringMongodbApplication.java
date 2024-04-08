package com.example.springmongodb;

import com.example.springmongodb.model.Address;
import com.example.springmongodb.model.Gender;
import com.example.springmongodb.model.Student;
import com.example.springmongodb.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringMongodbApplication {
    //private final StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        MongoTemplate mongoTemplate){
        return args->{
            Address address= Address.builder()
                    .country("Azerbaijani")
                    .city("Baku")
                    .postCode("123")
                    .build();
            String email="test@gmail.com";

            Student student= Student.builder()
                    .firstName("test")
                    .lastName("test")
                    .email(email)
                    .gender(Gender.FEMALE)
                    .createdAt(LocalDateTime.now())
                    .address(address)
                    .totalSpentInBooks(BigDecimal.TEN)
                    .subjects(List.of("Maths","Computer Science"))
                    .build();
            Query query=new Query();
            query.addCriteria(Criteria.where("email").is(email));

            List<Student> students=mongoTemplate.find(query, Student.class);

            if (students.size()>1){
                throw new IllegalStateException("found many students with email"+email);

            }
            if (students.isEmpty()){
                studentRepository.insert(student);
            }else {
                System.out.println("Already exists!");
            }




        };
    }

}
