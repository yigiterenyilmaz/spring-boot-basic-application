package com.yigiteren.starter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yigiteren.starter.entities.DTOs.SchoolRequestDTO;
import com.yigiteren.starter.entities.DTOs.SchoolResponseDTO;
import com.yigiteren.starter.entities.DTOs.StudentResponseDTO;
import com.yigiteren.starter.services.SchoolService;
import com.yigiteren.starter.services.StudentService;

@RestController
@RequestMapping(path = "/rest/api/school")
public class SchoolController {
    private final SchoolService service;

    @Autowired
    public SchoolController(SchoolService service , StudentService studentService){
        this.service = service;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<SchoolResponseDTO> saveSchool(@RequestBody SchoolRequestDTO dto){
        SchoolResponseDTO saved = service.saveSchool(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping(path = "/students-by-school/{id}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsBySchool(@PathVariable(name ="id") Integer id){
        return ResponseEntity.ok(service.getStudentsBySchool(id));
    }
}
