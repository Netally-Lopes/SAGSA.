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
@Table(name = "conhecimentos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conhecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    @NotNull(message = "A unidade curricular é obrigatória")
    private UnidadeCurricular unidadeCurricular;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "A descrição do conhecimento é obrigatória")
    private String descricao;
}