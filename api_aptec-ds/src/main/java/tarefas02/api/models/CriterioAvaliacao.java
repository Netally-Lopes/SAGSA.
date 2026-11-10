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
@Table(name = "criterios_avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CriterioAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "instrumento_avaliacao_id", nullable = false)
    @NotNull(message = "O instrumento de avaliação é obrigatório")
    private InstrumentoAvaliacao instrumentoAvaliacao;

    @ManyToOne
    @JoinColumn(name = "capacidade_id")
    private Capacidade capacidade;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "A descrição do critério é obrigatória")
    private String descricao;

    @Column(name = "peso")
    private Double peso;
}