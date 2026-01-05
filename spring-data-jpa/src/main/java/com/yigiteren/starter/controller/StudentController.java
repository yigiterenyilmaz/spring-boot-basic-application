package com.yigiteren.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yigiteren.starter.entities.StudentFirstNameRequestDTO;
import com.yigiteren.starter.entities.StudentRequestDTO;
import com.yigiteren.starter.entities.StudentResponseDTO;
import com.yigiteren.starter.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rest/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping(path = "/save")
    public StudentResponseDTO saveStudent(@RequestBody @Valid StudentRequestDTO student){
        return studentService.saveStudent(student);
    }
    @GetMapping(path = "/list")
    public List<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping(path = "/list/{id}")
    public StudentResponseDTO findStudentByID(@PathVariable(name = "id") Integer id){
        return studentService.findStudentByID(id);
    }
    @GetMapping(path = "/get-student-with-param")
    public StudentResponseDTO getStudent(@RequestParam Integer id){
        return studentService.findStudentByID(id);
    }
    @DeleteMapping(path = "/delete/{id}")
    public StudentResponseDTO deleteStudent(@PathVariable(name = "id") Integer id){
        return studentService.deleteStudent(id);
    }
    @PutMapping(path = "/update/{id}")
    public StudentResponseDTO updateStudent(@PathVariable(name = "id") Integer id, @RequestBody @Valid StudentRequestDTO dto){
        return studentService.updateStudent(id, dto);
    }
    @GetMapping(path = "/list/first-name")
    public List<StudentResponseDTO> findStudentsByFirstName(@RequestBody @Valid StudentFirstNameRequestDTO dto){
        return studentService.findStudentByFirstName(dto);
    }
}
