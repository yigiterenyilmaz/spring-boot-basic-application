package com.yigiteren.starter.entities.DTOs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    @NotEmpty(message = "First Name alanı boş bırakılamaz")
    @Size(min = 3 , max = 40)
    private String firstName;
    @Size(min = 3 , max = 40)
    @NotEmpty(message = "Last Name alanı boş bırakılamaz")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    @NotNull(message = "Student with no school is not allowed.")
    @JsonProperty("schoolID")
    private Integer schoolID;
}
