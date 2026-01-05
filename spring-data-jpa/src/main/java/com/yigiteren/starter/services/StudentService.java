package com.yigiteren.starter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yigiteren.starter.entities.Student;
import com.yigiteren.starter.entities.StudentFirstNameRequestDTO;
import com.yigiteren.starter.entities.StudentRequestDTO;
import com.yigiteren.starter.entities.StudentResponseDTO;
import com.yigiteren.starter.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentResponseDTO saveStudent(StudentRequestDTO dto){
        Student s = new Student();
        BeanUtils.copyProperties(dto, s, "id");
        studentRepository.save(s);

        return returnResponseDTO(s);
    }

    public List<StudentResponseDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> list = new ArrayList<>();

        for (Student student : students) {
            list.add(returnResponseDTO(student));
        }

        return list;
    }

    public StudentResponseDTO findStudentByID(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return returnResponseDTO(student.get());
        }
        else return null;
    }

    public StudentResponseDTO deleteStudent(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) return null;
        else{
            studentRepository.delete(student.get());
            return returnResponseDTO(student.get());
        }
    }

    public StudentResponseDTO updateStudent(Integer id , StudentRequestDTO dto){
        Optional<Student> optStudent = studentRepository.findById(id);
        Student student = null;
        if(optStudent.isPresent()){
            student = optStudent.get();
            BeanUtils.copyProperties(dto, student, "id");
            studentRepository.save(student);
            
            return returnResponseDTO(student);
        }
        return null;
    }

    public List<StudentResponseDTO> findStudentByFirstName(StudentFirstNameRequestDTO dto){
        List<Student> students = studentRepository.findStudentByFirstName(dto.getFirstName());
        List<StudentResponseDTO> dtoStudents = new ArrayList<>();
        for (Student student : students) {
            dtoStudents.add(returnResponseDTO(student));
        }
        return dtoStudents;
    }

    private StudentResponseDTO returnResponseDTO(Student student){
        StudentResponseDTO dto = new StudentResponseDTO();
        BeanUtils.copyProperties(student, dto);
        return dto;
    }
}