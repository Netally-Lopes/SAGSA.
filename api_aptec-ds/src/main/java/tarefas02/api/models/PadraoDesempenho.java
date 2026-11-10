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
@Table(name = "padroes_desempenho")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PadraoDesempenho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subfuncao_id", nullable = false)
    @NotNull(message = "A subfunção é obrigatória")
    private Subfuncao subfuncao;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "A descrição do padrão de desempenho é obrigatória")
    private String descricao;
}