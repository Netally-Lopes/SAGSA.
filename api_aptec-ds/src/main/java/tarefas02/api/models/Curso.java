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
@Table(name = "cursos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "area_tecnologica_id", nullable = false)
    @NotNull(message = "A área tecnológica é obrigatória")
    private AreaTecnologica areaTecnologica;

    @Column(name = "nome", nullable = false, length = 150)
    @NotBlank(message = "O nome do curso é obrigatório")
    private String nome;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "modalidade", length = 50)
    private String modalidade;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}