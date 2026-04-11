package br.com.javamagazine.clinicajm.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "data_consulta")
    private LocalDateTime data_consulta;

    @NotBlank
    @Size(min = 10)
    @Column(name = "sintomas", nullable = false, length = 255)
    private String sintomas;

    @Column(length = 255)
    private String receita;

    @Column(name = "data_atendimento")
    private LocalDateTime data_atendimento;

    @Column(name = "avaliacao_atendimento")
    private String avaliacao_atendimento;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_id_paciente")
    private Paciente paciente;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "fk_id_medico")
    private Medico medico;
}