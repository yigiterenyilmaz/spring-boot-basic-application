package com.yigiteren.starter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yigiteren.starter.entities.School;
import com.yigiteren.starter.entities.Student;
import com.yigiteren.starter.entities.DTOs.StudentFirstNameRequestDTO;
import com.yigiteren.starter.entities.DTOs.StudentRequestDTO;
import com.yigiteren.starter.entities.DTOs.StudentResponseDTO;
import com.yigiteren.starter.exceptions.StudentNotFoundException;
import com.yigiteren.starter.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public StudentResponseDTO saveStudent(StudentRequestDTO dto){
        Student s = new Student();
        BeanUtils.copyProperties(dto, s, "id");
        Student saved = studentRepository.save(s);

        return toResponseDTO(saved);
    }

    public List<StudentResponseDTO> getAllStudents(){

        return studentRepository.findAll()
                .stream()
                .map(student -> toResponseDTO(student))
                .toList();
    }

    public StudentResponseDTO findStudentByID(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return toResponseDTO(student.get());
        }
        else throw new StudentNotFoundException(id);
    }

    public void deleteStudent(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) throw new StudentNotFoundException(id);
        else{
            studentRepository.delete(student.get());
        }
    }

    public StudentResponseDTO updateStudent(Integer id , StudentRequestDTO dto){
        Optional<Student> optStudent = studentRepository.findById(id);
        Student student = null;
        if(optStudent.isPresent()){
            student = optStudent.get();
            BeanUtils.copyProperties(dto, student, "id");
            studentRepository.save(student);
            
            return toResponseDTO(student);
        }
        throw new StudentNotFoundException(id);
    }

    public List<StudentResponseDTO> findStudentByFirstName(StudentFirstNameRequestDTO dto){
        return studentRepository.findStudentByFirstName(dto.getFirstName())
                .stream()
                .map(student -> toResponseDTO(student))
                .toList();
    }

    public List<StudentResponseDTO> findBySchool(School school){
        return studentRepository.findBySchool(school)
                .stream()
                .map(student -> toResponseDTO(student))
                .toList();
    }

    private StudentResponseDTO toResponseDTO(Student student){
        StudentResponseDTO dto = new StudentResponseDTO();
        BeanUtils.copyProperties(student, dto);
        return dto;
    }
}