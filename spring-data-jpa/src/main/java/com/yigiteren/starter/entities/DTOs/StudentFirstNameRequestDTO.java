package com.yigiteren.starter.entities.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFirstNameRequestDTO {
    @NotEmpty(message = "First Name alanı boş bırakılamaz")
    @Size(min = 3 , max = 40)
    String firstName;
}
