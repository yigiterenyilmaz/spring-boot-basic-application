package com.yigiteren.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yigiteren.starter.entities.DTOs.StudentFirstNameRequestDTO;
import com.yigiteren.starter.entities.DTOs.StudentRequestDTO;
import com.yigiteren.starter.entities.DTOs.StudentResponseDTO;
import com.yigiteren.starter.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/rest/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<StudentResponseDTO> saveStudent(@RequestBody @Valid StudentRequestDTO student){
        StudentResponseDTO saved = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping(path = "/list")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping(path = "/list/{id}")
    public ResponseEntity<StudentResponseDTO> findStudentByID(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(studentService.findStudentByID(id));
    }
    @GetMapping(path = "/get-student-with-param")
    public ResponseEntity<StudentResponseDTO> getStudent(@RequestParam Integer id){
        return ResponseEntity.ok(studentService.findStudentByID(id));
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(name = "id") Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable(name = "id") Integer id, @RequestBody @Valid StudentRequestDTO dto){
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }
    @PostMapping(path = "/list/first-name")
    public ResponseEntity<List<StudentResponseDTO>> findStudentsByFirstName(@RequestBody @Valid StudentFirstNameRequestDTO dto){
        return ResponseEntity.ok(studentService.findStudentByFirstName(dto));
    }
}
