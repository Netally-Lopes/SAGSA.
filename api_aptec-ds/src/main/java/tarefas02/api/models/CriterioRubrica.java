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
@Table(name = "criterios_rubrica")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CriterioRubrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "criterio_avaliacao_id", nullable = false)
    @NotNull(message = "O critério de avaliação é obrigatório")
    private CriterioAvaliacao criterioAvaliacao;

    @Column(name = "nivel_desempenho", nullable = false, length = 50)
    @NotBlank(message = "O nível de desempenho é obrigatório")
    private String nivelDesempenho;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "A descrição da rubrica é obrigatória")
    private String descricao;
}