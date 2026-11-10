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
@Table(name = "alunos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    @NotNull(message = "A turma é obrigatória")
    private Turma turma;

    @Column(name = "nome", nullable = false, length = 150)
    @NotBlank(message = "O nome do aluno é obrigatório")
    private String nome;

    @Column(name = "matricula", nullable = false, unique = true, length = 50)
    @NotBlank(message = "A matrícula é obrigatória")
    private String matricula;

    @Column(name = "email", length = 100)
    private String email;
}