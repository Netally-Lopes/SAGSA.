package tarefas02.api.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planos_curso")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @NotNull(message = "O curso é obrigatório")
    private Curso curso;

    @Column(name = "codigo_plano", nullable = false, length = 50)
    @NotBlank(message = "O código do plano é obrigatório")
    private String codigoPlano;

    @Column(name = "versao", nullable = false, length = 20)
    private String versao = "1.0";

    @Column(name = "carga_horaria_total", nullable = false)
    @NotNull(message = "A carga horária total é obrigatória")
    private Integer cargaHorariaTotal;

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}