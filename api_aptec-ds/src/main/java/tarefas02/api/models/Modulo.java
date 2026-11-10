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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modulos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plano_curso_id", nullable = false)
    @NotNull(message = "O plano de curso é obrigatório")
    private PlanoCurso planoCurso;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome do módulo é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Column(name = "carga_horaria", nullable = false)
    @NotNull(message = "A carga horária é obrigatória")
    private Integer cargaHoraria;

    @Column(name = "ordem")
    private Integer ordem;
}