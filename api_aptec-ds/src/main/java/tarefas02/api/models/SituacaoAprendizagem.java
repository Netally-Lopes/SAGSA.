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
@Table(name = "situacoes_aprendizagem")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SituacaoAprendizagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unidade_curricular_id", nullable = false)
    @NotNull(message = "A unidade curricular é obrigatória")
    private UnidadeCurricular unidadeCurricular;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "O usuário autor é obrigatório")
    private Usuario usuario;

    @Column(name = "titulo", nullable = false, length = 150)
    @NotBlank(message = "O título da situação de aprendizagem é obrigatório")
    private String titulo;

    @Column(name = "descricao_contexto", columnDefinition = "TEXT")
    private String descricaoContexto;

    @Column(name = "desafio", columnDefinition = "TEXT")
    private String desafio;

    @Column(name = "carga_horaria", nullable = false)
    @NotNull(message = "A carga horária é obrigatória")
    private Integer cargaHoraria;

    @Column(name = "publico", nullable = false)
    private Boolean publico = false;
}