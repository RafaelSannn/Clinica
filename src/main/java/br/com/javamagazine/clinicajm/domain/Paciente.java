package br.com.javamagazine.clinicajm.domain;

import br.com.javamagazine.clinicajm.domain.enums.Sexo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 120)
    @Column(nullable = false, length = 120)
    private String nome;


    @Column(name="data_nascimento")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate data_nascimento;

    @NotBlank
    @Column(name = "SEXO")
    private String sexo;

}
