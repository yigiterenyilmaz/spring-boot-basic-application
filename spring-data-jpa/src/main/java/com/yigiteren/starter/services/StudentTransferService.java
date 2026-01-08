package com.yigiteren.starter.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.yigiteren.starter.entities.Student;
import com.yigiteren.starter.entities.School;
import com.yigiteren.starter.exceptions.SchoolNotFoundException;
import com.yigiteren.starter.exceptions.StudentNotFoundException;
import com.yigiteren.starter.repository.SchoolRepository;
import com.yigiteren.starter.repository.StudentRepository;

@Service
@ConditionalOnProperty(
    prefix = "feature.student.transfer",
    name = "enabled",
    havingValue = "true",
    matchIfMissing = false
)
public class StudentTransferService {
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public StudentTransferService(StudentRepository studentRepository, SchoolRepository schoolRepository){
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    public void transfer(Integer studentID, Integer schoolID){
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new StudentNotFoundException(studentID));
        School newSchool = schoolRepository.findById(schoolID).orElseThrow(() -> new SchoolNotFoundException(schoolID));
        student.setSchool(newSchool);
        studentRepository.save(student);
    }
}
