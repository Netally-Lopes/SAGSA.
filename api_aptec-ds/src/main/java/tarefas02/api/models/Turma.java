package tarefas02.api.models;

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
@Table(name = "turmas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "escola_id", nullable = false)
    @NotNull(message = "A escola é obrigatória")
    private Escola escola;

    @ManyToOne
    @JoinColumn(name = "plano_curso_id", nullable = false)
    @NotNull(message = "O plano de curso é obrigatório") PlanoCurso planoCurso;

    @Column(name = "codigo", nullable = false, length = 50)
    @NotBlank(message = "O código da turma é obrigatório")
    private String codigo;

    @Column(name = "periodo", length = 50)
    private String periodo;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}