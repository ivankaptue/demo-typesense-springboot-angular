package com.klid.demodockercompose.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Ivan Kaptue
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(max = 255)
    @Column
    private String firstname;

    @NotBlank
    @Length(max = 255)
    @Column
    private String lastname;

    @NotBlank
    @Length(max = 255)
    @Email
    @Column
    private String email;

    @NotBlank
    @Length(max = 255)
    @Column
    private String school;

}
