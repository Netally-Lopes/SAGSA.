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
@Table(name = "instrumentos_avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstrumentoAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull(message = "A situação de aprendizagem é obrigatória")
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome do instrumento de avaliação é obrigatório")
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
}