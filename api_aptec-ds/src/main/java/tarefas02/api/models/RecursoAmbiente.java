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
@Table(name = "recursos_ambientes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecursoAmbiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "situacao_aprendizagem_id", nullable = false)
    @NotNull(message = "A situação de aprendizagem é obrigatória")
    private SituacaoAprendizagem situacaoAprendizagem;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "A descrição do recurso/ambiente é obrigatória")
    private String descricao;

    @Column(name = "tipo", length = 50)
    private String tipo;
}