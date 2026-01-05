package com.yigiteren.starter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yigiteren.starter.entities.School;
import com.yigiteren.starter.entities.DTOs.SchoolRequestDTO;
import com.yigiteren.starter.entities.DTOs.SchoolResponseDTO;
import com.yigiteren.starter.entities.DTOs.StudentResponseDTO;
import com.yigiteren.starter.exceptions.SchoolNotFoundException;
import com.yigiteren.starter.repository.SchoolRepository;

@Service
public class SchoolService {

    private final StudentService studentService;
    private final SchoolRepository repository;
    @Autowired
    public SchoolService(SchoolRepository repository, StudentService studentService){
        this.repository = repository;
        this.studentService = studentService;
    }

    public SchoolResponseDTO saveSchool(SchoolRequestDTO dto){
        School s = new School();
        BeanUtils.copyProperties(dto, s, "id");
        School saved = repository.save(s);
        return this.toResponseDTO(saved);
    }

    public List<StudentResponseDTO> getStudentsBySchool(Integer id){
        Optional<School> school = repository.findById(id);
        if(school.isEmpty()){
            throw new SchoolNotFoundException(id);
        }
        else{
            return studentService.findBySchool(school.get());
        }
    }

    private SchoolResponseDTO toResponseDTO(School school){
        SchoolResponseDTO dto = new SchoolResponseDTO();
        BeanUtils.copyProperties(school, dto);
        return dto;
    }
}
