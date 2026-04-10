package br.com.javamagazine.clinicajm.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 10, max = 255)
    @Column(name = "sitomas", nullable = false, length = 255)
    private String sitomas;

    @Column(name = "receita",length = 255)
    private String receita;

    @Column(name="data_consulta")

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime data_consulta;

    @Column(name="data_atendimento")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate data_atendimento;

    @Column(name="avaliacao_atendimento")
    private Double avaliacao_atendimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_medico")
    private Medico medico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_paciente")
    private Paciente paciente;

}
